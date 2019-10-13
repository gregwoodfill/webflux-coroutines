package com.gregwoodfill.webfluxcoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class CoroutineTest {

    private val log = LoggerFactory.getLogger(CoroutineTest::class.java)

    @Test
    fun `1 million coroutines`() {
            val futures = (1..1_000_000).map { i ->
                GlobalScope.async {
                    i + 1
                }
            }
            runBlocking {
                futures.forEach { log.info(it.await().toString()) }
            }
    }

//    @Test
//    fun `1 million threads`() {
//        (1..100_000).forEach {
//            thread {
//                log.info("${it + 1}")
//            }
//        }
//    }
}
