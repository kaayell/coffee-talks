package com.chicago.labs.pair

import com.chicago.labs.humans.Human
import com.chicago.labs.humans.HumanRepository
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

class PairServiceTest {

    lateinit var shuffleService: ShuffleService
    lateinit var matcherService: MatcherService
    lateinit var recorderService: RecorderService
    lateinit var humanRepository: HumanRepository
    lateinit var pairingListRepository: PairingListRepository
    lateinit var pairService: PairService

    @Before
    fun setUp() {
        humanRepository = mock()
        pairingListRepository = mock()
        matcherService = mock()
        recorderService = mock()
        shuffleService = mock()
        pairService = PairService(humanRepository, pairingListRepository,
                shuffleService, matcherService, recorderService)

        doReturn(listOf(
                Human(email = "bob@burger.com"),
                Human(email = "tina@burger.com"),
                Human(email = "louise@burger.com"),
                Human(email = "gene@burger.com")))
                .whenever(humanRepository).findAll()

        doReturn(listOf(
                Human(email = "louise@burger.com"),
                Human(email = "tina@burger.com"),
                Human(email = "bob@burger.com"),
                Human(email = "gene@burger.com")))
                .whenever(shuffleService).shuffle(any())
    }

    @Test
    fun `gets list of humans and returns pairs list`() {
        doReturn(Human(email = "bob@burger.com")).whenever(matcherService).findBestMatch(eq("louise@burger.com"), any(), any())
        doReturn(Human(email = "gene@burger.com")).whenever(matcherService).findBestMatch(eq("tina@burger.com"), any(), any())

        val matches = pairService.match()
        assertThat(matches.id).isNotBlank()
        assertThat(matches.pairingList).hasSize(2)
        verify(humanRepository).findAll()

        val argumentCaptor = argumentCaptor<PairingList>()
        verify(pairingListRepository).save(argumentCaptor.capture())
        assertThat(argumentCaptor.firstValue.pairingList).hasSize(2)
    }

    @Test
    fun `calls matcher service to make pairs, narrows list along the way`() {
        doReturn(Human(email = "bob@burger.com")).whenever(matcherService).findBestMatch(eq("louise@burger.com"), any(), any())
        doReturn(Human(email = "gene@burger.com")).whenever(matcherService).findBestMatch(eq("tina@burger.com"), any(), any())
        val matches = pairService.match()
        assertThat(matches.pairingList[0].first).isEqualTo(Human(email = "louise@burger.com"))
        assertThat(matches.pairingList[0].second).isEqualTo(Human(email = "bob@burger.com"))

        assertThat(matches.pairingList[1].first).isEqualTo(Human(email = "tina@burger.com"))
        assertThat(matches.pairingList[1].second).isEqualTo(Human(email = "gene@burger.com"))
    }

    @Test
    fun `gets pairing list and calls record service`() {
        doReturn(PairingList("12345", listOf(Pair(Human(), Human())))).whenever(pairingListRepository).findOne("12345")
        pairService.record("12345")

        verify(recorderService).record(listOf(Pair(Human(), Human())))
    }
}