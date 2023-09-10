package org.fuckrkn1.android.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.fuckrkn1.android.room.dao.KeyPairDAO
import org.fuckrkn1.android.room.dao.WGConfigDao
import org.fuckrkn1.android.room.entity.KeyPairDB
import org.fuckrkn1.android.room.entity.WireGuardConfigDB

@Database(
    entities = [
        KeyPairDB::class,
        WireGuardConfigDB::class
    ],
    version = 2
)
abstract class FrknDatabase : RoomDatabase() {
    abstract fun keyPairDao(): KeyPairDAO
    abstract fun wgConfigDao(): WGConfigDao
}