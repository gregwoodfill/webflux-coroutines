package com.gregwoodfill.webfluxcoroutines

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxCoroutinesApplication

fun main(args: Array<String>) {
    runApplication<WebfluxCoroutinesApplication>(*args)
}
