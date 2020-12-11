package com.jason.util;

import java.util.Optional;

public class OptionalTest {
	public static void main(String[] args) {
		OptionalTest test = new OptionalTest();
		Integer val1 = null;
		Integer val2 = new Integer(10);
		
		System.out.println(test.sum(val1, val2));
//		Optional<Integer> a = Optional.ofNullable(val1);
//		Optional<Integer> b = Optional.of(val2);
//		System.out.println(test.sum(a,b));
	}
	
	public Integer sum(Integer a, Integer b) {
		if(null == a) {
			a = 0;
		}
		return a + b;
	}
	
	public Integer sum(Optional<Integer> a, Optional<Integer> b) {
		System.out.println("第一个参数值存在：" + a.isPresent());
		System.out.println("第二个参数值存在：" + b.isPresent());
		Integer val1 = a.orElse(new Integer(0));
		Integer val2 = b.get();
		return val1 + val2;
	}
}
