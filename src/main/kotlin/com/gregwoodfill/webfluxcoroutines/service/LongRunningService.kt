package com.gregwoodfill.webfluxcoroutines.service

import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class LongRunningService {
    private val log = LoggerFactory.getLogger(LongRunningService::class.java)
    suspend fun longRunningCall(): List<String> {
        log.info("in long running service")
        delay(1000)
        return listOf("hello", "world")
    }
}
