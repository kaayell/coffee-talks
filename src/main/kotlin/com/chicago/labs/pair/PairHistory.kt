package com.chicago.labs.pair

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.Instant
import java.util.*

@RedisHash("pairHistory")
data class PairHistory(@Id var id: String = UUID.randomUUID().toString(),
                       var emailOne: String,
                       var emailTwo: String,
                       var timesPaired: Int = 0,
                       var lastPairDate: Date = Date.from(Instant.now()))