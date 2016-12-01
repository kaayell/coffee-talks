package com.chicago.labs.pair

import com.chicago.labs.humans.Human
import org.springframework.stereotype.Service
import java.util.*

@Service
open class ShuffleService {
    open fun shuffle(humans: List<Human>): List<Human> {
        Collections.shuffle(humans)
        return humans
    }
}
