package com.chicago.labs.pair

import org.springframework.data.repository.CrudRepository

interface PairingListRepository : CrudRepository<PairingList, String> {
}
