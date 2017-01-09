package com.chicago.labs.pair

import com.chicago.labs.domain.PairHistory
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface PairHistoryRepository : MongoRepository<PairHistory, String> {

//    @Query("{'name':?0}")
    fun findOneByEmailOneAndEmailTwo(emailOne: String, emailTwo: String) : PairHistory?
}
