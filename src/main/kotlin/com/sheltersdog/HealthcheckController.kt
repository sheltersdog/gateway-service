package com.sheltersdog

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthcheckController {

    @GetMapping("/health")
    fun health(): String {
        return "service is health"
    }
}