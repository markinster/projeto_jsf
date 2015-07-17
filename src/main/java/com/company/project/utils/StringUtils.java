package com.company.project.utils;

public class StringUtils {
	
	public static boolean isEmpty(String string) {
		if (string == null)
			return true;
		
		if (string.trim().isEmpty())
			return true;
		
		return false;
	}

}
