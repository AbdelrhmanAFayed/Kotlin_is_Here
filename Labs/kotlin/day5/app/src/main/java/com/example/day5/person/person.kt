package com.example.day5.person

data class Person(var name : String ,var id : Int , var gender : String)


fun main()
{
    var person = Person("Alice", 1, "Female").apply {
        name = "Alice Alice"
        gender = "F"
    }

    person.also {
        println(it)
    }

    println(person.run {
        id++
        "$name says hello"
    })


}