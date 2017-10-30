package com.chicago.labs.pair

import com.chicago.labs.domain.PairingList
import org.springframework.data.mongodb.repository.MongoRepository

interface PairingListRepository : MongoRepository<PairingList, String> {
    fun findTopByOrderByTimestampDesc(): PairingList
}
