package com.clockscreensaver.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
// 全局变量定义在这里 比如 context
class ClockScreenSaverApplication: Application() {

    // companion是实现静态关键字
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val WEATHER_TOKEN = "3npBQKVLj1Ia63ej"
        const val A_MAP_TOKEN = "e0ebe953599c22bb7abaa746c17d0a69"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}