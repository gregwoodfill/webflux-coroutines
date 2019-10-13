package com.gregwoodfill.webfluxcoroutines.controller

import com.gregwoodfill.webfluxcoroutines.service.LongRunningService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class DemoController(
    private val service: LongRunningService
) {
    private val log = LoggerFactory.getLogger(DemoController::class.java)
    @GetMapping("/cr")
    suspend fun getLongRunningResource(): List<String> {
        log.info("controller start")
        return service.longRunningCall()
            .also { log.info("controller end")
        }
    }

    @GetMapping("/flux")
    fun regularFlux(): Flux<String> {
        return Flux.from { listOf("hello", "flux") }
    }
}
