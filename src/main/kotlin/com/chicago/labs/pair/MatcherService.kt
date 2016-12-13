package com.chicago.labs.pair

import com.chicago.labs.humans.Human
import com.chicago.labs.humans.HumanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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

                    pairHistory ?: PairHistory(emailOne = email, emailTwo = human.email!!)
                }
                .minBy(PairHistory::timesPaired)

        val humanThatIsNotMe = if (lowestPairHistory?.emailOne.equals(email)) {
            lowestPairHistory?.emailTwo
        } else {
            lowestPairHistory?.emailOne
        }

        if(humanThatIsNotMe != null){
            return humanRepository.findOne(humanThatIsNotMe)
        }

        return null
    }
}
