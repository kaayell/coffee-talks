package com.chicago.labs.humans

import org.springframework.data.repository.CrudRepository

interface HumanRepository : CrudRepository<Human, String> {
    override fun findAll(): List<Human>
}