package com.chicago.labs.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class PairHistory
@PersistenceConstructor
constructor(
        @Id val id: ObjectId? = null,
        var internalId: String? = null,
        var emailOne: String? = null,
        var emailTwo: String? = null,
        var timesPaired: Int? = null,
        var lastPairDate: Date? = null)