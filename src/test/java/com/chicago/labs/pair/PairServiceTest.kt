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
    lateinit var humanRepository: HumanRepository
    lateinit var pairService: PairService

    @Before
    fun setUp() {
        humanRepository = mock()
        matcherService = mock()
        shuffleService = mock()
        pairService = PairService(humanRepository, shuffleService, matcherService)

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
        assertThat(matches).hasSize(2)
        verify(humanRepository).findAll()
    }

    @Test
    fun `calls matcher service to make pairs, narrows list along the way`() {
        doReturn(Human(email = "bob@burger.com")).whenever(matcherService).findBestMatch(eq("louise@burger.com"), any(), any())
        doReturn(Human(email = "gene@burger.com")).whenever(matcherService).findBestMatch(eq("tina@burger.com"), any(), any())
        val matches = pairService.match()
        assertThat(matches[0].first).isEqualTo(Human(email = "louise@burger.com"))
        assertThat(matches[0].second).isEqualTo(Human(email = "bob@burger.com"))

        assertThat(matches[1].first).isEqualTo(Human(email = "tina@burger.com"))
        assertThat(matches[1].second).isEqualTo(Human(email = "gene@burger.com"))
    }
}