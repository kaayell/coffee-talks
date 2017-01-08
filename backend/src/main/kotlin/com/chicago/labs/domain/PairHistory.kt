package com.chicago.labs.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import java.util.*

data class PairHistory
@PersistenceConstructor
constructor(
        @Id val id: ObjectId? = null,
        var emailOne: String,
        var emailTwo: String,
        var timesPaired: Int = 0,
        var lastPairDate: Date = Date())