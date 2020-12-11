package com.jason.util;

class Message{
	private String info;
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
}

class Channel{//消息的发送通道
	private static final ThreadLocal<Message> THREADLOCAL = new ThreadLocal<Message>();
	public static void setMessage(Message m) {
		THREADLOCAL.set(m);
	}
	public static void send() {//发送消息
		System.out.println("【" + Thread.currentThread().getName() + "发送消息】" 
							+ THREADLOCAL.get().getInfo());
	}
}

public class ThreadLocalTest {
	public static void main(String[] args) {
		new Thread(()->{
			Message msg = new Message();//实例化消息对象
			msg.setInfo("www.baidu.com");//设置要发送的内容
			Channel.setMessage(msg);//
			Channel.send();
		},"线程A").start();
		new Thread(()->{
			Message msg = new Message();//实例化消息对象
			msg.setInfo("Bwww.");//设置要发送的内容
			Channel.setMessage(msg);//
			Channel.send();
		},"线程B").start();
		new Thread(()->{
			Message msg = new Message();//实例化消息对象
			msg.setInfo("Ccom");//设置要发送的内容
			Channel.setMessage(msg);//
			Channel.send();
		},"线程C").start();

	}
}
