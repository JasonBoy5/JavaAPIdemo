package com.jason.util;

import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		File file = new File("G:" + File.separator + "Test" + File.separator);
//		FileUtil.listDir(file);
		FileUtil.renameFiles(file);
		long end = System.currentTimeMillis();
		System.out.println("use time:" + (end - start));
	}

	
}

class FileUtil{
	public static void listDir(File file) {
		if(file.isDirectory()) {
			File results [] = file.listFiles();
			if(null != results) {
				for(File f : results) {
					listDir(f);
				}
			}
		}
		System.out.println(file);
	}
	
	public static void renameFiles(File file) {
		if(file.isDirectory()) {
			File results [] = file.listFiles();
			if(null != results) {
				for(File f : results) {
					renameFiles(f);
				}
			}
		}else {
			String fileName = null;
			if(file.getName().contains(".")) {
				fileName = file.getName().substring(0,file.getName().lastIndexOf(".")) + ".txt";
			}else {
				fileName = file.getName() + ".txt";
			}
			File newFile = new File(file.getParentFile(),fileName);
			file.renameTo(newFile);
			System.out.println(fileName);
		}
	}
}
