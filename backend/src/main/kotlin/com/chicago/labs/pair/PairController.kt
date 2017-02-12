package com.chicago.labs.humans

import com.chicago.labs.pair.PairService
import com.chicago.labs.domain.PairingList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/pair")
open class PairController
@Autowired constructor(var pairService: PairService) {

    @GetMapping
    open fun get(): PairingList {
        return pairService.match()
    }

//    @PostMapping("/record/{id}")
    open fun post(@PathVariable id: String) {
        pairService.record(id)
    }

    @GetMapping("/latest")
    open fun getLatest(): PairingList {
        return pairService.latest()
    }
}

