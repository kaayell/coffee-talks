package com.chicago.labs.pair

import com.chicago.labs.domain.PairingList
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/pair")
open class PairController(var pairService: PairService) {

    @PostMapping("/matchAndRecord")
    open fun matchAndRecord(): PairingList = pairService.matchAndRecord()

    @GetMapping("/latest")
    open fun getLatest(): PairingList = pairService.latest()
}

