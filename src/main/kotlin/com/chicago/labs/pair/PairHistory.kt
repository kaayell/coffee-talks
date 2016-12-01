package com.chicago.labs.pair

import org.springframework.data.redis.core.RedisHash

@RedisHash("pairHistory")
data class PairHistory(var emailOne: String,
                       var emailTwo: String,
                       var timesPaired: Int)