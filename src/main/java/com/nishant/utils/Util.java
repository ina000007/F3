package com.nishant.utils;

import java.util.Date;

public class Util {
	public static String getToken(String emailId) {
	      Date date = new Date();
	      long time = date.getTime();
		return emailId.split("@")[0]+"-"+time+"";
	}
}
