package com.chicago.labs.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Human
@PersistenceConstructor
constructor(@Id val id: ObjectId? = null,
            val email: String? = null,
            val name: String? = null)


