package com.example.day5.calc

val add = { num1: Double, num2: Double -> num1 + num2 }
val sub = {num1: Double, num2: Double -> num1 - num2 }
val mul = {num1: Double, num2: Double -> num1 * num2 }
val div = {num1: Double, num2: Double ->
    if(num2 == 0.0){0.0}
    else{num1/num2}
}

typealias param  = (Double, Double) -> Double

fun calc(num1 : Double ,num2 : Double,equ : param) : Double
{
    return equ(num1,num2)
}

fun main()
{
    println(calc(20.6 , 21.5 , add))
    println(calc(19.4 , 10.0 , sub))
    println(calc(50.0 , 2.0 , mul))
    println(calc(200.0 , 2.0 , div))

}




