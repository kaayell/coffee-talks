package com.chicago.labs.humans

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("humans")
data class Human(@Id var email: String? = null,
                 var name: String? = null)

