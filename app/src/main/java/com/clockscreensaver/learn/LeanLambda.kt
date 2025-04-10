package com.clockscreensaver.learn

fun main() {

    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape", "Watermelon")
    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
//    println("anyResult is " + anyResult + ", allResult is " + allResult)

    myName()
    myName("sanmusen")

    val res = with(list) {
        var res = "begin eat fruit!"
        for (item in list) {
            res = "$res$item,"
        }
        res = "$res eat end!"

        res
    }

    println(res)
}

fun myName(name:String = "xiaomi") {
    println("my name is $name")
}
