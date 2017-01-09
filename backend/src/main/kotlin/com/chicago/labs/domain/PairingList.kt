package com.chicago.labs.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class PairingList
@PersistenceConstructor
constructor(
        @Id val id: ObjectId? = null,
        val internalId: String? = null,
        var pairingList: List<Pair>? = null,
        var timestamp: Date? = null)

