package com.feature.gcoin.common.util;

import org.slf4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author tiennv@gmail.com
 *
 */
public class SecurityUtil {
	private static @Log
    Logger logger;

	 public static String encryptSHA256(String base) throws SecurityException {
		try {
		    MessageDigest digestSha = MessageDigest.getInstance("SHA-256");
		    byte[] hash = digestSha.digest(base.getBytes("UTF-8"));
		    StringBuilder hexString = new StringBuilder();
		    for (int i = 0; i < hash.length; i++) {
		        String hex = Integer.toHexString(0xff & hash[i]);
		        if (hex.length() == 1) {
		            hexString.append('0');
		        }
		        hexString.append(hex);
		    }
		    return hexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			logger.error("encryptSHA256", ex);
		    throw new SecurityException(ex);
		}
	 }
}
