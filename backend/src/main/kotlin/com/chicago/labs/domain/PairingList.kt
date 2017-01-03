package com.chicago.labs.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import java.util.*

data class PairingList
@PersistenceConstructor
constructor(@Id var id: String? = null,
            var pairingList: List<Pair>?,
            var timestamp: Date?)

