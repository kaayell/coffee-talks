package com.chicago.labs.pair

import com.chicago.labs.domain.Human
import com.chicago.labs.humans.HumanRepository
import com.chicago.labs.domain.Pair
import com.chicago.labs.domain.PairingList
import com.chicago.labs.pair.error.PairingListNotFoundException
import com.chicago.labs.pair.matching.MatcherService
import com.chicago.labs.pair.matching.ShuffleService
import com.chicago.labs.pair.recording.RecorderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
open class PairService
@Autowired constructor(var humanRepository: HumanRepository,
                       var pairingListRepository: PairingListRepository,
                       var shuffleService: ShuffleService,
                       var matcherService: MatcherService,
                       var recorderService: RecorderService) {

    open fun match(): PairingList {
        val humans = shuffleService.shuffle(humanRepository.findAll())

        val pairs = ArrayList<Pair>()

        val humanSet = humans.toMutableSet()
        val alreadyMatched = HashSet<Human>()

        humanSet.forEach {
            if (!alreadyMatched.contains(it)) {
                val matchForHuman = matcherService.findBestMatch(it.email!!, humans, alreadyMatched)
                pairs.add(Pair(it, matchForHuman))
                if (matchForHuman != null) {
                    alreadyMatched.add(matchForHuman)
                }
            }

            alreadyMatched.add(it)
        }

        val pairingList = PairingList(
                UUID.randomUUID().toString(),
                pairs,
                Date())
        pairingListRepository.save(pairingList)
        return pairingList
    }

    open fun record(pairListId: String) {
        val pairingList = pairingListRepository.findOne(pairListId) ?: throw PairingListNotFoundException()
        recorderService.record(pairingList.pairingList!!)
    }

    open fun latest() : PairingList {
        return pairingListRepository.findFirstByOrderByTimestampDesc()
    }
}

