package com.jason.thread;

class MeThread implements Runnable{
	private String title;
	public MeThread(String title) {
		this.title = title;
	}
	@Override
	public void run() {
		for(int x = 0; x < 10; x++) {
			System.out.println("运行 " + this.title + x);
		}
	}
}

class MyThread extends Thread{
	private String title;
	public MyThread(String title) {
		this.title = title;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int x = 0; x < 10; x++) {
			System.out.println("运行 " + this.title + x);
		}
	}
}

public class ThreadDemo2 {
	public static void main(String[] args) {
//		Thread t1 = new Thread(new MeThread("线程A"));
//		t1.start();
//		Thread t2 = new Thread(new MeThread("线程B"));
//		t2.start();
//		Thread t3 = new Thread(new MeThread("线程C"));
//		t3.start();
		for(int x = 0; x < 3; x++) {
			String title = "线程对象-"+ x;
			Runnable run = ()->{
				for(int y = 0; y < 10; y++) {
					System.out.println(title + "运行,y = " + y);
				}
			};
			new Thread(run).start();
		}
	}
}
