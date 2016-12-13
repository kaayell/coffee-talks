package com.chicago.labs.pair

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("pairingList")
data class PairingList(@Id var id: String,
                       var pairingList: List<Pair>)

