package org.fuckrkn1.android.data

import android.os.Build
import android.security.keystore.KeyProperties
import android.security.keystore.KeyProtection
import androidx.annotation.RequiresApi
import java.security.KeyStore
import java.util.Calendar
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


@RequiresApi(Build.VERSION_CODES.M)
class CryptoManager {
    private val validityOnYear = 2

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private fun createKey(keyName: String): SecretKey {
        val startValidity: Calendar = Calendar.getInstance()
        val endValidity: Calendar = Calendar.getInstance()
        endValidity.add(Calendar.YEAR, validityOnYear)

        val keyGen = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES)
        keyGen.init(256)
        val protectParam =
            KeyProtection.Builder(
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setKeyValidityStart(startValidity.time)
                .setKeyValidityEnd(endValidity.time)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setUserAuthenticationRequired(false)
                .setRandomizedEncryptionRequired(true)
                .build()

        val secretKey: SecretKey = keyGen.generateKey()
        val entry = KeyStore.SecretKeyEntry(secretKey)

        keyStore.setEntry(keyName, entry, protectParam)

        return secretKey
    }

    fun getKey(keyName: String): SecretKey {
        val existingKey =
            keyStore.getEntry(keyName, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey(keyName)
    }

}