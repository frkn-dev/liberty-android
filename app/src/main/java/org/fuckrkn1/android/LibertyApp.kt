package org.fuckrkn1.android

import android.app.Application
import org.fuckrkn1.android.tunnel.TunnelManager

class LibertyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        TunnelManager.init(this)
    }
}