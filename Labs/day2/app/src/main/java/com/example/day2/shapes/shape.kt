package com.example.day2.shapes

import com.example.day2.shapes.Rectangle

abstract class Shape(var dim: Double)
{
    constructor():this(0.0)

    abstract fun calcArea( ):Double

}

class Picture
{
    fun sumAreas(first : Shape ,sec : Shape, third : Shape ) : Double
    {

        return first.calcArea() + sec.calcArea() + third.calcArea()
    }
}


class Rectangle(var height :Double ,width : Double) : Shape(width)
{
    constructor(width: Double):this(1.0,width)
    constructor():this(1.0,1.0)

    override fun calcArea(): Double {

        return this.height * this.dim
    }

}

class Circle( radius : Double ) : Shape(radius)
{
    constructor():this(1.0)

    override fun calcArea(): Double {
        return dim * dim * 3.14
    }
}
class Triangle (var height :Double ,width : Double) : Shape(width)
{
    constructor(width: Double):this(1.0,width)
    constructor():this(1.0,1.0)

    override fun calcArea(): Double {
        return 0.5 * dim * height
    }

}


fun main()
{
    var tri = Triangle(2.0,1.0)
    var rec = Rectangle(1.0,1.0)
    var cir = Circle(5.0)

    var pic = Picture()

    println(pic.sumAreas(tri,rec,cir))


}