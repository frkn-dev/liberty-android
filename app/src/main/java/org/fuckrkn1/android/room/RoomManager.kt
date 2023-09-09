package org.fuckrkn1.android.room

import android.app.Application
import android.content.Context
import androidx.room.Room
import org.fuckrkn1.android.room.entity.KeyPair

object RoomManager {

    // MARK: - Properties

    private lateinit var applicationContext: Context
    private lateinit var db: FrknDatabase

    // MARK: - Init

    fun init(application: Application) {
        this.applicationContext = application.applicationContext

        databaseInit()
    }

    private fun databaseInit() {

        db = Room.databaseBuilder(
            applicationContext,
            FrknDatabase::class.java, "frkn-db"
        ).fallbackToDestructiveMigration().build()
    }

    // MARK: - KeyPair methods

    suspend fun getKeyPair(): KeyPair? {

        val dao = db.keyPairDao()
        val keyPair = dao.getAllKeyPairs().firstOrNull()
        return keyPair
    }

    suspend fun saveKeyPair(privateKey: String, publicKey: String) {

        val dao = db.keyPairDao()
        val keyPair = KeyPair(privateKey = privateKey, publicKey = publicKey)

        dao.insertKeyPair(keyPair)
    }

    suspend fun delete(keyPair: KeyPair) {

        val dao = db.keyPairDao()
        dao.deleteKeyPair(keyPair)
    }
}