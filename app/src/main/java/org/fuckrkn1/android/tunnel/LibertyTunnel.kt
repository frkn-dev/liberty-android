package org.fuckrkn1.android.tunnel

import android.util.Log
import com.wireguard.android.backend.Tunnel

class LibertyTunnel : Tunnel {

    override fun getName(): String = "Liberty"

    override fun onStateChange(newState: Tunnel.State) {
        Log.d("LIBERTY", "LibertyTunnel.onStateChange $newState")
    }
}