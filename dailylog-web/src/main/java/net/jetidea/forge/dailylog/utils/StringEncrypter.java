package net.jetidea.forge.dailylog.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class StringEncrypter extends BaseEncrypter {

	/**
	 * Default constructor.
	 * 
	 */
	public StringEncrypter() {

		super();

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
	public StringEncrypter(String cryptoAlgo, Key key) {

		super(cryptoAlgo, key);

	}

	/**
	 * Constructor that takes in a crypto algo and an encoded key
	 * 
	 * @param cryptoAlgo
	 *            The crypto algo
	 * @param encodedKey
	 *            The encoded key
	 */
	public StringEncrypter(String cryptoAlgo, byte[] encodedKey) {

		super(cryptoAlgo, encodedKey);

	}

	/**
	 * Takes a single String as an argument and returns an Encrypted version of
	 * that String.
	 * 
	 * @param str
	 *            String to be encrypted
	 * @param padding
	 * 
	 * @return <code>String</code> Encrypted version of the provided String
	 */
	public String encrypt(String str, boolean padding) {
		try {
			// Encrypt
			byte[] enc = ecipher.doFinal(str.getBytes("UTF-8"));

			// Encode bytes to base64 to get a string
			String encodedStr = Base64.encodeBytes(enc, false);

			if (padding)// default cryptoAlgo is padding
				return encodedStr;

			return encodedStr.replaceAll("=", "");

		} catch (BadPaddingException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			log.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * @param str
	 * @return
	 */
	public String encrypt(String str) {
		return encrypt(str, false);
	}

	/**
	 * Takes a single String as an argument and a cipher and returns an
	 * Encrypted version of that String.
	 * 
	 * @param str
	 *            String to be encrypted ecipherin Cipher used to encrypt the
	 *            string
	 * @param ecipherin
	 *            The cipher
	 * @return <code>String</code> Encrypted version of the provided String
	 */
	public String encrypt(String str, Cipher ecipherin) {
		try {
			// Encrypt
			byte[] enc = ecipherin.doFinal(str.getBytes("UTF-8"));

			// Encode bytes to base64 to get a string
			return Base64.encodeBytes(enc, false);
		} catch (BadPaddingException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			log.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * Takes a encrypted String as an argument, decrypts and returns the
	 * decrypted String.
	 * 
	 * @param str
	 *            Encrypted String to be decrypted
	 * @return <code>String</code> Decrypted version of the provided String
	 */
	public String decrypt(String str) {
		try {
			// 2008-11-04 don't know why the cookie value will lost the padded
			// strings on virtual host
			// to avoid that case we call the padding method first just in case
			str = padding(str);

			// Decode base64 to get bytes
			byte[] dec = Base64.decode(str);

			// Decrypt
			return new String(dcipher.doFinal(dec), "UTF-8");
		} catch (BadPaddingException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			log.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * Takes a encrypted String as an argument and a cipher, decrypts and
	 * returns the decrypted String.
	 * 
	 * @param str
	 *            Encrypted String to be decrypted dcipherin Cipher used to
	 *            decrpt the encrypted string
	 * @param dcipherin
	 *            The decipher
	 * @return <code>String</code> Decrypted version of the provided String
	 */
	public String decrypt(String str, Cipher dcipherin) {
		try {

			// str = padding(str);

			// Decode base64 to get bytes
			byte[] dec = Base64.decode(str);

			// Decrypt
			return new String(dcipherin.doFinal(dec), "UTF-8");
		} catch (BadPaddingException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			log.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * The following method is used for testing the String Encrypter class. This
	 * method is responsible for encrypting and decrypting a sample String
	 * 
	 * @throws Exception
	 */
	public static void test() throws Exception {
		String secretString = "dfadfdee";
		String cryptoAlgo = "AES/ECB/PKCS5PADDING";

		// Generate a secret key
		Key secretKey = StringEncrypter.decodeKey(cryptoAlgo,
				StringEncrypter.encodeKey(cryptoAlgo, StringEncrypter.generateKey(cryptoAlgo)));

		// Create encrypter/decrypter class
		StringEncrypter encrypter = new StringEncrypter(cryptoAlgo, (Key) null);

		// Encrypt the string
		String desEncrypted = encrypter.encrypt(secretString, false);

		// Decrypt the string
		String desDecrypted = encrypter.decrypt(desEncrypted);

		// Print out values
		System.out.println(cryptoAlgo + " Encryption algorithm");
		System.out.println("    Secret Key       : "
				+ Base64.encodeBytes(StringEncrypter.encodeKey(cryptoAlgo, secretKey), false));
		System.out.println("    Original String  : " + secretString);
		System.out.println("    Encrypted String : " + desEncrypted);
		System.out.println("    Decrypted String : " + desDecrypted);
		System.out.println();
	}

	/**
	 * Sole entry point to the class and application used for testing the String
	 * Encrypter class.
	 * 
	 * @param args
	 *            Array of String arguments.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String key = "1234567812345678";

		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

		StringEncrypter se = new StringEncrypter("DES/CBC/PKCS5Padding", secretKey);
		String encrypt = se.encrypt("hello", se.ecipher);

		System.out.println(encrypt);
		System.out.println("======================");
		System.out.println(se.decrypt(encrypt, se.dcipher));
	}

	private String padding(String str) {
		switch (str.length() % 4) {
		case 0:
			return str;
		case 1:
			return str + "===";
		case 2:
			return str + "==";
		case 3:
			return str + "=";
		default:
			break;
		}

		return str;
	}
}
