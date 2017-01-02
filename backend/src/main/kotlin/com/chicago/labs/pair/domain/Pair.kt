package com.chicago.labs.pair.domain

import com.chicago.labs.humans.Human

data class Pair(
        var first: Human,
        var second: Human? = null
)
