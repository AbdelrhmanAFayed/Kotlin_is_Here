package com.example.day6.fourth

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield


suspend fun sum(nums : List<String>) : Double {
    var sums = 0.0
    for (itr in nums)
    {
        yield()
        sums = sums + itr.toDouble()
    }

    return sums
}

suspend fun main()
{
    println("Enter a list of numbers ")

    val input = readLine().orEmpty()
    val tokens = input.split(" ").filter { it.isNotBlank() }

    val job = GlobalScope.async {
        sum(tokens)
    }

    println(job.await())

}