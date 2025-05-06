package com.example.day6.first

import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main()
{
    runBlocking {
        launch {
            for (i in 1..10) {

                println("job1 $i")
                yield()
            }
        }
        async {
            for (i in 1..10) {

                println("job2 $i")
                yield()
            }
        }
    }
}