package org.fuckrkn1.android.api

import com.google.gson.annotations.SerializedName

data class ApiLocationConfig(
    @SerializedName("iface") val iface: IFace,
    @SerializedName("peer") val peer: Peer
) {
    data class IFace(
        @SerializedName("address") val address: String,
        @SerializedName("key") val key: String,
        @SerializedName("dns") val dns: String,
    )

    data class Peer(
        @SerializedName("pubkey") val pubkey: String,
        @SerializedName("allowed_ips") val allowedIps: String,
        @SerializedName("endpoint") val endpoint: String
    )
}
