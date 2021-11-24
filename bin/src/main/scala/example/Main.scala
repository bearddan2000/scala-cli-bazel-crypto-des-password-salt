package example;

import java.io._;
import java.util._;
import java.security._;
import javax.crypto._;
import javax.crypto.spec._;
import org.apache.commons.codec.binary.Base64;

object Main {
  var encryptCipher: Cipher = null;
  var decryptCipher: Cipher = null;

/**
 * Construct a new object which can be utilized to encrypt
 * and decrypt strings using the specified key
 * with a DES encryption algorithm.
 *
 * @param key The secret key used in the crypto operations.
 * @throws Exception If an error occurs.
 *
 */
@throws(classOf[Exception])
def genSecret(key: SecretKey): Unit = {
    encryptCipher = Cipher.getInstance("DES");
    decryptCipher = Cipher.getInstance("DES");
    encryptCipher.init(Cipher.ENCRYPT_MODE, key);
    decryptCipher.init(Cipher.DECRYPT_MODE, key);
}

/**
 * Encrypt a string using DES encryption, and return the encrypted
 * string as a base64 encoded string.
 * @param unencryptedString The string to encrypt.
 * @return String The DES encrypted and base 64 encoded string.
 * @throws Exception If an error occurs.
 */
@throws(classOf[Exception])
def encryptBase64 (unencryptedString: String): String = {
    // Encode the string into bytes using utf-8
    val unencryptedByteArray: Array[Byte] = unencryptedString.getBytes("UTF8");

    // Encrypt
    val encryptedBytes: Array[Byte] = encryptCipher.doFinal(unencryptedByteArray);

    // Encode bytes to base64 to get a string
    val encodedBytes: Array[Byte] = Base64.encodeBase64(encryptedBytes);

    return new String(encodedBytes);
}

/**
 * Decrypt a base64 encoded, DES encrypted string and return
 * the unencrypted string.
 * @param encryptedString The base64 encoded string to decrypt.
 * @return String The decrypted string.
 * @throws Exception If an error occurs.
 */
@throws(classOf[Exception])
def decryptBase64 (encryptedString: String): String  = {
    // Encode bytes to base64 to get a string
    val decodedBytes: Array[Byte] = Base64.decodeBase64(encryptedString.getBytes());

    // Decrypt
    val unencryptedByteArray: Array[Byte] = decryptCipher.doFinal(decodedBytes);

    // Decode using utf-8
    return new String(unencryptedByteArray, "UTF8");
}

/**
 * Main unit test method.
 * @param args Command line arguments.
 *
 */
def main(args: Array[String]): Unit = {
    try {
        //Generate the secret key
        val password = "abcd1234";
        val key = new DESKeySpec(password.getBytes());
        val keyFactory = SecretKeyFactory.getInstance("DES");
        val secretKey = keyFactory.generateSecret(key);
        val salt = java.util.Base64.getEncoder().encodeToString(secretKey.getEncoded());

        //Instantiate the encrypter/decrypter
        genSecret(secretKey);
        val plaintext = "Hello World";
        val encryptedString = encryptBase64(plaintext);
        // Encrypted String:8dKft9vkZ4I=

        println("Salt String:"+salt);
        println("Encrypted String:"+encryptedString);

        //Decrypt the string
        val unencryptedString = decryptBase64(encryptedString);
        // UnEncrypted String:Message
        println("UnEncrypted String:"+unencryptedString);

    } catch {
      case e: Exception => {
        println("Error:"+e.toString());
      }
    }
}

}
