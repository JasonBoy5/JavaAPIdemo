package com.jason.thread;

class ticketThread implements Runnable{

	private  int ticket = 10;
	private synchronized boolean sale() {
		if(this.ticket > 0) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "卖票，ticket = " + this.ticket-- );
			return true;
		}else {
			System.out.println("*****票卖光了****");
			return false;
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(sale()) {
			
		}
	}
	
}

public class ThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		ticketThread mt = new ticketThread();
		new Thread(mt,"票贩子A").start();
		new Thread(mt,"票贩子B").start();
		new Thread(mt,"票贩子C").start();
	}
}
