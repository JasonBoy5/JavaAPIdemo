package com.jason.util;

import java.util.Arrays;

class Person implements Comparable<Person>{
	private String name;
	private int age;
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Person o) {
//		if(this.age < o.age) {
//			return -1;
//		}else if(this.age > o.age) {
//			return 1;
//		}else {
//			return 0;
//		}
		return this.age - o.age;//ASC
//		return o.age - this.age;//DEC
	}
}

public class ArraysTest {

	public static void main(String[] args) {
		Integer[] data = new Integer[] {10,9,5,2,20};
		Arrays.sort(data);
		System.out.println(Arrays.toString(data));
		
		Person per [] = new Person [] {
				new Person("A",80),
				new Person("B",20),
				new Person("C",100)
		};
		Arrays.sort(per);
		System.out.println(Arrays.toString(per));
	}
}
