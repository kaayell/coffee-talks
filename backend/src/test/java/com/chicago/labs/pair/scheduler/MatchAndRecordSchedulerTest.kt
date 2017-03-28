package com.chicago.labs.pair.scheduler

import com.chicago.labs.domain.PairingList
import com.chicago.labs.pair.PairService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class MatchAndRecordSchedulerTest {

    lateinit var mockPairService: PairService
    lateinit var matchAndRecordScheduler: MatchAndRecordScheduler
    lateinit var mockClock: Clock

    @Before
    fun setUp() {
        mockPairService = mock()
        mockClock = mock()
        matchAndRecordScheduler = MatchAndRecordScheduler(mockPairService, mockClock)
    }

    @Test
    fun `scheduler calls pair service after two weeks have been passed`() {
        whenever(mockClock.instant()).thenReturn(Instant.ofEpochMilli(1490331600000))
        whenever(mockClock.zone).thenReturn(ZoneId.of("America/Chicago"))

        val pairingList = PairingList().apply { timestamp = LocalDate.of(2017, 3, 10) }
        whenever(mockPairService.latest()).thenReturn(pairingList)

        matchAndRecordScheduler.match()

        verify(mockPairService).matchAndRecord()
    }
}