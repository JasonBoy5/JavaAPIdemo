package com.jason.thread;

class Producer implements Runnable{
	private Message msg;
	public Producer(Message msg) {
		this.msg = msg;
	}
	@Override
	public void run() {
		for(int x = 0; x < 100; x++) {
			if(x % 2 == 0) {
				this.msg.set(x + "->java","基于C++开发的高级语言");
			}else {
				this.msg.set(x + "->JS","用于前端编程的脚本");
			}
		}
		
	}	
}

class Consumer implements Runnable{

	private Message msg;
	public Consumer(Message msg) {
		this.msg = msg;
	}
	@Override
	public void run() {
		for(int x = 0; x < 100; x++) {
			System.out.println(this.msg.get());
		}
	}
}

class Message{
	private String title;
	private String content;
	private boolean flag = true;
	//flag = ture 允许生产，但是不允许消费
	//flag = false 允许消费，但是不允许生产
	public synchronized void set(String title, String content) {
		if(false == this.flag) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.title = title;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.content = content;
		this.flag = false;
		super.notify();
	}
	public synchronized String get() {
		if(true == this.flag) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.flag = true;//继续生产
			super.notify();//唤醒等待线程
		}
		return this.title + " - " + this.content;
	}
}

public class ProducerConsumer {
	public static void main(String[] args) throws Exception{
		Message msg = new Message();
		new Thread(new Producer(msg)).start();
		new Thread(new Consumer(msg)).start();
//		new Thread(new Consumer(msg)).start();
	}
}
