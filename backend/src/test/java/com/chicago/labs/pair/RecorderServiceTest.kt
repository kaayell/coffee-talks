package com.chicago.labs.pair

import com.chicago.labs.domain.Human
import com.chicago.labs.domain.Pair
import com.chicago.labs.domain.PairHistory
import com.chicago.labs.pair.recording.RecorderService
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.doReturn
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.mockito.Mockito.verify
import java.util.*

class RecorderServiceTest {

    lateinit var pairHistoryRepository: PairHistoryRepository
    lateinit var recorderService: RecorderService

    @Before
    fun setUp() {
        pairHistoryRepository = mock()
        recorderService = RecorderService(pairHistoryRepository)
    }

    @Test
    fun `saves new pair history`() {
        recorderService.record(listOf(Pair(Human("bob@burger.com", ""), Human("tina@burger.com", ""))))

        val argumentCaptor = argumentCaptor<PairHistory>()
        verify(pairHistoryRepository).save(argumentCaptor.capture())
        val pairHistory = argumentCaptor.firstValue
        assertThat(pairHistory.emailOne).isEqualTo("bob@burger.com")
        assertThat(pairHistory.emailTwo).isEqualTo("tina@burger.com")
    }

    @Test
    fun `increases pair count for existing pairs and saves pair date`() {
        doReturn(PairHistory(null, "bob@burger.com", "tina@burger.com", 1, Date()))
                .whenever(pairHistoryRepository).findOneByEmailOneAndEmailTwo("bob@burger.com", "tina@burger.com")
        recorderService.record(listOf(Pair(Human("bob@burger.com", ""), Human("tina@burger.com", ""))))

        val argumentCaptor = argumentCaptor<PairHistory>()
        verify(pairHistoryRepository).save(argumentCaptor.capture())
        val pairHistory = argumentCaptor.firstValue
        assertThat(pairHistory.emailOne).isEqualTo("bob@burger.com")
        assertThat(pairHistory.emailTwo).isEqualTo("tina@burger.com")
        assertThat(pairHistory.timesPaired).isEqualTo(2)
        assertThat(pairHistory.lastPairDate).isToday()
    }

    @Test
    fun `saves null as forever alone`() {
        recorderService.record(listOf(Pair(Human("bob@burger.com", ""), null)))

        val argumentCaptor = argumentCaptor<PairHistory>()
        verify(pairHistoryRepository).save(argumentCaptor.capture())
        val pairHistory = argumentCaptor.firstValue
        assertThat(pairHistory.emailOne).isEqualTo("bob@burger.com")
        assertThat(pairHistory.emailTwo).isEqualTo("foreveralone")
    }
}