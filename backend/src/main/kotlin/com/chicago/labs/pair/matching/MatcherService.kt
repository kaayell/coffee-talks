package com.chicago.labs.pair.matching

import com.chicago.labs.domain.Human
import com.chicago.labs.domain.PairHistory
import com.chicago.labs.humans.HumanRepository
import com.chicago.labs.pair.PairHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
open class MatcherService(var humanRepository: HumanRepository,
                          var pairHistoryRepository: PairHistoryRepository) {

    open fun findBestMatch(email: String, humansList: List<Human>, humansAlreadyMatched: Set<Human>): Human? {
        val lowestPairHistory = humansList
                .filter { email != it.email }
                .filter { !humansAlreadyMatched.contains(it) }
                .map { human ->
                    val pairHistory = pairHistoryRepository.findOneByEmailOneAndEmailTwo(email, human.email!!) ?:
                            pairHistoryRepository.findOneByEmailOneAndEmailTwo(human.email!!, email)
                    pairHistory ?: PairHistory(
                            UUID.randomUUID().toString(), email, human.email,
                            0, Date())
                }
                .minBy { pairHistory -> pairHistory.timesPaired ?: 0 }

        val humanEmailThatIsNotMe = if (lowestPairHistory?.emailOne.equals(email)) {
            lowestPairHistory?.emailTwo
        } else {
            lowestPairHistory?.emailOne
        }

        if (humanEmailThatIsNotMe != null) {
            return humanRepository.findFirstByEmail(humanEmailThatIsNotMe)
        }

        return null
    }
}
