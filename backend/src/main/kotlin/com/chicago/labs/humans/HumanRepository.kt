package com.chicago.labs.humans

import com.chicago.labs.domain.Human
import org.springframework.data.mongodb.repository.MongoRepository

interface HumanRepository : MongoRepository<Human, String>