package com.chicago.labs.pair

import com.chicago.labs.humans.Human
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class MatcherService
@Autowired constructor(var pairHistoryRepository: PairHistoryRepository) {

    open fun findBestMatch(email: String, humansList: List<Human>, humansAlreadyMatched: Set<Human>): Human {
        return Human()
    }
}
