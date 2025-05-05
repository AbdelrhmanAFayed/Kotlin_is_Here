package com.example.day6.second

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield


suspend fun fact(num : Long) : Long {
    if(num == 0L){
        return 1
    }
    yield()
    return num * fact(num -1)
}

suspend fun main()
{
    var input =  readLine() ?: "0"
    input = input.split(".")[0]
    if (input.isBlank()) input = "0"

        val job = GlobalScope.async {
            fact(input.toLong())
        }
    println(job.await())


}