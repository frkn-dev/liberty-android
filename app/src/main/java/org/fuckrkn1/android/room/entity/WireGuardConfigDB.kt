package org.fuckrkn1.android.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.fuckrkn1.android.api.ApiLocationConfig
import java.lang.reflect.Constructor
import java.util.UUID

@Entity(tableName = "wireguard")
data class WireGuardConfigDB(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val address: String,
    val key: String,
    val dns: String,
    val pubkey: String,
    val allowedIps: String,
    val endpoint: String,
)