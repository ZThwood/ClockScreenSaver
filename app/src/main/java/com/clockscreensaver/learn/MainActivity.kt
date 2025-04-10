package com.clockscreensaver.learn

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import com.example.clockscreensaver.R
import com.example.clockscreensaver.databinding.MainActivityBinding
import com.clockscreensaver.android.ui.theme.ClockScreenSaverTheme

class MainActivity : BaseActivity() {
    private var tag: String? = "MainActivity"
    private lateinit var binding: MainActivityBinding

    lateinit var timeChangeReceiver: TimeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        让应用的内容扩展到屏幕边缘，充分利用屏幕空间
//        enableEdgeToEdge()
////        设置 Compose 的 UI 内容（Compose 是传统 Android View 系统的替代品）
//        setContent {
//            ClockScreenSaverTheme {
//                // Scaffold：提供一种规范的应用布局结构，支持顶部栏、底部栏、浮动按钮等组件。在这里，它用于填充整个屏幕，并将 innerPadding 传递给内容
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    Greeting(getString(R.string.app_name), Modifier.padding(innerPadding))
//
//                    Button(
//                        onClick = {
//                            // 标准自定义广播
////                            sendCustomBroadcastClick()
//                            sendOrderBroadcastClick()
//                        }
//
//                    ) {
//                        Text(
//                            text = "发送广播",
//                            modifier = Modifier.padding(innerPadding)
//                        ) // 按钮文字
//
//                    }
////                    Counter(Modifier.padding(innerPadding))
//                }
//            }
//        }

        Log.v(tag, "onCreate end")
        Log.d(tag, "onCreate end")
        Log.i(tag, "onCreate end")
        Log.w(tag, "onCreate end")
        Log.e(tag, "onCreate end")

        /* 广播学习 */
        leanBroadcast();


       // 触发自定义广播
        binding.sendBroadcastButton.setOnClickListener {
            // 标准自定义广播
//           sendCustomBroadcastClick()
            sendOrderBroadcastClick()
        }

        binding.forceClose.setOnClickListener {
            val intent = Intent("com.example.clockscreensaver.FORCE_OFFLINE")
//            intent.setPackage(packageName)
            Toast.makeText(this, "forceClose", Toast.LENGTH_SHORT).show()

            sendBroadcast(intent)
        }


        // 发送普通通知用 normal
        val noticeManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // NotificationManager.IMPORTANCE_HIGH  表示通知等级高
//            val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            val channel = NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)

            noticeManager.createNotificationChannel(channel)
        }
        binding.sendNotice.setOnClickListener {
            // 决定点击通知跳转页面
            val intent = Intent(this, LoginActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


          val notification = NotificationCompat.Builder(this, "important")
              .setContentTitle("通知标题")
              .setContentText("通知内容2")
              .setSmallIcon(R.drawable.ic_launcher)
              .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_round))
              .setContentIntent(pi)
              .setAutoCancel(true) // 点击通知是否取消
              .build()

            // 通知id 和 通知对象
            noticeManager.notify(1, notification)
        }

    }

    private fun sendCustomBroadcastClick() {
        // [自定义广播] 发送广播
        val intent = Intent("com.example.clockscreensaver.CUSTOM_BROADCAST")
        // 自定义广播默认是隐式广播。因此这里一定要调用setPackage()方法。让自定义广播变为显性广播
        intent.setPackage(packageName)
        sendBroadcast(intent)
    }

    private fun sendOrderBroadcastClick() {
        // [有序广播] 发送广播
        val intent = Intent("com.example.clockscreensaver.CUSTOM_BROADCAST")
        // 自定义广播默认是隐式广播。因此这里一定要调用setPackage()方法。让自定义广播变为显性广播
        intent.setPackage(packageName)
        sendOrderedBroadcast(intent, null)
    }

    private fun leanBroadcast() {
        val intentFilter = IntentFilter()
        // 监听系统时间改变广播
        // 想要监听什么广播，就在这里添加相应的action
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
