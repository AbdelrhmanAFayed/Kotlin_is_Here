package com.example.day2.shapes

import com.example.day2.shapes.Rectangle

abstract class Shape(open val dim: Double)
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


class Rectangle(height :Double ,width : Double) : Shape(width)
{
    var height : Double = 0.0
        get() = field
        set(value) {
            field = value
        }

    constructor(width: Double):this(1.0,width)
    constructor():this(1.0,1.0)

    init {
        this.height = height
    }

    override fun calcArea(): Double {

        return this.height * this.dim
    }

}

class Circle( radius : Double ) : Shape(radius)
{
    override var dim = radius
        get() = field
        set(value) {
            field = value
        }

    constructor():this(1.0)

    override fun calcArea(): Double {
        return dim * dim * 3.14
    }
}

class Triangle ( height :Double ,width : Double) : Shape(width)
{
    var height : Double = 0.0
        get() = field
        set(value) {
            field = value
        }
    constructor(width: Double):this(1.0,width)
    constructor():this(1.0,1.0)

    init {
        this.height = height
    }


    override fun calcArea(): Double {
        return 0.5 * dim * height
    }

}


fun main()
{
    var tri = Triangle(0.0,1.0)
    var rec = Rectangle(0.0,1.0)
    var cir = Circle(10.0)



    var pic = Picture()

    println(pic.sumAreas(tri,rec,cir))


}