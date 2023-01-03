package org.fuckrkn1.android.tunnel

import android.app.Application
import android.content.Context
import com.wireguard.android.backend.Backend
import com.wireguard.android.backend.GoBackend
import com.wireguard.android.backend.Tunnel
import com.wireguard.config.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.fuckrkn1.android.ui.MainToggleState

object TunnelManager {

    private lateinit var applicationContext: Context

    private val backend: Backend by lazy { GoBackend(applicationContext) }

    private val tunnel = LibertyTunnel()

    private val config: Config by lazy {
        Config.parse(applicationContext.assets.open("wgconfig"))
    }

    var stateChangeListener: OnStateChangeListener? = null
        set(value) {
            field = value
            value?.onStateChanged(backend.getState(tunnel).toMainToggleState())
        }

    suspend fun toggle() {
        withContext(Dispatchers.Main) {
            stateChangeListener?.onStateChanged(MainToggleState.IN_PROGRESS)
        }
        val newState = withContext(Dispatchers.IO) {
            backend.setState(tunnel, Tunnel.State.TOGGLE, config)
        }
        withContext(Dispatchers.Main) {
            stateChangeListener?.onStateChanged(newState.toMainToggleState())
        }
    }

    fun init(application: Application) {
        this.applicationContext = application.applicationContext
    }

    fun onTunnelUp(tunnelName: String) {
        if (tunnelName == tunnel.name) {
            stateChangeListener?.onStateChanged(MainToggleState.ACTIVE)
        }
    }

    fun onTunnelDown(tunnelName: String) {
        if (tunnelName == tunnel.name) {
            stateChangeListener?.onStateChanged(MainToggleState.INACTIVE)
        }
    }

    interface OnStateChangeListener {
        fun onStateChanged(state: MainToggleState)

    }

    private fun Tunnel.State.toMainToggleState(): MainToggleState =
        when (this) {
            Tunnel.State.DOWN -> MainToggleState.INACTIVE
            Tunnel.State.TOGGLE -> MainToggleState.IN_PROGRESS
            Tunnel.State.UP -> MainToggleState.ACTIVE
        }
}
