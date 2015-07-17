package com.company.project.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {
	
	public static boolean isEmpty(String string) {
		if (string == null)
			return true;		

		return string.trim().isEmpty();		
	}

	
	public static String geraMD5(String senha) {

		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");

			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			String md5 = hash.toString(16);

			// MARKINSTER: adicionando zeros a esquerda
			while (md5.length() < 32) 
				md5 = "0" + md5;			

			return md5;
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
