package com.gregwoodfill.webfluxcoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WebfluxCoroutinesApplicationTests {

    private val log = LoggerFactory.getLogger(WebfluxCoroutinesApplicationTests::class.java)

    @Autowired
    lateinit var webClientBuilder: WebClient.Builder

    @Test
    fun `gets long running resource`() {

        val futures = (1..10_000).map {
            GlobalScope.async(Dispatchers.IO) {
                callEndPoint()
            }
        }

        runBlocking {
            futures.forEach { (it.await()) }
        }
    }

    private suspend fun callEndPoint(): String {
        return webClientBuilder.build().get().uri("http://localhost:8080/cr")
                .awaitExchange().awaitBody<String>()
    }
}
