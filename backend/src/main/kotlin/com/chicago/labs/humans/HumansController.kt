package com.chicago.labs.humans

import com.chicago.labs.domain.Human
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
@RequestMapping("/humans")
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

    @GetMapping("/emails")
    open fun getEmails(): List<String> {
        return humansService.getEmails()
    }

    @PutMapping("/remove")
    open fun remove(@RequestBody human: Human) {
        humansService.remove(human)
    }
}

