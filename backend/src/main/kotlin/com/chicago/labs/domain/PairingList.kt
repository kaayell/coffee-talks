package com.chicago.labs.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import java.util.*

data class PairingList
@PersistenceConstructor
constructor(
        @Id val id: ObjectId? = null,
        var pairingList: List<Pair>?,
        var timestamp: Date?)

