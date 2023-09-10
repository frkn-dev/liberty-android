package org.fuckrkn1.android.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.fuckrkn1.android.room.entity.WireGuardConfigDB

@Dao
interface WGConfigDao {
    @Query( "SELECT * FROM wireguard")
    suspend fun getSavedConfigs(): List<WireGuardConfigDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(config: WireGuardConfigDB)

    @Delete
    suspend fun delete(config: WireGuardConfigDB)
}