package com.example.clockscreensaver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clockscreensaver.databinding.MainActivityBinding
import com.example.clockscreensaver.ui.theme.ClockScreenSaverTheme

class MainActivity : ComponentActivity() {
    private var tag: String? = "MainActivity"
    private lateinit var binding: MainActivityBinding

    lateinit var timeChangeReceiver: TimeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = MainActivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        让应用的内容扩展到屏幕边缘，充分利用屏幕空间
        enableEdgeToEdge()
////        设置 Compose 的 UI 内容（Compose 是传统 Android View 系统的替代品）
        setContent {
            ClockScreenSaverTheme {
                // Scaffold：提供一种规范的应用布局结构，支持顶部栏、底部栏、浮动按钮等组件。在这里，它用于填充整个屏幕，并将 innerPadding 传递给内容
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(getString(R.string.app_name), Modifier.padding(innerPadding))

                    Button(
                        onClick = {
                            // 这里是点击事件的处理逻辑
                            val intent = Intent("com.example.clockscreensaver.CUSTOM_BROADCAST")
                            intent.setPackage(packageName)
                            sendBroadcast(intent)
                        }

                    ) {
                        Text(
                            text = "发送广播",
                            modifier = Modifier.padding(innerPadding)
                        ) // 按钮文字

                    }
//                    Counter(Modifier.padding(innerPadding))
                }
            }
        }

        Log.v(tag, "onCreate end")
        Log.d(tag, "onCreate end")
        Log.i(tag, "onCreate end")
        Log.w(tag, "onCreate end")
        Log.e(tag, "onCreate end")

        /* 广播学习 */
        leanBroadcast();


       // 触发自定义广播
//        binding.sendBroadcastButton.setOnClickListener {
////            val intent = Intent("com.example.clockscreensaver.CUSTOM_BROADCAST")
////            intent.setPackage(packageName)
////            sendBroadcast(intent)
////        }
    }

    private fun leanBroadcast() {
        val intentFilter = IntentFilter()
        // 监听系统时间改变广播
        // 想要监听什么广播，就在这里添加相应的action， 在android 8.0+ 这个广播被禁止了
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        // 广播需要移除
        unregisterReceiver(timeChangeReceiver)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {
        // 当有广播到来时，onReceive()方法就会得到执行
        // 不要在onReceive()方法中添加过多的逻辑或者进行任何的耗时操作，因为BroadcastReceiver中是不允许开启线程的，当onReceive()方法运行了较长时间而没有结束时，程序就会出现错误
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(p0, "time is changed", Toast.LENGTH_SHORT).show()
            Log.e(tag, "time is changed")

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {
            // 这里是点击事件的处理逻辑
            println("按钮被点击了!")
        }

    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        ) // 按钮文字

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClockScreenSaverTheme {
        Greeting("Android")
    }
    ClockScreenSaverTheme {
        Counter()
    }
}



@Composable
fun Counter(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Button(onClick = { count++ }) {
        Text(
            text = "Click count: $count",
            modifier = modifier
        )

    }
}
