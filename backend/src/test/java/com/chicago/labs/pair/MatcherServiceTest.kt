package com.chicago.labs.pair

import com.chicago.labs.domain.Human
import com.chicago.labs.humans.HumanRepository
import com.chicago.labs.domain.PairHistory
import com.chicago.labs.pair.matching.MatcherService
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import java.util.*

class MatcherServiceTest {

    lateinit var humanRepository: HumanRepository
    lateinit var pairHistoryRepository: PairHistoryRepository
    lateinit var matcherService: MatcherService

    val humansList = listOf(
            Human("bob@burger.com", ""),
            Human("tina@burger.com", ""),
            Human("linda@burger.com", ""),
            Human("louise@burger.com", ""))

    @Before
    fun setUp() {
        humanRepository = mock()
        pairHistoryRepository = mock()
        matcherService = MatcherService(humanRepository, pairHistoryRepository)
    }

    @Test
    fun `matches if pair has no history`() {
        doReturn(Human("tina@burger.com", "")).whenever(humanRepository).findFirstByEmail("tina@burger.com")
        doReturn(null).whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo(any(), any())
        val match = matcherService.findBestMatch("bob@burger.com", humansList, emptySet())
        assertThat(match).isEqualTo(Human("tina@burger.com", ""))
        verify(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "tina@burger.com")
    }

    @Test
    fun `ignores already humansAlreadyMatched`() {
        doReturn(Human("linda@burger.com", "")).whenever(humanRepository).findFirstByEmail("linda@burger.com")
        doReturn(null).whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo(any(), any())
        val match = matcherService.findBestMatch("bob@burger.com", humansList, setOf(Human("tina@burger.com", "")))
        assertThat(match).isEqualTo(Human("linda@burger.com", ""))
        verify(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "linda@burger.com")
    }

    @Test
    fun `matches based off lowest pair times`() {
        doReturn(Human("louise@burger.com", "")).whenever(humanRepository).findFirstByEmail("louise@burger.com")

        doReturn(PairHistory(null, "bob@burger.com", "tina@burger.com", 2, Date()))
                .whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "tina@burger.com")
        doReturn(PairHistory(null, "bob@burger.com","linda@burger.com", 3, Date()))
                .whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "linda@burger.com")
        doReturn(PairHistory(null, "bob@burger.com","louise@burger.com", 1, Date()))
                .whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "louise@burger.com")

        val match = matcherService.findBestMatch("bob@burger.com", humansList, emptySet())
        assertThat(match).isEqualTo(Human("louise@burger.com", ""))
    }
}