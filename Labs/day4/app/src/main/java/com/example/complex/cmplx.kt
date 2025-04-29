package com.example.complex


import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class Complex(var real : Double = 0.0 , var img : Double = 0.0)
{
    constructor(
        magnitude: Double = 1.0,
        angle: Float = 0.0f
    ):this(magnitude * cos(angle) ,magnitude * sin(angle))

    override fun toString(): String {
        return when
        {
            img < 0 ->  "$real - ${abs(img)}i"
            else -> "$real + ${abs(img)}i"
        }
    }

    infix operator fun plus(complex: Complex): Complex
    {
        return Complex(this.real + complex.real,this.img + complex.img)
    }

    infix operator fun minus(complex: Complex): Complex
    {
        return Complex(this.real - complex.real,this.img - complex.img)
    }
}


fun Complex.print()
{
    println(this)
}

fun main()
{
    var c1 = Complex(img = 20.0)

    var c2 = Complex( magnitude = -10.0 )

    var c3 = c1 + c2

    c3.print()
    c3 = c3 - c2
    c3.print()
}