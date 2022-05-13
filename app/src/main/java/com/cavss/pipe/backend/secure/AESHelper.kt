package com.cavss.pipe.backend.secure

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyInfo
import android.security.keystore.KeyProperties
import android.util.Log
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import java.security.spec.InvalidKeySpecException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


object AESHelper {
    /** 키를 외부에 저장할 경우 유출 위험이 있으니까 소스 코드 내에 숨겨둔다. 길이는 16자여야 한다. */

    private val ALLIAS = "AsgardHeimdallr" // 키스토어 에서 사용할 별칭
    private var keyGenerator: KeyGenerator? = null
    private var keyGenParameterSpec: KeyGenParameterSpec? = null
    private const val AndroidKeyStore = "AndroidKeyStore"

    fun keySetting(){
        try {
            // AndroidKeyStore를 정확히 입력해야 키 스토어에 접근합니다.
            val keyStore = KeyStore.getInstance(AndroidKeyStore)
            keyStore.load(null)

            if (!keyStore.containsAlias(ALLIAS)) { // 지정된 별칭으로 키 미 생성 시 새롭게 키 생성
                // 다음은 생성할 키 알고리즘입니다.
                keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, AndroidKeyStore)

                // 첫 번째, 인자는 별칭  두 번째, 인자는 key사용목적 암호화&복호화가 목적입니다.
                keyGenParameterSpec = KeyGenParameterSpec.Builder(
                    ALLIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM) // 세 번째, 인자는 운용할 블록모드
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE) //네 번째, 인자는 사용할 패딩 값
                    .setRandomizedEncryptionRequired(true) //KeyStore에 매번 새로운 IV를 사용
//                    .setUserAuthenticationRequired(true) -> 잠금화면 설정. 화면 잠금 요구사항을 활성화할때 사용자가 잠금화면, pin, 암호를 제거하거나 변경하면 키가 바로 취소된다.
//                    .setUserAuthenticationValidityDurationSeconds(120) -> 장치 인증 후 120초 동안 키를 사용할 수 있다. 키에 엑세스 할 때마다 지문 인증이 필요하도록 -1을 전달한다.
                    .build() // 초기화 완료

                keyGenerator!!.init(keyGenParameterSpec) // 키 생성을 위해 정의한 keyGenParameterSpec을 로드
                keyGenerator!!.generateKey() // 대칭 키 생성
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("mException","AESHelper, keySetting // NoSuchAlgorithmException : ${e.message}")
        } catch (e: NoSuchProviderException) {
            Log.e("mException","AESHelper, keySetting // NoSuchProviderException : ${e.message}")
        } catch (e: KeyStoreException) {
            Log.e("mException","AESHelper, keySetting // KeyStoreException : ${e.message}")
        } catch (e: CertificateException) {
            Log.e("mException","AESHelper, keySetting // CertificateException : ${e.message}")
        } catch (e: IOException) {
            Log.e("mException","AESHelper, keySetting // IOException : ${e.message}")
        } catch (e: InvalidAlgorithmParameterException) {
            Log.e("mException","AESHelper, keySetting // InvalidAlgorithmParameterException : ${e.message}")
        }
    }

    /*
   키스토어 암호화할때 map으로 return했기 떄문에 파라미터는 HashMap으로 해야한다.
   return 은 원래 String(kenyEncrypt파라미터는 String을 받아서 ByteArray로 변환했다.)값으로
   받도록 ByteArray로 해야한다.
   */
    fun keystoreEncrypt(dataToEncrypt : ByteArray?) : HashMap<String, ByteArray>{
        val map = HashMap<String, ByteArray>()
        try {
            //Get the key
            val keyStore = KeyStore.getInstance(AndroidKeyStore)
            keyStore.load(null)

            val secretKeyEntry = keyStore.getEntry(ALLIAS, null) as KeyStore.SecretKeyEntry
            val secretKey = secretKeyEntry.secretKey

            //Encrypt data
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            val ivBytes = cipher.iv
            val encryptedBytes = cipher.doFinal(dataToEncrypt)

            map["iv"] = ivBytes
            map["encrypted"] = encryptedBytes
        }catch (e: Exception){
            Log.e("mException","AESHelper, keystoreEncrypt // Exception : ${e.message}")
        }finally {
            return map
        }
    }

    fun keystoreDecrypt(map: HashMap<String, ByteArray>?) : ByteArray?{
        var decrypted: ByteArray? = null
        try {
            // 1
            //Get the key
            val keyStore : KeyStore = KeyStore.getInstance(AndroidKeyStore)
            keyStore.load(null)

            val secretKeyEntry : KeyStore.SecretKeyEntry = keyStore.getEntry(ALLIAS, null) as KeyStore.SecretKeyEntry
            val secretKey : SecretKey = secretKeyEntry.secretKey

            // 2
            //Extract info from map
            val encryptedBytes : ByteArray? = map!!["encrypted"]
            val ivBytes : ByteArray? = map["iv"]

            if (encryptedBytes == null || ivBytes == null){
                decrypted = null
            }else{
                // 3
                //Decrypt data
                val cipher : Cipher = Cipher.getInstance("AES/GCM/NoPadding")
                val spec = GCMParameterSpec(128, ivBytes)
                cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)

                cipher.doFinal(encryptedBytes)?.let {
                    decrypted = it
                }
            }
        } catch (e: KotlinNullPointerException) {
            Log.e("mException","AESHelper, keystoreDecrypt // KotlinNullPointerException : ${e.message}")
        } catch (e: Exception) {
            Log.e("mException","AESHelper, keystoreDecrypt // Exception : ${e.message}")
        } catch (e: Throwable) {
            Log.e("mException","AESHelper, keystoreDecrypt // Throwable : ${e.message}")
        }finally {
            return decrypted!!
        }
    }



    fun encrypt(dataToEncrypt: ByteArray, password: CharArray): HashMap<String, ByteArray> {
        val map = HashMap<String, ByteArray>()
        try{
            val random = SecureRandom()
            val salt = ByteArray(256)
            random.nextBytes(salt)

            val pbKeySpec = PBEKeySpec(password, salt, 1324, 256) //1
            val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1") // 2
            val keyBytes = secretKeyFactory.generateSecret(pbKeySpec).encoded // 3
            val keySpec = SecretKeySpec(keyBytes, "AES") // 4

            val ivRandom = SecureRandom()
            val iv = ByteArray(16)
            ivRandom.nextBytes(iv)
            val ivSpec = IvParameterSpec(iv)

            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding") // 1
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
            val encrypted = cipher.doFinal(dataToEncrypt) // 2


            map["salt"] = salt
            map["iv"] = iv
            map["encrypted"] = encrypted
        }catch (e : Exception){
            Log.d("mException", "암호화중 Exception : ${e.message}")
        }catch (e : GeneralSecurityException){
            // 특정 국가 혹은 저사양 기기에서는 알고리즘이 지원하지 않을 수 있음. 특히 중국 인도 대상기기
            Log.d("mException", "암호화중 GeneralSecurityException : ${e.message}")
        }catch (e : SecurityException){
            Log.d("mException", "암호화중 SecurityException : ${e.message}")
        }catch (e : InvalidKeyException){
            Log.d("mException", "암호화중 키값의 길이가 부족 : ${e.message}")
        }finally {
            return map
        }
    }
    fun decrypt(map: HashMap<String, ByteArray>, password: CharArray) : ByteArray?{
        var decrypted: ByteArray? = null
        try{
            val salt = map["salt"]
            val iv = map["iv"]
            val encrypted = map["encrypted"]

            val pbKeySpec = PBEKeySpec(password, salt, 1324, 256)
            val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            val keyBytes = secretKeyFactory.generateSecret(pbKeySpec).encoded
            val keySpec = SecretKeySpec(keyBytes, "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            val ivSpec = IvParameterSpec(iv)
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
            decrypted = cipher.doFinal(encrypted)
        }catch (e : Exception){
            Log.d("mException", "복호화중 에러발생 : ${e.message}")
        }catch (e : GeneralSecurityException){
            // 특정 국가 혹은 저사양 기기에서는 알고리즘이 지원하지 않을 수 있음. 특히 중국 인도 대상기기
            Log.d("mException", "복호화중 GeneralSecurityException : ${e.message}")
        }catch (e : SecurityException){
            Log.d("mException", "복호화중 SecurityException : ${e.message}")
        }finally {
            return decrypted
        }
    }
    fun keystoreTest(testMessage : String,
                     afterFunc : (decrypted : String) -> Unit,
                     isInsideSecureHardware : (isInsideSecureHardware : Boolean) -> Unit) {
        try {
            //Get the key
            val keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore.load(null)


            val privateKeyEnrty = keyStore.getKey("AsgardHeimdallr", null)
            val privKey = privateKeyEnrty
            val factory = KeyFactory.getInstance(privKey.algorithm, "AndroidKeyStore")
            val keyInfo: KeyInfo
            try {
                keyInfo = factory.getKeySpec(privKey, KeyInfo::class.java)
                isInsideSecureHardware(keyInfo.isInsideSecureHardware)
            } catch (e: InvalidKeySpecException) {
                // Not an Android KeyStore key.
                Log.e("mException","AESHelper, keystoreTest // InvalidKeySpecException : ${e.message}")
            }

            val map = keystoreEncrypt(testMessage.toByteArray(Charsets.UTF_8))
            val decryptedData = keystoreDecrypt(map)
            decryptedData?.let{
                val decryptedString = String(it, Charsets.UTF_8)
                afterFunc(decryptedString)
            }
        }catch (e: Exception){
            Log.e("mException","AESHelper, keystoreTest // Exception : ${e.message}")
        }catch (e: InvalidKeySpecException) {
            Log.e("mException","AESHelper, keystoreTest, InvalidKeySpecException(키를 못가져옴) // InvalidKeySpecException : ${e.message}")
        }
    }
}