package com.chicago.labs.pair.matching

import com.chicago.labs.domain.Human
import org.springframework.stereotype.Service
import java.util.*

@Service
open class ShuffleService {
    open fun shuffle(humans: List<Human>): List<Human> {
        Collections.shuffle(humans)
        return humans
    }
}
