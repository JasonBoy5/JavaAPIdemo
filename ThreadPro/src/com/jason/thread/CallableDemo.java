package com.jason.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyThread1 implements Callable<String>{

	@Override
	public String call() throws Exception {
		for(int x = 0; x < 10; x++) {
			System.out.println("**********线程执行、x = "+ x);
		}
		return "线程执行完毕。";
	}
	
}

class Test{
	public static void stringTest(String test) {
		test += "dsfasd";
		System.out.println(test);
	}
}

public class CallableDemo {
	public static void main(String[] args) throws Exception {
//		FutureTask<String> task = new FutureTask<>(new MyThread1());
//		new Thread(task).start();
//		System.out.println("【线程返回数据】" + task.get());
		String str = "123";
		System.out.println("call before:"+str);
		Test.stringTest(str);
		System.out.println("call after:"+str);
	}
}
