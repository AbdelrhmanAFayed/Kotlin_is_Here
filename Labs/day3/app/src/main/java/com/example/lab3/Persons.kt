package com.example.lab3



data class Person(var name : String,var address: Address)
{
    data class Address(var streetName : String , var city : String, var building : building)

}

enum class building {
    VILLA
    ,APARTMENT
}


fun main()
{
    var someone = Person("Ahmed", Person.Address("Street","Cairo", building.APARTMENT))

    println(someone)


}