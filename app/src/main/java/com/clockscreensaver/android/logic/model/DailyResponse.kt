package com.clockscreensaver.android.logic.model

import java.util.Date

data class DailyResponse(val status: String,  val result: Result ) {
    data class Result(val daily: Daily)
    data class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>, val lifeIndex: LifeIndex)
    data class Temperature(val max: Float,val min: Float)
    data class Skycon(val value: String, val date: Date)
    data class LifeIndex(val ultraviolet: List<LifeDesc>, val carWashing: List<LifeDesc>, val coldRisk:List<LifeDesc>, val dressing:List<LifeDesc>)
    data class LifeDesc(val desc: String)
}
