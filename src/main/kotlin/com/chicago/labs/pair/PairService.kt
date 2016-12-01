package com.chicago.labs.pair

import com.chicago.labs.humans.Human
import com.chicago.labs.humans.HumanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
open class PairService
@Autowired constructor(var humanRepository: HumanRepository,
                       var shuffleService: ShuffleService,
                       var matcherService: MatcherService) {
    open fun match(): List<Pair> {
        val humans = shuffleService.shuffle(humanRepository.findAll())

        val pairs = ArrayList<Pair>()

        val humanSet = humans.toMutableSet()
        val alreadyMatched = HashSet<Human>()

        humanSet.forEach {
            if(!alreadyMatched.contains(it)) {
                val matchForHuman = matcherService.findBestMatch(it.email!!, humans, alreadyMatched)
                pairs.add(Pair(it, matchForHuman))
                alreadyMatched.add(matchForHuman)
            }

            alreadyMatched.add(it)
        }

        return pairs
    }

}

