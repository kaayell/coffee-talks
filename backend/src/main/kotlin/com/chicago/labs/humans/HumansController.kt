package com.chicago.labs.humans

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/humans")
open class HumansController
@Autowired constructor(var humansService: HumansService) {

    @RequestMapping(method = arrayOf(POST))
    @ResponseStatus(HttpStatus.CREATED)
    open fun post(@RequestBody human: Human) {
        humansService.create(human)
    }


    @RequestMapping(method = arrayOf(GET))
    open fun get(): List<Human> {
        return humansService.getAll()
    }
}

