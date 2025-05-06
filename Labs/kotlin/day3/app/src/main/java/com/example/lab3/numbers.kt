package com.example.lab3



interface NumberSeries
{
    var start: Int
    var current : Int

    fun reset()
    fun getNext(): Int
}

class ByTwo : NumberSeries
{
    override var start: Int = 0
        set(value) {
            current = value
            field = value
        }
    override var current: Int = 0

    override fun reset() {
        current = start
    }

    override fun getNext(): Int {
        current +=2
        return current
    }
}

class ByThree : NumberSeries
{
    override var start: Int = 0
        set(value) {
            current = value
            field = value
        }
    override var current: Int = 0

    override fun reset() {
        current = start
    }

    override fun getNext(): Int {
        current +=3
        return current
    }
}

fun main()
{
    val byTwos = ByTwo();
    val byThrees = ByThree();

    var ref : NumberSeries

    repeat(10) {
        ref = byTwos

        println("By two: ${ref.getNext()}")

        ref = byThrees

        println("By three: ${ref.getNext()}")
    }
}