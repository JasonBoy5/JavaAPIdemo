package com.jason.thread;

import java.io.*;

class MathUtil{
	private MathUtil() {}
	public static double round(double num, int scale) {
		System.out.println((num * Math.pow(10.0, scale)) / Math.pow(10.0, scale));
		return Math.round(num * Math.pow(10.0, scale)) / Math.pow(10.0, scale);
	}
}

public class JavaAPIDemo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = null;
		System.out.println("Enter youre value:");
		temp = br.readLine();
		System.out.println("your value is:" + temp);
		
		String str = "123";
		if(isNumber(str)) {
			int num = Integer.parseInt(str);
			System.out.println(num * 2);
		}
	}
	
	public static boolean isNumber(String str) {
		char data[] = str.toCharArray();
		for(int x = 0; x < data.length; x++) {
			if(data[x] > '9' || data[x] < '0') {
				return false;
			}
		}
		return true;
	}
}
