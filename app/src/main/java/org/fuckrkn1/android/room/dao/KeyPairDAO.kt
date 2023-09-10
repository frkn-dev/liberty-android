package org.fuckrkn1.android.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.fuckrkn1.android.room.entity.KeyPairDB

@Dao
interface KeyPairDAO {
    @Query( "SELECT * FROM keys")
    suspend fun getAllKeyPairs(): List<KeyPairDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keyPair: KeyPairDB)

    @Delete
    suspend fun delete(keyPair: KeyPairDB)
}