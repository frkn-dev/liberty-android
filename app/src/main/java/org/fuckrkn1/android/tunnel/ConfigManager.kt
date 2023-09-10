package org.fuckrkn1.android.tunnel

import android.util.Log
import com.wireguard.config.Config
import com.wireguard.config.Interface
import com.wireguard.config.Peer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.fuckrkn1.android.api.LibertyApi
import org.fuckrkn1.android.room.RoomManager
import org.fuckrkn1.android.room.entity.WireGuardConfigDB
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigManager {

    private val api = Retrofit.Builder()
        .baseUrl("https://api.fuckrkn1.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LibertyApi::class.java)

    // MARK: - WireGuard

    suspend fun getWireGuardConfig(): Config? = withContext(Dispatchers.IO) {

        val savedConfig = RoomManager.getSavedConfigForWG()

        if (savedConfig == null) {
            Log.d("ConfigManager", "Use network for getting new WG config")
            getWireGuardConfigFromServer()
        } else {
            Log.d("ConfigManager", "Use old WG config from database")
            getWireGuardConfigFromSavedData(savedConfig)
        }
    }

    private suspend fun getWireGuardConfigFromServer(): Config? = withContext(Dispatchers.IO) {

        val location = api.getLocations().filter{ it.code != "ru" }.randomOrNull() ?: return@withContext null

        val config = api.getPeer(location.code)
        RoomManager.saveConfigForWG(config)
        Config.Builder()
            .setInterface(
                Interface.Builder()
                    .parseAddresses(config.iface.address)
                    .parsePrivateKey(config.iface.key)
                    .parseDnsServers(config.iface.dns)
                    .build()
            )
            .addPeer(
                Peer.Builder()
                    .parsePublicKey(config.peer.pubkey)
                    .parseAllowedIPs(config.peer.allowedIps)
                    .parseEndpoint(config.peer.endpoint)
                    .build()
            )
            .build()
    }

    private suspend fun getWireGuardConfigFromSavedData(savedConfig: WireGuardConfigDB): Config?  = withContext(Dispatchers.IO) {

        Config.Builder()
            .setInterface(
                Interface.Builder()
                    .parseAddresses(savedConfig.address)
                    .parsePrivateKey(savedConfig.key)
                    .parseDnsServers(savedConfig.dns)
                    .build()
            )
            .addPeer(
                Peer.Builder()
                    .parsePublicKey(savedConfig.pubkey)
                    .parseAllowedIPs(savedConfig.allowedIps)
                    .parseEndpoint(savedConfig.endpoint)
                    .build()
            )
            .build()
    }
}