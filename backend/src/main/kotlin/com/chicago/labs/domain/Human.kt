package com.chicago.labs.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor

data class Human
@PersistenceConstructor
constructor(@Id var email: String? = null,
        var name: String? = null)

