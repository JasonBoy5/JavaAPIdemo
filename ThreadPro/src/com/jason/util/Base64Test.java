package com.jason.util;

import java.util.Base64;

class StringUtil{
	private static final String SALT = "Jason Java";
	private static final int REPEAT = 5;
	
	public static String encode(String str) {
		String temp = str + "{" + SALT + "}";
		byte data[] = temp.getBytes();
		for(int x = 0; x < REPEAT; x++) {
			data = Base64.getEncoder().encode(data);
		}
		return new String(data);
	}
	
	public static String decode(String str) {
		byte data [] = str.getBytes();
		for(int x = 0; x < REPEAT; x++) {
			data = Base64.getDecoder().decode(data);
		}
		return new String(data).replaceAll("\\{Jason Java\\}", "");
	}
}

public class Base64Test {

	public static void main(String[] args) {
		String msg = "www.baidu.com";
		String encMsg = new String(Base64.getEncoder().encode(msg.getBytes()));
		System.out.println("Encoder-->" + encMsg);
		String oldMsg = new String(Base64.getDecoder().decode(encMsg));
		System.out.println("Decoder-->" + oldMsg);
		
		String str = StringUtil.encode("www.baidu.com");
		System.out.println(str);
		System.out.println(StringUtil.decode(str));
		System.out.println();
	}
}
