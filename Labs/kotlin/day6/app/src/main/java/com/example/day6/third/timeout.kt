package com.example.day6.third

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main()
{
    runBlocking {
        withTimeout(10_000) {
            launch {
                for (i in 1..100) {

                    println("job1 $i")
                    delay(1000)
                }
            }
            async {
                for (i in 1..100) {

                    println("job2 $i")
                    delay(1000)
                }
            }
        }

    }
}