package org.fuckrkn1.android.tunnel

import com.wireguard.config.Config
import com.wireguard.config.Interface
import com.wireguard.config.Peer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.fuckrkn1.android.api.LibertyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigManager {

    private val api = Retrofit.Builder()
        .baseUrl("https://api.fuckrkn1.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LibertyApi::class.java)

    suspend fun getCurrentConfig(): Config? = withContext(Dispatchers.IO) {
        val location = api.getLocations().first()
        val config = api.getPeer(location.code)
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
}