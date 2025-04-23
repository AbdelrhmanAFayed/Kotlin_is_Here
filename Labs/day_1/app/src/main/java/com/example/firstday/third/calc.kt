package com.example.firstday.third



fun main()
{
    println("Enter your operation: ")

    val input = readLine()

    if(input?.isBlank() ?: true)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
    {
        println("Invalid Operation")
    }
    else
    {

        if(input.split("+").size == 2 )
        {
            val args = input.split("+")
            val result = args[0].toDouble() + args[1].toDouble()
            println(result)
        }
        else if (input.split("-").size == 2 )
        {
            val args = input.split("-")
            val result = args[0].toDouble() - args[1].toDouble()
            println(result)
        }
        else if (input.split("*").size == 2 )
        {
            val args = input.split("*")
            val result = args[0].toDouble() * args[1].toDouble()
            println(result)
        }
        else if (input.split("/").size == 2 )
        {
            val args = input.split("/")
            if(args[1].toDouble() == 0.0) {
                println("Invalid Operation")

            }
            else {
                val result = args[0].toDouble() / args[1].toDouble()
                println(result)
            }
        }
        else
        {
            println("Invalid Operation")

        }



    }




}