package com.feature.gcoin.common.util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author TienNV
 * @CreatedDate Oct 4, 2017 2:46:27 PM
 */
public class Utils {
	public static final SimpleDateFormat SIMPLE_FOMAT_DATE_TIME = new SimpleDateFormat(Constants.DATE_TIME_PATTERN);

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public static void main(String[] args)
	{
		String password = "1";
		String salt = "abcd";
		System.out.println(encryptSHA256(password + salt));
	}

	public static String generateRandomString(int len) {
		SecureRandom r = new SecureRandom();
		int maxLen = ALPHABET.length();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(ALPHABET.charAt(r.nextInt(maxLen)));
		}

		return sb.toString();
	}
	
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
		    throw new SecurityException("SecurityUtil",ex);
		}
	 }
	public static String readFile(File file) {
		StringBuilder result = new StringBuilder();
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				if (result.length() != 0) {
					result.append("\n");
				}
				result.append(line);
				line = "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();

	}

	public static Date getNextDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getNumberDayOfMonth(Date month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(month);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Date getBeginDateOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.HOUR, 0);
		return cal.getTime();
	}

	public static Date getEndDateOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.HOUR, 23);
		return cal.getTime();
	}

	public static Date convertStringToDate(String dateStr, String pattern) throws ParseException {
		DateFormat format = new SimpleDateFormat(pattern);
		Date date = format.parse(dateStr);
		return date;
	}
}
