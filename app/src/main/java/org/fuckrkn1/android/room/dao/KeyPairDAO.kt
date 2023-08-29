package org.fuckrkn1.android.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.fuckrkn1.android.room.entity.KeyPair

@Dao
interface KeyPairDAO {
    @Query( "SELECT * FROM keys")
    fun getAllKeyPairs(): List<KeyPair>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKeyPair(keyPair: KeyPair)

    @Delete
    fun deleteKeyPair(keyPair: KeyPair)
}