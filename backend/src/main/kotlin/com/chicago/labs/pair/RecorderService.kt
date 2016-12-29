package com.chicago.labs.pair

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
open class RecorderService
@Autowired constructor(var pairHistoryRepository: PairHistoryRepository) {
    open fun record(pairingList: List<Pair>) {
        pairingList.forEach { pair ->
            val emailTwo = if (pair.second == null) {
                "foreveralone"
            } else {
                pair.second!!.email
            }
            val pairHistory = pairHistoryRepository.findOneByEmailOneAndEmailTwo(pair.first.email!!, emailTwo!!)
            if(pairHistory == null) {
                pairHistoryRepository.save(PairHistory(emailOne = pair.first.email!!, emailTwo = emailTwo))
            } else {
                pairHistory.timesPaired++
                pairHistory.lastPairDate = Date.from(Instant.now())
                pairHistoryRepository.save(pairHistory)
            }
        }
    }

}
