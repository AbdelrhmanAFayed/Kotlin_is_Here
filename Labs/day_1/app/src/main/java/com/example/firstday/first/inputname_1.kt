package com.example.firstday.first



fun main()
{
    println("What is your name ?")

    val input: String? = readLine()

    if(input?.isBlank() ?: true)
    {
        println("Anonymous")
    }
    else
    {
        println(input)
    }


}