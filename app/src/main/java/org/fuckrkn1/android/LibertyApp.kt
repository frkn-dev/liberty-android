package org.fuckrkn1.android

import android.app.Application
import android.util.Log
import org.fuckrkn1.android.room.RoomManager
import org.fuckrkn1.android.tunnel.TunnelManager
import org.fuckrkn1.android.util.CryptoUtils

class LibertyApp : Application() {

    lateinit var cryptoUtils: CryptoUtils

    override fun onCreate() {
        super.onCreate()

        TunnelManager.init(this)
        RoomManager.init(this)

        cryptoUtilsInit()
    }

    private fun cryptoUtilsInit() {
        cryptoUtils = CryptoUtils()
    }
}