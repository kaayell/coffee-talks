package com.chicago.labs.pair

import com.chicago.labs.humans.Human
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat

class MatcherServiceTest {

    lateinit var pairHistoryRepository: PairHistoryRepository
    lateinit var matcherService: MatcherService

    val humansList = listOf(
            Human("bob@burger.com"),
            Human("tina@burger.com"),
            Human("linda@burger.com"))

    @Before
    fun setUp() {
        pairHistoryRepository = mock()
        matcherService = MatcherService(pairHistoryRepository)
    }

    @Test
    fun `matches if pair has no history`() {
        doReturn(null).whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo(any(), any())
        val match = matcherService.findBestMatch("bob@burger.com", humansList, emptySet())
        assertThat(match).isEqualTo(Human("tina@burger.com"))
        verify(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "tina@burger.com")
    }
}