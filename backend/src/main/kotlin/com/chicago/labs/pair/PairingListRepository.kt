package com.chicago.labs.pair

import com.chicago.labs.pair.domain.PairingList
import org.springframework.data.repository.CrudRepository

interface PairingListRepository : CrudRepository<PairingList, String> {
    fun findFirstByOrderByTimestampDesc(): PairingList
}
