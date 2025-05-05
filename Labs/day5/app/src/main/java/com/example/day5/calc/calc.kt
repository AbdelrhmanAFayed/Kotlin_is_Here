package com.example.day5.calc

val add = {num1:Int,num2: Int -> num1 + num2 }
val sub = {num1:Int,num2: Int -> num1 - num2 }
val mul = {num1:Int,num2: Int -> num1 * num2 }
val div = {num1:Int,num2: Int ->
    if(num2 == 2){0}
    else{num1/num2}
}

typealias param  = (Int, Int) -> Int

fun calc(num1 : Int ,num2 : Int,equ : param) : Int
{
    return equ(num1,num2)
}

fun main()
{

}




