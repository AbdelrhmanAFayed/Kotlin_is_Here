package com.example.day5



abstract class Person(var name : String,var iD : Int )

class Librarian(name : String , iD : Int , var passWord : String) : Person(name,iD)

class User(name : String , iD : Int , var job : String) : Person(name,iD)


abstract class LibraryItem(var title : String, var ISBN : String , var publication : Date , var numOfPages : Int = 0 , var numOfItems : Int = 0  )
{
    var available: Boolean = numOfItems > 0

    var owner : User = User("None",-1,"None")
    class Date( day : Int , month: Int , year : Int)
    {
        var day : Int = -1
            set(value) {
                field = if( value > 0 && value < 32) {
                    value
                } else{
                    -1
                }
            }
        var month : Int = -1
            set(value) {
                field = if( value > 0 && value < 13) {
                    value
                } else{
                    -1
                }
            }
        var year : Int

        init {
            this.day = day
            this.month = month
            this.year = year
        }
    }

    public fun isAvaliable() : Boolean {
        return available
    }

    public fun deposit(num : Int = 1){
        numOfItems++
    }
    public fun borrow(num : Int = 1) : Boolean {
       return if(numOfItems > num - 1)
        {
           numOfItems--
             true
        }
        else
        {
            false
        }
    }
}

class Book(title : String,ISBN : String ,publication : Date , numOfPages : Int ,numOfItems : Int = 0 ) : LibraryItem(title,ISBN,publication,numOfPages,numOfItems)

class Magazine(title : String,ISBN : String ,publication : Date , numOfPages : Int ,numOfItems : Int = 0 ) : LibraryItem(title,ISBN,publication,numOfPages,numOfItems)

class Journal(title : String,ISBN : String ,publication : Date , numOfPages : Int ,numOfItems : Int = 0 ) : LibraryItem(title,ISBN,publication,numOfPages,numOfItems)


object LibraryDataBase
{
    var currentLibrarian : Librarian = Librarian("Ahmed" , 0 , "Very Strong Password")

    var allItems : MutableMap<String,LibraryItem> = mutableMapOf()

    var borrowedItems : MutableList<Pair<User,LibraryItem>> = mutableListOf()

    fun addItem(item : LibraryItem)
    {
            allItems[item.title]?.deposit() ?: allItems.put(item.title,item)
    }

    fun borrowItem(user : User , item: LibraryItem) : Boolean
    {
        val success = allItems[item.title]?.borrow() ?: false

       if (success)
        {
           borrowedItems.add(Pair(user,item))

        }

        return success
    }

    fun returnItem(user : User ,item: LibraryItem) : Boolean
    {
        return borrowedItems.remove(Pair(user,item))

    }

    fun search(item: LibraryItem) : Boolean
    {
        return allItems.contains(item.title)
    }

    fun returnAvailable() : List<LibraryItem>
    {
        val availableItems = mutableListOf<LibraryItem>()

        for (entry in allItems)
        {
            if(entry.value.numOfItems > 0)
            {
                availableItems.add(entry.value)
            }
        }
        return availableItems
    }
}