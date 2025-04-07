package com.example.clockscreensaver

import com.example.clockscreensaver.ui.Util

fun main() {
    val phoneOne = Cellphone("xiaomi", 1299.22)
    val phoneTwo = Cellphone("xiaomi", 1299.22)
//    println(phoneOne == phoneTwo)
    println(Singleton.getNum())

    Singleton.singletonTest()
    Singleton.changeNumber()
    println(Singleton.getNum())


    Util.tall()
}
