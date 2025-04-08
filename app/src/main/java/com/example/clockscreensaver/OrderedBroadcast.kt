package com.example.clockscreensaver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class OrderedBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "received in OrderedBroadcastReceiver",Toast.LENGTH_LONG).show()
    }
}