package com.clockscreensaver.android.ui.wearther

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clockscreensaver.android.logic.model.Weather
import com.clockscreensaver.android.logic.model.getSky
import com.example.clockscreensaver.R
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather)

//        val decorView = window.decorView
//        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.statusBarColor = Color.Transparent
//        setContentView(R.layout.activity_weather)

        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }

        if (viewModel.locationLat.isEmpty()) {
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }

        if(viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }

        viewModel.weatherLiveData.observe(this, Observer { result ->
            val weather = result.getOrNull()
            if(weather != null) {
                showWeatherInfo(weather as Weather)
            } else {
                Toast.makeText(this, "无法成功获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        viewModel.refreshWeather("${viewModel.locationLng}, ${viewModel.locationLat}")

    }

    private fun showWeatherInfo(weather: Weather) {
        val placeName = this.findViewById<TextView>(R.id.placeName)
        placeName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily
        val currentTempText = "${realtime.temperature.toInt()} ℃"
        val currentTemp = findViewById<TextView>(R.id.currentTemp)
        currentTemp.text = currentTempText
        val currentSky = findViewById<TextView>(R.id.currentSky)
        currentSky.text = getSky(realtime.skycon).info
        val currentPM25Text = "空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
        val currentAQI = findViewById<TextView>(R.id.currentAQI)
        currentAQI.text = currentPM25Text
        val nowLayout = findViewById<RelativeLayout>(R.id.nowLayout)
        nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)

        val forecastLayout = findViewById<LinearLayout>(R.id.forecastLayout)
        forecastLayout.removeAllViews()

        val days = daily.skycon.size
        for (i in 0 until days) {
            val skycon = daily.skycon[i]
            val temp = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false)
            val dateInfo = view.findViewById<TextView>(R.id.dateInfo)
            val skyIcon = view.findViewById<ImageView>(R.id.skyIcon)
            val skyInfo = view.findViewById<TextView>(R.id.skyInfo)
            val temperatureInfo = view.findViewById<TextView>(R.id.temperatureInfo)
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text=sky.info
            val tempText ="${temp.min.toInt()} ~ ${temp.min.toInt()} ℃"
            temperatureInfo.text = tempText
            forecastLayout.addView(view)
        }

        val lifeIndex = daily.lifeIndex
        val coldRiskText = findViewById<TextView>(R.id.coldRiskText)
        coldRiskText.text = lifeIndex.coldRisk[0].desc
        val dressingText = findViewById<TextView>(R.id.dressingText)
        dressingText.text = lifeIndex.dressing[0].desc

        val ultravioletText = findViewById<TextView>(R.id.ultravioletText)
        ultravioletText.text = lifeIndex.ultraviolet[0].desc

        val carWashingText = findViewById<TextView>(R.id.carWashingText)
        carWashingText.text = lifeIndex.carWashing[0].desc

       val weatherLayout = findViewById<ScrollView>(R.id.weatherLayout)
        weatherLayout.visibility = View.VISIBLE

    }
}