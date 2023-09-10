package org.fuckrkn1.android.room

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.wireguard.config.Config
import org.fuckrkn1.android.api.ApiLocationConfig
import org.fuckrkn1.android.room.entity.CountryDB
import org.fuckrkn1.android.room.entity.KeyPairDB
import org.fuckrkn1.android.room.entity.WireGuardConfigDB

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

    suspend fun getKeyPair(): KeyPairDB? {

        val dao = db.keyPairDao()
        val keyPair = dao.getAllKeyPairs().firstOrNull()
        return keyPair
    }

    suspend fun saveKeyPair(privateKey: String, publicKey: String) {

        val dao = db.keyPairDao()
        val keyPair = KeyPairDB(privateKey = privateKey, publicKey = publicKey)

        dao.insert(keyPair)
    }

    suspend fun delete(keyPair: KeyPairDB) {

        val dao = db.keyPairDao()
        dao.delete(keyPair)
    }

    // MARK: - Countries

    suspend fun getAllCountries(): List<CountryDB> {

        val dao = db.countriesDAO()
        val countries = dao.getAllCountries()
        return countries
    }

    suspend fun insert(newCountries: List<CountryDB>) {

        val dao = db.countriesDAO()
        val savedCountries = dao.getAllCountries()
        val allCountries = savedCountries + newCountries
        val countries = allCountries.groupBy { it.code }
            .filter { it.value.size == 1 }
            .flatMap { it.value }

        countries.forEach { dao.insert(it) }
    }

    suspend fun delete(country: CountryDB) {

        val dao = db.countriesDAO()
        dao.delete(country)
    }

    // MARK: - WG config

    suspend fun getSavedConfigForWG(): WireGuardConfigDB? {

        val dao = db.wgConfigDao()
        val config = dao.getSavedConfigs().firstOrNull()
        return config
    }

    suspend fun saveConfigForWG(config: ApiLocationConfig) {

        val dao = db.wgConfigDao()

        val wgConfigForDB = WireGuardConfigDB(
            address = config.iface.address,
            key = config.iface.key,
            dns = config.iface.dns,
            pubkey = config.peer.pubkey,
            allowedIps = config.peer.allowedIps,
            endpoint = config.peer.endpoint
        )

        dao.save(wgConfigForDB)
    }

    suspend fun deleteLastSavedConfigForWG(config: WireGuardConfigDB) {

        val dao = db.wgConfigDao()
        dao.delete(config)
    }
}