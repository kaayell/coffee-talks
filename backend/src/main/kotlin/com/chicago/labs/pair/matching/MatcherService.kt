package com.chicago.labs.pair.matching

import com.chicago.labs.domain.Human
import com.chicago.labs.humans.HumanRepository
import com.chicago.labs.domain.PairHistory
import com.chicago.labs.pair.PairHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
open class MatcherService
@Autowired constructor(var humanRepository: HumanRepository,
                       var pairHistoryRepository: PairHistoryRepository) {

    open fun findBestMatch(email: String, humansList: List<Human>, humansAlreadyMatched: Set<Human>): Human? {

        val lowestPairHistory = humansList
                .filter { email != it.email }
                .filter { !humansAlreadyMatched.contains(it) }
                .map {
                    human ->
                    val pairHistory = pairHistoryRepository.findOneByEmailOneAndEmailTwo(email, human.email!!)
                    pairHistory ?: PairHistory(
                            internalId = UUID.randomUUID().toString(),
                            emailOne = email,
                            emailTwo = human.email,
                            timesPaired = 0,
                            lastPairDate = Date())
                }
                .minBy { pairHistory -> pairHistory.timesPaired ?: 0}

        val humanEmailThatIsNotMe = if (lowestPairHistory?.emailOne.equals(email)) {
            lowestPairHistory?.emailTwo
        } else {
            lowestPairHistory?.emailOne
        }

        if(humanEmailThatIsNotMe != null){
            return humanRepository.findFirstByEmail(humanEmailThatIsNotMe)
        }

        return null
    }
}
