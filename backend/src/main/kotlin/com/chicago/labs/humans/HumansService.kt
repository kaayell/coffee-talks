package com.chicago.labs.humans

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
open class HumansService
@Autowired constructor(var humanRepository: HumanRepository) {

    open fun create(human: Human) {
        humanRepository.save(human)
    }

    open fun getAll(): List<Human> {
        return humanRepository.findAll()

    }
}
