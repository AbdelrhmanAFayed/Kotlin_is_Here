package com.example.day6.fifth

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield


fun main()
{
    runBlocking {
        val job = launch {
            for (i in 1..10) {

                println("job1 $i")
                yield()
            }
        }
        delay(10)
        job.cancelAndJoin()
        launch {
            for (i in 1..10) {

                println("job2 $i")
                yield()
            }
        }
    }
    runBlocking {
        val job = launch {
            for (i in 1..10) {

                println("job1 $i")
                yield()
            }
        }
        delay(10)
        job.join()
        launch {
            for (i in 1..10) {

                println("job2 $i")
                yield()
            }
        }
    }

}