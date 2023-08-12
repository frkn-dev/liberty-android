package org.fuckrkn1.android.util

import android.os.Build
import androidx.annotation.RequiresApi
import org.bouncycastle.crypto.AsymmetricCipherKeyPair
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator
import org.bouncycastle.crypto.params.AsymmetricKeyParameter
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory
import java.security.SecureRandom
import java.util.Base64

class CryptoUtils {

    // MARK: - Parameters

    private var keyPair: AsymmetricCipherKeyPair? = null

    @get:RequiresApi(Build.VERSION_CODES.O)
    val privateKey: String?
        get() { return keyToString(keyPair?.private) }

    @get:RequiresApi(Build.VERSION_CODES.O)
    val publicKey: String?
        get() { return keyToString(keyPair?.public) }

    // MARK: - Init

    init {
        generateKeyPair()
    }

    // MARK: - Generate method

    fun generateKeyPair() {
        val keyGen = X25519KeyPairGenerator()
        val secureRandom = SecureRandom()
        val keyGenerationParameters = X25519KeyGenerationParameters(secureRandom)

        keyGen.init(keyGenerationParameters)

        keyPair = keyGen.generateKeyPair()
    }

    // MARK: - Conversion methods

    fun keyToBytes(key: AsymmetricKeyParameter?): ByteArray? {
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun keyToString(key: AsymmetricKeyParameter?): String? {
        val keyBytes = keyToBytes(key) ?: return null
        return Base64.getEncoder().encodeToString(keyBytes)
    }
}