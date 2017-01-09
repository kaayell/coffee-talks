package com.chicago.labs.pair

import com.chicago.labs.domain.Human
import com.chicago.labs.humans.HumanRepository
import com.chicago.labs.domain.PairHistory
import com.chicago.labs.pair.matching.MatcherService
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat

class MatcherServiceTest {

    lateinit var humanRepository: HumanRepository
    lateinit var pairHistoryRepository: PairHistoryRepository
    lateinit var matcherService: MatcherService

    val humansList = listOf(
            Human(email = "bob@burger.com", name = ""),
            Human(email = "tina@burger.com", name = ""),
            Human(email = "linda@burger.com", name = ""),
            Human(email = "louise@burger.com", name = ""))

    @Before
    fun setUp() {
        humanRepository = mock()
        pairHistoryRepository = mock()
        matcherService = MatcherService(humanRepository, pairHistoryRepository)
    }

    @Test
    fun `matches if pair has no history`() {
        doReturn(Human(email = "tina@burger.com", name = "")).whenever(humanRepository).findFirstByEmail("tina@burger.com")
        doReturn(null).whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo(any(), any())
        val match = matcherService.findBestMatch("bob@burger.com", humansList, emptySet())
        assertThat(match).isEqualTo(Human(email = "tina@burger.com", name = ""))
        verify(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "tina@burger.com")
    }

    @Test
    fun `ignores already humansAlreadyMatched`() {
        doReturn(Human(email = "linda@burger.com", name = "")).whenever(humanRepository).findFirstByEmail("linda@burger.com")
        doReturn(null).whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo(any(), any())
        val match = matcherService.findBestMatch("bob@burger.com", humansList, setOf(Human(email = "tina@burger.com", name = "")))
        assertThat(match).isEqualTo(Human(email = "linda@burger.com", name = ""))
        verify(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "linda@burger.com")
    }

    @Test
    fun `matches based off lowest pair times`() {
        doReturn(Human(email = "louise@burger.com", name = "")).whenever(humanRepository).findFirstByEmail("louise@burger.com")

        doReturn(PairHistory(emailOne = "bob@burger.com", emailTwo =  "tina@burger.com", timesPaired = 2))
                .whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "tina@burger.com")
        doReturn(PairHistory(emailOne = "bob@burger.com", emailTwo = "linda@burger.com", timesPaired = 3))
                .whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "linda@burger.com")
        doReturn(PairHistory(emailOne = "bob@burger.com", emailTwo = "louise@burger.com", timesPaired = 1))
                .whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "louise@burger.com")

        val match = matcherService.findBestMatch("bob@burger.com", humansList, emptySet())
        assertThat(match).isEqualTo(Human(email = "louise@burger.com", name = ""))
    }
}