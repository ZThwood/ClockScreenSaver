package com.clockscreensaver.learn

object Singleton {
    fun singletonTest() {
        println("singletonTest end")
    }
    private var num = 0

    fun changeNumber() {
        num++
    }

    fun getNum() = num

}