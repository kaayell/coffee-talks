package com.chicago.labs.pair

import com.chicago.labs.humans.HumanRepository
import com.chicago.labs.domain.Pair
import com.chicago.labs.domain.PairingList
import com.chicago.labs.domain.Human
import com.chicago.labs.pair.error.PairingListNotFoundException
import com.chicago.labs.pair.matching.MatcherService
import com.chicago.labs.pair.matching.ShuffleService
import com.chicago.labs.pair.recording.RecorderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDate
import java.util.*

@Service
open class PairService
@Autowired constructor(val humanRepository: HumanRepository,
                       val pairingListRepository: PairingListRepository,
                       val shuffleService: ShuffleService,
                       val matcherService: MatcherService,
                       val clock: Clock,
                       val recorderService: RecorderService) {

    open fun match(): PairingList {
        return match(false)
    }

    open fun record(pairListId: String) {
        val pairingList = pairingListRepository.findFirstByInternalId(pairListId) ?: throw PairingListNotFoundException()

        pairingList.recorded = true
        pairingListRepository.save(pairingList)

        recorderService.record(pairingList.pairingList!!)
    }

    open fun latest(): PairingList {
        return pairingListRepository.findFirstByRecordedTrueOrderByTimestampDesc()
    }

    open fun matchAndRecord() {
        val pairingList = match(true)
        recorderService.record(pairingList.pairingList!!)
    }

    private fun match(shouldBeRecorded: Boolean): PairingList {
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

        val pairingList = PairingList(UUID.randomUUID().toString(), pairs, LocalDate.now(clock), false)
        pairingList.recorded = shouldBeRecorded
        pairingListRepository.save(pairingList)
        return pairingList
    }
}

