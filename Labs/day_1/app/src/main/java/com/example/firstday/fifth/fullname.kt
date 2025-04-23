package com.example.firstday.fifth



fun main()
{
    println("Enter First Name")
    var firstname = readLine()
    println("Enter Last Name")
    var lastname = readLine()

    val result = "$firstname $lastname";


    if(result.isBlank())
    {println("Anonymous") }
    else
    { println(result) }

}