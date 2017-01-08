package com.chicago.labs.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor

data class Human
@PersistenceConstructor
constructor(@Id val mongoId: ObjectId? = null,
            val email: String? = null,
            val name: String? = null,
            val active: Boolean? = true)


