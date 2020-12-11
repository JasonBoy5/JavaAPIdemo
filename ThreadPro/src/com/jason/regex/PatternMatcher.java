package com.jason.regex;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
	public static void main(String[] args) {
		//要求取出“#{内容}”标记中的所有内容
		String str = "INSERT INTO dept(deptno,dname,loc) VALUES "
		+"(#{deptno},#{dname},#{loc})";
		String regex = "#\\{\\w+\\}";
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(str);
		while(mat.find()) {
			System.out.println(mat.group(0).replaceAll("(#|\\{|\\})", ""));
		}
	}
}
