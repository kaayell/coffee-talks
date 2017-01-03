package com.chicago.labs.pair

import com.chicago.labs.domain.PairHistory
import org.springframework.data.mongodb.repository.MongoRepository

interface PairHistoryRepository : MongoRepository<PairHistory, String> {

//    @Query("SELECT p FROM PairHistory p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    fun findOneByEmailOneAndEmailTwo(emailOne: String, emailTwo: String) : PairHistory
}
