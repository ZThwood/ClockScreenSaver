package com.clockscreensaver.learn

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Receive custom broadcast", Toast.LENGTH_SHORT).show()
//        拦截后续广播
        abortBroadcast()
    }
}