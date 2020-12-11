package com.jason.util;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

class MyTask extends TimerTask{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()
				+ "、定时任务执行，当前时间：" + 
				new SimpleDateFormat("y-m-d h:m:s").format(System.currentTimeMillis()));
	}
	
}

public class TimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 0);
	}
}
