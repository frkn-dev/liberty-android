package org.fuckrkn1.android.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.fuckrkn1.android.room.dao.CountriesDAO
import org.fuckrkn1.android.room.dao.KeyPairDAO
import org.fuckrkn1.android.room.dao.WGConfigDao
import org.fuckrkn1.android.room.entity.CountryDB
import org.fuckrkn1.android.room.entity.KeyPairDB
import org.fuckrkn1.android.room.entity.WireGuardConfigDB

@Database(
    entities = [
        KeyPairDB::class,
        CountryDB::class,
        WireGuardConfigDB::class
    ],
    version = 3
)
abstract class FrknDatabase : RoomDatabase() {
    abstract fun keyPairDao(): KeyPairDAO
    abstract fun countriesDAO(): CountriesDAO
    abstract fun wgConfigDao(): WGConfigDao
}