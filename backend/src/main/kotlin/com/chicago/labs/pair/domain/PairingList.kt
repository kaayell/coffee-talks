package com.chicago.labs.pair.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*

@RedisHash("pairingList")
data class PairingList(@Id var id: String,
                       var pairingList: List<Pair>?,
                       var timestamp: Date?
)

