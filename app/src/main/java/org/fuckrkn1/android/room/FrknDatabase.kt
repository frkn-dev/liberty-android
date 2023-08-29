package org.fuckrkn1.android.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.fuckrkn1.android.room.dao.KeyPairDAO
import org.fuckrkn1.android.room.entity.KeyPair

@Database(entities = [KeyPair::class], version = 1)
abstract class FrknDatabase : RoomDatabase() {
    abstract fun keyPairDao(): KeyPairDAO
}