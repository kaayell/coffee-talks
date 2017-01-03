package com.chicago.labs.domain

import org.springframework.data.annotation.PersistenceConstructor

data class Pair
@PersistenceConstructor constructor(
        var first: Human? = null,
        var second: Human? = null
)