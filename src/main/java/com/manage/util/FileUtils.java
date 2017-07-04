package com.manage.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

public class FileUtils {
	public static final SFTPUtil sftpUtil = new SFTPUtil();
	
	public static Session getSession243() {
		//正式
		Session session = sftpUtil.getConnection("60.29.240.243", 10022, "iwanvi", "ChineseAll*()@iwanvi");
		//测试
		//Session session = sftpUtil.getConnection("60.28.209.235", 10022, "root", "zwsctest@2015");
		return session;
	}
	
	public static Session getSession211() {
		//正式
		Session session = sftpUtil.getConnection("60.29.240.211", 10007, "client", "unV/V(2c");
		return session;
	}
	
	public static void uploadFile(String filePath,String remotePath,Session session){
		ChannelSftp channelSftp = sftpUtil.getChannelSftp(session);
		sftpUtil.uploadFile(channelSftp,filePath, remotePath);
		sftpUtil.closeConnection(channelSftp, session);
		
	}

	public static void deleteFile(String deleteFile,Session session){
		ChannelSftp channelSftp = sftpUtil.getChannelSftp(session);
		sftpUtil.deleteFile(channelSftp, deleteFile);
		sftpUtil.closeConnection(channelSftp, session);
	}
	
}
