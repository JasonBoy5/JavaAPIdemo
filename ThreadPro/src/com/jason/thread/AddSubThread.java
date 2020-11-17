package com.jason.thread;
class AddThread implements Runnable{
	private Resource resource;
	public AddThread(Resource resource) {
		this.resource = resource;
	}
	@Override
	public void run() {
		for(int x = 0; x < 50; x++) {
			try {
				this.resource.add(x);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class SubThread implements Runnable{
	private Resource resource;
	public SubThread(Resource resource) {
		this.resource = resource;
	}
	@Override
	public void run() {
		for(int x = 0; x < 50; x++) {
			try {
				this.resource.sub(x);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Resource{
	private int num = 0;
	private boolean flag = true;
	
	public synchronized void add(int index) throws Exception {
		if(!this.flag) {
			System.out.println("线程等待.....");
			wait();
		}
		Thread.sleep(10);
		this.num ++ ;
		System.out.println(index + "【+】 - " + Thread.currentThread().getName() + " num = "+this.num);
		this.flag = false;
		super.notifyAll();

	}
	
	public synchronized void sub(int index) throws Exception {
		if(this.flag) {
			wait();
		}
		Thread.sleep(20);
		this.num -- ;
		System.out.println(index + "【-】 - " + Thread.currentThread().getName() + " num = "+this.num);
		this.flag = true;
		super.notifyAll();

	}
}

public class AddSubThread {
	public static void main(String[] args) {
		Resource res  = new Resource();
		SubThread st = new SubThread(res);
		AddThread at = new AddThread(res);
		
		new Thread(at,"加法线程 - A").start();
		new Thread(at,"加法线程 - B").start();
		new Thread(st,"减法线程 - X").start();
		new Thread(st,"减法线程 - Y").start();
	}
}
