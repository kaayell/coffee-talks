package com.chicago.labs.humans

import com.chicago.labs.pair.Pair
import com.chicago.labs.pair.PairService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/pair")
open class PairController
@Autowired constructor(var pairService: PairService) {

    @GetMapping
    open fun get(): List<Pair> {
        return pairService.match()
    }
}

