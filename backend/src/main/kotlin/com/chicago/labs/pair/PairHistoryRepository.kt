package com.chicago.labs.pair

import com.chicago.labs.pair.domain.PairHistory
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PairHistoryRepository : CrudRepository<PairHistory, String> {

//    @Query("SELECT p FROM PairHistory p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    fun findOneByEmailOneAndEmailTwo(emailOne: String, emailTwo: String) : PairHistory
}
