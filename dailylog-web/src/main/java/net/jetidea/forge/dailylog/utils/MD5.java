package net.jetidea.forge.dailylog.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	private static MessageDigest digest;

	static {

		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
		}
	}

	public synchronized static String encrypt(String toEncrypt) {
		try {
			digest.update(toEncrypt.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] hash = digest.digest();
		return bytes2Hex(hash);
	}

	public synchronized static byte[] hash(String toEncrypt) {
		try {
			digest.update(toEncrypt.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] hash = digest.digest();
		return hash;
	}

	public static String bytes2Hex(byte[] bts) {
		StringBuffer des = new StringBuffer();
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des.append("0");
			}
			des.append(tmp);
		}
		return des.toString();
	}

	public static void main(String[] args) {
		System.out.println(MD5.encrypt("123"));
	}

}
