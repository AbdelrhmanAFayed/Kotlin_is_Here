package com.example.firstday.fourth

fun main()
{
    println("Insert num of lines")
    val num = readLine()?.toInt() ?: 1

    var x = 0
    var numofspaces = 2*num - 2
    var numofpad = num -1


    var str = "*"
    var spac = " "
    var output = ""


    while(x < num)
    {
         output = ""
        repeat(x) {output = output + str + spac }
        repeat(numofspaces)  {output = output + spac  }
        repeat(numofpad)  {output = output + spac  }
        repeat(x) {output = output + str + spac }

        println(output)
        x++
        numofspaces = numofspaces - 2
        numofpad--


    }

//
//    while (x < num)
//    {
//        repeat(numofspaces/2 ) {
//            output = output + spac
//
//        }
//
//        repeat(x) {
//
//            if(x%2 == 0)
//            {
//                output = output + spac
//                output = output + str
//            }
//            else
//            {
//                output = output + str
//                output = output + spac
//            }
//        }
//
//
//
//        println(output)
//
//        x++
//        numofspaces--
//
//    }


}