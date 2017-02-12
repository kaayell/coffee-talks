package com.chicago.labs.pair.scheduler

import com.chicago.labs.pair.PairService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class MatchAndRecordSchedulerTest {

    lateinit var mockPairService: PairService
    lateinit var matchAndRecordScheduler: MatchAndRecordScheduler

    @Before
    fun setUp() {
        mockPairService = mock()
        matchAndRecordScheduler = MatchAndRecordScheduler(mockPairService)
    }

    @Test
    fun `scheduler calls pair service`(){
        matchAndRecordScheduler.match()
        verify(mockPairService).matchAndRecord()
    }
}