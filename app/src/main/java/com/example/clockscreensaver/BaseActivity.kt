package com.example.clockscreensaver

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {
    private lateinit var receiver: ForceOfflineBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.clockscreensaver.FORCE_OFFLINE")
        receiver = ForceOfflineBroadcastReceiver()
        registerReceiver(receiver, intentFilter, Context.RECEIVER_NOT_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class ForceOfflineBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, p1: Intent?) {
            AlertDialog.Builder(context).apply {
                setTitle("Warning")
                setMessage("你已经被强制下线,请重新登录")
                setCancelable(false)
                setPositiveButton("OK") {
                    _, i ->
                    ActivityCollector.finishAll()
                    // 重新启动登录页
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
                show()
            }
        }
    }
}
