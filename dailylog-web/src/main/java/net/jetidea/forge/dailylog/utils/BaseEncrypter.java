package net.jetidea.forge.dailylog.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseEncrypter {

	protected static Log log = LogFactory.getLog(BaseEncrypter.class);

	/**
	 * Encryption cipher
	 */
	protected Cipher ecipher;

	/**
	 * Decryption cipher
	 */
	protected Cipher dcipher;

	/**
	 * Secret key
	 */
	protected Key key;

	/**
	 * Default constructor.
	 * 
	 */
	public BaseEncrypter() {
		init(null, null);
	}

	/**
	 * Constructor that takes in a crypto algo to use and the secret key
	 * 
	 * @param cryptoAlgo
	 *            The crypto algo
	 * @param key
	 *            The key
	 * 
	 */
	public BaseEncrypter(String cryptoAlgo, Key key) {
		init(cryptoAlgo, key);
	}

	/**
	 * Constructor that takes in a crypto algo and an encoded key
	 * 
	 * @param cryptoAlgo
	 *            The crypto algo
	 * @param encodedKey
	 *            The encoded key
	 */
	public BaseEncrypter(String cryptoAlgo, byte[] encodedKey) {

		// decode the key
		Key key = decodeKey(cryptoAlgo, encodedKey);

		init(cryptoAlgo, key);

	}

	/**
	 * Get the secret key
	 * 
	 * @return The secret key
	 */
	public Key getKey() {
		return this.key;
	}

	/**
	 * Get the encoded secret key
	 * 
	 * @return The encoded secret key
	 */
	public byte[] getEncodedKey() {
		return encodeKey(this.key.getAlgorithm(), this.key);
	}

	/**
	 * Decode a key
	 * 
	 * @param cryptoAlgo
	 *            The crypto algo in use
	 * @param key
	 *            The encoded key
	 * @return The key
	 */
	public static Key decodeKey(String cryptoAlgo, byte[] key) {

		// get the KeySpec
		SecretKeySpec keySpec = new SecretKeySpec(key, getCryptoAlgo(cryptoAlgo));
		return keySpec;

	}

	/**
	 * Encode a key
	 * 
	 * @param cryptoAlgo
	 *            The crypto algo in use
	 * @param key
	 *            The key
	 * @return The encoded key
	 */
	public static byte[] encodeKey(String cryptoAlgo, Key key) {

		// get the KeySpec
		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), getCryptoAlgo(cryptoAlgo));
		return keySpec.getEncoded();

	}

	/**
	 * Generate a key
	 * 
	 * @param cryptoAlgo
	 *            The crypto algo
	 * @return A key
	 */
	public static Key generateKey(String cryptoAlgo) {
		if (cryptoAlgo == null) {
			cryptoAlgo = "AES/ECB/PKCS5PADDING";
		}

		try {
			KeyGenerator keyGen = KeyGenerator.getInstance(getCryptoAlgo(cryptoAlgo));
			return keyGen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * Initialize the ciphers and key
	 * 
	 * @param cryptoAlgo
	 *            The crypto algorithm to use
	 * @param key
	 *            The secret key
	 */
	private void init(String cryptoAlgo, Key key) {

		if (cryptoAlgo == null) {
			// get crypto algo from properties file
			cryptoAlgo = "AES/ECB/PKCS5PADDING";
		}

		// initialize the secret key
		if (key == null) {
			// get key from config file
			String secretKey = "5cGrvweQUYKkw+mw87M8tw==";

			if (secretKey != null) {
				key = decodeKey(cryptoAlgo, Base64.decode(secretKey));
			}
		}

		// if cannot get key from config file as well
		if (key == null) {
			// generate a secret key
			try {
				KeyGenerator keyGen = KeyGenerator.getInstance(getCryptoAlgo(cryptoAlgo));
				key = keyGen.generateKey();
			} catch (NoSuchAlgorithmException e) {
				log.error(e.getMessage(), e);
			}
		}

		this.key = key;

		// initialize the ciphers
		try {
			this.ecipher = Cipher.getInstance(cryptoAlgo);
			this.dcipher = Cipher.getInstance(cryptoAlgo);
			this.ecipher.init(Cipher.ENCRYPT_MODE, this.key);
			this.dcipher.init(Cipher.DECRYPT_MODE, this.key);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			log.error(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			log.error(e.getMessage(), e);
		}

	}

	private static String getCryptoAlgo(String cryptoAlgo) {
		if (cryptoAlgo.indexOf("/") >= 0) {
			return cryptoAlgo.substring(0, cryptoAlgo.indexOf("/"));
		} else {
			return cryptoAlgo;
		}
	}

}