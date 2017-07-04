package com.demo.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SeparateFiles {
	public static void main(String[] args) {
//		File ppFileDir = new File("F:\\Chinese\\pp\\pp");
//		File[] ppFiles = ppFileDir.listFiles();
//		for(File ppFile : ppFiles){
//			if(ppFile.isDirectory()){
//				File[] files = ppFile.listFiles();
//				for(File file : files){
//					try{
//						String fileName = file.getName();
//						boolean flag = file.renameTo(new File("F:\\Chinese\\pp\\result"+"\\"+fileName.substring(0, fileName.indexOf("."))+"\\"+fileName));
//						if(!flag){
//							System.out.println("移动失败："+file.getAbsolutePath());
//						}
//					}catch(Exception e){
//						System.out.println("移动异常；"+file.getAbsolutePath());
//					}
//				}
//			}
//		}
		File destFile = new File("F:\\4.png");
		File ppFileDir = new File("F:\\capture");
		File[] ppFiles = ppFileDir.listFiles();
		for(File ppFile : ppFiles){
			InputStream inStream = null;
			FileOutputStream fs = null;
			try {
				int byteread = 0;
				inStream = new FileInputStream(destFile); // 读入原文件
				fs = new FileOutputStream(ppFile.getAbsolutePath()+"\\4.png");
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			} catch (Exception e) {
				System.out.println("复制单个文件操作出错");
				e.printStackTrace();
			}finally{
				if(inStream != null){
					try {
						inStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fs != null){
					try {
						fs.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
