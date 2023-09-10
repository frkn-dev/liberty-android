package org.fuckrkn1.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.lifecycleScope
import com.wireguard.android.backend.GoBackend
import kotlinx.coroutines.launch
import org.fuckrkn1.android.tunnel.TunnelManager
import org.fuckrkn1.android.ui.LibertyApp
import org.fuckrkn1.android.ui.MainToggleState

class MainActivity : ComponentActivity(), TunnelManager.OnStateChangeListener {

    private val permissionActivityResultLauncher = registerForActivityResult(StartActivityForResult()) {
        toggleState()
    }

    private val state = mutableStateOf(MainToggleState.INACTIVE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LibertyApp(
                mainToggleState = state.value,
                onToggleClick = {
                    toggleState()
                }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        TunnelManager.stateChangeListener = this
    }

    override fun onStop() {
        TunnelManager.stateChangeListener = null
        super.onStop()
    }

    override fun onStateChanged(state: MainToggleState) {
        this.state.value = state
    }

    private fun toggleState() {
        val intent = GoBackend.VpnService.prepare(this)
        if (intent == null) {
            lifecycleScope.launch {
                TunnelManager.toggle()
            }
        } else {
            permissionActivityResultLauncher.launch(intent)
        }
    }
}