package com.chicago.labs.humans

import com.chicago.labs.domain.Human
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/humans")
@CrossOrigin
open class HumansController
@Autowired constructor(var humansService: HumansService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    open fun post(@RequestBody human: Human) {
        humansService.create(human)
    }

    @GetMapping
    open fun get(): List<Human> {
        return humansService.getAll()
    }
}

