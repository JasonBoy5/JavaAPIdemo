package com.jason.regex;

import java.util.regex.Pattern;

public class RegexDemo1 {
	public static void main(String[] args) {
		
		String str = "JKL()UI$fasdo@#sfdads[]fsdfj";
		String regex = "[^a-zA-Z]+";
		Pattern pat = Pattern.compile(regex);
		String ret[] = pat.split(str);
		for(int x = 0; x < ret.length; x++) {
			System.out.println(ret[x]);
		}
		System.out.println();
		for (String r : ret) {
			System.out.println(r);
		}
	}
}
