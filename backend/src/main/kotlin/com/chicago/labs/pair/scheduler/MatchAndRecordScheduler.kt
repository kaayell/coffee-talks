package com.chicago.labs.pair.scheduler

import com.chicago.labs.pair.PairService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime

@Component
open class MatchAndRecordScheduler(private val service: PairService, private val clock: Clock) {

    @Scheduled(cron = "0 0 1 * * ?")
    open fun match() {
        val latestPairingListTime = service.latest().timestamp
        val localDate = LocalDateTime.now(clock)

        if (localDate >= latestPairingListTime.plusDays(14)) {
            service.matchAndRecord()
        }
    }
}

