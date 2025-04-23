package com.example.firstday.second



 import java.util.Random



fun main()
{

    val arr = arrayOfNulls<Int>(100)





    for(x in 0..99)
    {
        arr[x] = (Random().nextInt(99) + 1 )


        if((arr?.get(x) ?:100) <= 10 )
        {
            println(arr[x])
        }

    }

}