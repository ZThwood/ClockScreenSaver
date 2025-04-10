package com.clockscreensaver.learn

import kotlin.math.max
import kotlin.math.min

fun main() {
    println("Hello Kotlin")
    // 变量var 常量val
    var a = 10
    a = a * 10
    println("now a is " + a)
    val max = getLargestNumber(10, 20)
    println("max num is " + max)
    val minNum = getMinNumber(10, 20)
    println("min num is " + minNum)

    // 逻辑控制: 顺序语句、条件语句（if, when）、循环语句
    val maxNum = getMaxNumber(22, 45)
    println("who max?" + maxNum)

    val tomScore = getScore("Tom")
    println("so, Tom score is " + tomScore)

//    println(checkNumberType(23))
//    println(checkNumberType(2.2))
//
//    println(checkNumberType(2.2f))
//    println(checkNumberType(222222222222222222))
    whileNumber(10)
    forRange()

    val nana = Student("nana", 3, "312", 123)
//    println("nana readBook？ " + nana.readBook())

    val li = Teacher("li", 22)
    println("How old is Teacher Li?" + li.age)

//    doStudy(nana)
}

// 面向接口编程，多态
fun doStudy(study: Study) {
    study.readBook()
    study.doHomework()
}


// 函数
fun getLargestNumber(num1: Int, num2: Int): Int {
    return max(num1, num2)
}

// function 语法糖
fun getMinNumber(num1: Int, num2: Int) = min(num1, num2)

// if
fun getMaxNumber(n:Int, n2:Int):Int {
   return if (n > n2) {
        n
    } else {
        n2
    }
}

// when
fun getScore(name: String) = when {
    name == "Tom" -> 88
    name == "Alex" -> 59
    // 作为返回值时必须 else 结尾
    else -> 0
}

// when type check
// is关键字就是类型匹配的核心，它相当于Java中的instanceof关键字
fun checkNumberType(type: Number){
 when(type) {
        is Int -> println("is Int type")
        is Double -> println("is Double type")
        is Long -> println("is Long type")
        is Float -> println("is Float type")
        else -> println("type not support")
    }
}

fun whileNumber(number: Int) {
    var target = number
    while (target > 0) {
        println("while" + target)
        target--
    }
}

fun forRange() {
    val range = 0..10;
    for (i in range step 2) {
        println("for " + i)
    }
}

open class Person(val name: String, val age: Int) {
}

//class Student(val sno: String, val grade: Int, name:String, age:Int): Person(name, age) {
//    // 次构造函数
//    constructor(name: String, age: Int): this("", 0, name, age) {
//
//    }
//    // 第二个次构造函数
//    constructor(): this("", 0) {}
//}

// 只有次构造函数, Teacher 没有加括号，说明没有主构造函数
class Teacher : Person {
    // 没有主构造函数，次构造函数只能直接调用父类的构造函数, 用 super 关键字
    constructor(name: String, age: Int): super("", 20) {

    }
}

//class Student(name: String, age: Int): Person(name, age), Study {
//    override fun readBook() {
//        println(name + " readBook")
//    }
//}

class Student(val sno: String = "", val grade: Int = 0, name:String="", age:Int = 0): Person(name, age) {

}



