package com.clockscreensaver.learn

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
//[静态广播]
class DeviceRebootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context, "Device Reboot", Toast.LENGTH_SHORT).show()
    }
}