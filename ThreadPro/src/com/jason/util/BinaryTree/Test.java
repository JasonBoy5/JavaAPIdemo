package com.jason.util.BinaryTree;

public class Test {
	public static void main(String[] args) {
		String str = "192.168.13.259";
		System.out.println(Validator.validateIP(str));
	}
}

class Validator{
	public static boolean validateIP(String ip) {
		if(null == ip || "".equals(ip)) {
			return false;
		}
		String regex = "([12]?[0-9]?[0-9]\\.){3}([12]?[0-9]?[0-9])";
		
		if(ip.matches(regex)) {
			String result[] = ip.split("\\.");
			for(String r : result) {
				if(Integer.valueOf(r) > 255) {
					return false;
				}
			}
		}
		return true;
	}
}
