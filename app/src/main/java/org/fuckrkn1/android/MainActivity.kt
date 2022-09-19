package org.fuckrkn1.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import org.fuckrkn1.android.ui.LibertyApp
import org.fuckrkn1.android.ui.MainToggleState

class MainActivity : ComponentActivity() {

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

    private fun toggleState() {
        state.value = when (state.value) {
            MainToggleState.ACTIVE -> MainToggleState.INACTIVE
            MainToggleState.INACTIVE -> MainToggleState.IN_PROGRESS
            MainToggleState.IN_PROGRESS -> MainToggleState.ACTIVE
        }
    }
}