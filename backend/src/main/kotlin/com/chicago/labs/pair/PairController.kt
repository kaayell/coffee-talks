package com.chicago.labs.humans

import com.chicago.labs.pair.Pair
import com.chicago.labs.pair.PairService
import com.chicago.labs.pair.PairingList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/pair")
open class PairController
@Autowired constructor(var pairService: PairService) {

    @GetMapping
    open fun get(): PairingList {
        return pairService.match()
    }

    @PostMapping("/record/{id}")
    open fun post(@PathVariable id: String) {
        pairService.record(id)
    }
}

