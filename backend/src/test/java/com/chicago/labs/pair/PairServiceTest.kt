package com.chicago.labs.pair

import com.chicago.labs.domain.Human
import com.chicago.labs.domain.Pair
import com.chicago.labs.domain.PairingList
import com.chicago.labs.humans.HumanRepository
import com.chicago.labs.pair.matching.MatcherService
import com.chicago.labs.pair.matching.ShuffleService
import com.chicago.labs.pair.recording.RecorderService
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class PairServiceTest {

    var shuffleService: ShuffleService = mock()
    var matcherService: MatcherService = mock()
    var recorderService: RecorderService = mock()
    var humanRepository: HumanRepository = mock()
    var pairingListRepository: PairingListRepository = mock()
    var pairService: PairService = mock()
    var mockClock: Clock = mock()

    val humanList = listOf(
            Human("bob@burger.com", "bob"),
            Human("tina@burger.com", "tina"),
            Human("louise@burger.com", "louise"),
            Human("gene@burger.com", "gene"))

    val shuffledHumans = listOf(
            Human("louise@burger.com", "louise"),
            Human("tina@burger.com", "tina"),
            Human("bob@burger.com", "bob"),
            Human("gene@burger.com", "gene"))

    @Before
    fun setUp() {
        pairService = PairService(humanRepository, pairingListRepository,
                shuffleService, matcherService, mockClock, recorderService)

        doReturn(humanList).whenever(humanRepository).findAll()
        doReturn(shuffledHumans).whenever(shuffleService).shuffle(any())

        whenever(mockClock.instant()).thenReturn(Instant.ofEpochMilli(1490331600000))
        whenever(mockClock.zone).thenReturn(ZoneId.of("America/Chicago"))

        doReturn(Human("bob@burger.com", "bob"))
                .whenever(matcherService).findBestMatch(eq("louise@burger.com"), any(), any())
        doReturn(Human("gene@burger.com", "gene"))
                .whenever(matcherService).findBestMatch(eq("tina@burger.com"), any(), any())

    }

    @Test
    fun `should return most recently recorded pair list`() {
        val expectedPairingList = PairingList(null, listOf(Pair(Human("", ""), Human("", ""))), LocalDateTime.now())
        doReturn(expectedPairingList).whenever(pairingListRepository).findTopByOrderByTimestampDesc()
        val actualPairingList = pairService.latest()
        assertThat(actualPairingList).isEqualTo(expectedPairingList)
        verify(pairingListRepository).findTopByOrderByTimestampDesc()
    }

    @Test
    fun `should shuffle the humans for rando pairing`() {
        pairService.matchAndRecord()
        verify(shuffleService).shuffle(humanList)
    }

    @Test
    fun `should call matcher service for humans`() {
        pairService.matchAndRecord()
        verify(matcherService).findBestMatch(eq("louise@burger.com"), eq(shuffledHumans), any())
        verify(matcherService).findBestMatch(eq("tina@burger.com"), eq(shuffledHumans), any())
    }

    @Test
    fun `should keep a list of humans that have already been assigned a pair`() {
        pairService.matchAndRecord()
        verify(matcherService, times(1)).findBestMatch("tina@burger.com", shuffledHumans,
                hashSetOf(Human("louise@burger.com", "louise"),
                        Human("bob@burger.com", "bob"), Human("tina@burger.com", "tina"),
                        Human("gene@burger.com", "gene")))
    }

    @Test
    fun `should not match for a human that has already been assigned a pair`() {
        pairService.matchAndRecord()
        verify(matcherService, times(0)).findBestMatch(eq("bob@burger.com"), any(), any())
        verify(matcherService, times(0)).findBestMatch(eq("gene@burger.com"), any(), any())
    }

    @Test
    fun `should save pairing list`() {
        pairService.matchAndRecord()

        val argumentCaptor = argumentCaptor<PairingList>()
        verify(pairingListRepository).save(argumentCaptor.capture())

        val matches = argumentCaptor.firstValue
        assertThat(matches.pairingList!![0].first).isEqualTo(Human("louise@burger.com", "louise"))
        assertThat(matches.pairingList!![0].second).isEqualTo(Human("bob@burger.com", "bob"))

        assertThat(matches.pairingList!![1].first).isEqualTo(Human("tina@burger.com", "tina"))
        assertThat(matches.pairingList!![1].second).isEqualTo(Human("gene@burger.com", "gene"))
    }

    @Test
    fun `should call recorder service to save the pair history`() {
        pairService.matchAndRecord()

        val argumentCaptor = argumentCaptor<List<Pair>>()
        verify(recorderService).record(argumentCaptor.capture())

        val pairs = argumentCaptor.firstValue
        assertThat(pairs[0].first).isEqualTo(Human("louise@burger.com", "louise"))
        assertThat(pairs[0].second).isEqualTo(Human("bob@burger.com", "bob"))

        assertThat(pairs[1].first).isEqualTo(Human("tina@burger.com", "tina"))
        assertThat(pairs[1].second).isEqualTo(Human("gene@burger.com", "gene"))
    }

    @Test
    fun `should return pair list`() {
        val matches = pairService.matchAndRecord()
        assertThat(matches.pairingList).hasSize(2)
    }
}
