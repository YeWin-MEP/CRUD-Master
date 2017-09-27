package com.techfun.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private static final String PASSWORD_CHECK = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

	public static boolean validatePassword(String strPassword) {
		if (!validateRegex(strPassword, PASSWORD_CHECK)) {
			return false;
		} else {
			return true;
		}
	}

	private static boolean validateRegex(String str, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.find();
	}
}
