package org.fuckrkn1.android.tunnel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TunnelBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val tunnelName = intent?.getStringExtra("tunnel") ?: return
        when (intent.action) {
            "com.wireguard.android.action.SET_TUNNEL_UP" -> TunnelManager.onTunnelUp(tunnelName)
            "com.wireguard.android.action.SET_TUNNEL_DOWN" -> TunnelManager.onTunnelDown(tunnelName)
        }
    }
}