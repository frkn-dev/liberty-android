package org.fuckrkn1.android.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "keys")
data class KeyPair(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val privateKey: String,
    val publicKey: String
)