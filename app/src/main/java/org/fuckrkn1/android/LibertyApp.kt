package org.fuckrkn1.android

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.fuckrkn1.android.tunnel.TunnelManager
import org.fuckrkn1.android.util.CryptoUtils

class LibertyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        TunnelManager.init(this)
        val cryptoUtils = CryptoUtils()

        Log.d("privateKey", cryptoUtils.privateKey.toString())
        Log.d("publicKey", cryptoUtils.publicKey.toString())
    }
}