package com.chicago.labs.pair

import org.springframework.data.repository.CrudRepository

interface PairHistoryRepository : CrudRepository<PairHistory, String> {
    fun findOneByEmailOneAndEmailTwo(emailOne: String, emailTwo: String) : PairHistory
}
