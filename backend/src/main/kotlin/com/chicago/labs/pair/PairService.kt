package com.chicago.labs.pair

import com.chicago.labs.domain.Human
import com.chicago.labs.domain.Pair
import com.chicago.labs.domain.PairingList
import com.chicago.labs.humans.HumanRepository
import com.chicago.labs.pair.matching.MatcherService
import com.chicago.labs.pair.matching.ShuffleService
import com.chicago.labs.pair.recording.RecorderService
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime
import java.util.*

@Service
open class PairService(
        val humanRepository: HumanRepository,
        val pairingListRepository: PairingListRepository,
        val shuffleService: ShuffleService,
        val matcherService: MatcherService,
        val clock: Clock,
        val recorderService: RecorderService) {

    open fun latest(): PairingList = pairingListRepository.findTopByOrderByTimestampDesc()

    open fun matchAndRecord(): PairingList =
            match().also { pairingList ->
                recorderService.record(pairingList.pairingList!!)
            }

    private fun match(): PairingList = shuffleService.shuffle(humanRepository.findAll())
            .let { shuffledHumans ->
                val pairs = ArrayList<Pair>()

                val alreadyMatchedHumans = HashSet<Human>()
                shuffledHumans.toMutableSet().forEach { human ->
                    if (!alreadyMatchedHumans.contains(human)) {
                        val matchForHuman = matcherService.findBestMatch(human.email!!, shuffledHumans, alreadyMatchedHumans)
                        pairs.add(Pair(human, matchForHuman))
                        if (matchForHuman != null) {
                            alreadyMatchedHumans.add(matchForHuman)
                        }
                    }
                    alreadyMatchedHumans.add(human)
                }

                PairingList(UUID.randomUUID().toString(), pairs, LocalDateTime.now(clock))
                        .also { pairingListRepository.save(it) }
            }
}

