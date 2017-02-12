package com.chicago.labs.pair.scheduler

import com.chicago.labs.pair.PairService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled

open class MatchAndRecordScheduler
@Autowired constructor(var service: PairService){

    @Scheduled(fixedRate = 1209600000)
    open fun match() {
        print("I MAKE MATCHES")
//        service.matchAndRecord()
    }

}

