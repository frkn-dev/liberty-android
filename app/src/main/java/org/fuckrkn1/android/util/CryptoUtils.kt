package org.fuckrkn1.android.util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator
import org.bouncycastle.crypto.params.AsymmetricKeyParameter
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory
import org.fuckrkn1.android.room.RoomManager
import java.security.SecureRandom
import java.util.Base64

class CryptoUtils {

    // MARK: - Coroutines

    private val scope =
        CoroutineScope(SupervisorJob() + Dispatchers.Default + CoroutineExceptionHandler { _, exception ->
            Log.e("CryptoUtils-CoroutineScope", "Caught $exception")
        })

    // MARK: - Parameters

    lateinit var privateKey: String
    lateinit var publicKey: String

    // MARK: - Init

    init {

        scope.launch {
            val savedKeyPair = RoomManager.getKeyPair()
            if (savedKeyPair == null) {
                generateKeyPair()
            } else {
                privateKey = savedKeyPair.privateKey
                publicKey  = savedKeyPair.publicKey
            }

            Log.d("privateKey", privateKey)
            Log.d("publicKey", publicKey)
        }
    }

    // MARK: - Generate method

    private fun generateKeyPair() {
        val keyGen = X25519KeyPairGenerator()
        val secureRandom = SecureRandom()
        val keyGenerationParameters = X25519KeyGenerationParameters(secureRandom)

        keyGen.init(keyGenerationParameters)

        val keyPair = keyGen.generateKeyPair()

        privateKey = keyToString(keyPair.private)
        publicKey  = keyToString(keyPair.public)

        scope.launch {
            RoomManager.saveKeyPair(privateKey, publicKey)
        }
    }

    // MARK: - Conversion methods

    private fun keyToString(key: AsymmetricKeyParameter?): String {
        val keyBytes = keyToBytes(key)
        return Base64.getEncoder().encodeToString(keyBytes)
    }

    private fun keyToBytes(key: AsymmetricKeyParameter?): ByteArray? {
        if (key == null) {
            return null
        }

        return if (key.isPrivate) {
            val privateKeyInfo = PrivateKeyInfoFactory.createPrivateKeyInfo(key)
            privateKeyInfo.getEncoded()
        } else {
            val publicKeyInfo = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(key)
            publicKeyInfo.getEncoded()
        }
    }
}