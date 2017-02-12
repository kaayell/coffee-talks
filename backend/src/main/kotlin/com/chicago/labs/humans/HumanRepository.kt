package com.chicago.labs.humans

import com.chicago.labs.domain.Human
import org.springframework.data.mongodb.repository.MongoRepository

interface HumanRepository : MongoRepository<Human, String> {
    fun deleteHumanByEmail(email: String): Long?
    fun findFirstByEmail(email: String) : Human?
}