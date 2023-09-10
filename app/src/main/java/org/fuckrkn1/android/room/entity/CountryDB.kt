package org.fuckrkn1.android.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "countries")
data class CountryDB(
    @PrimaryKey val code: String,
    val name: String
)