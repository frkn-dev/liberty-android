package org.fuckrkn1.android.tunnel

import android.app.Application
import android.content.Context
import com.wireguard.android.backend.Backend
import com.wireguard.android.backend.GoBackend
import com.wireguard.android.backend.Tunnel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.fuckrkn1.android.ui.MainToggleState

object TunnelManager {

    private lateinit var applicationContext: Context

    private val backend: Backend by lazy { GoBackend(applicationContext) }

    private val tunnel = LibertyTunnel()

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
            when (val currentState = backend.getState(tunnel)) {
                Tunnel.State.DOWN -> {
                    val config = ConfigManager.getCurrentConfig() // TODO error
                    if (config == null) {
                        Tunnel.State.DOWN
                    } else {
                        backend.setState(tunnel, Tunnel.State.UP, config)
                    }
                }
                Tunnel.State.TOGGLE -> currentState
                Tunnel.State.UP -> {
                    backend.setState(tunnel, Tunnel.State.DOWN, null)
                }
            }
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
