package com.manage.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * @author wellong
 */

public class SFTPUtil {
	
	/**
	 * 从远程服务器上拷贝文件夹到本地
	 * @param channelSftp
	 * @param remotePath
	 * @param localPath
	 */
	public void copyRemoteDirectory(ChannelSftp channelSftp, String remotePath, String localPath) {
		try {
			Vector<LsEntry> vector = channelSftp.ls(remotePath);
			File file = new File(localPath);
			file.mkdirs();
			
			for (LsEntry entry : vector) {
				String fileName = entry.getFilename();
				String current_remotePath = remotePath+"/"+fileName;
				String current_localPath = localPath+"/"+fileName;
				SftpATTRS attr = entry.getAttrs();
				if (attr.isDir()) {
					if (!fileName.contains(".") && !fileName.contains("..")) {
						copyRemoteDirectory(channelSftp, current_remotePath, current_localPath);
					}
				} else {
					this.downloadFile(channelSftp, current_remotePath, current_localPath);				
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 关闭连接
	 * @param channelSftp
	 * @param session
	 */
	public void closeConnection(ChannelSftp channelSftp, Session session) {
		channelSftp.disconnect();
		session.disconnect();
	}
	
	/**
	 * 上传文件
	 * @param channelSftp
	 * @param uploadFile 上传的文件
	 * @param directory 上传目录
	 */
	public void uploadFile(ChannelSftp channelSftp, String uploadFile, String directory) {
		try {
			channelSftp.cd(directory);
			File file = new File(uploadFile);
			FileInputStream fileInput = new FileInputStream(file);
			channelSftp.put(fileInput, file.getName(), ChannelSftp.OVERWRITE);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 删除文件
	 * @param channelSftp
	 * @param deleteFile
	 */
	public void deleteFile(ChannelSftp channelSftp, String deleteFile) {
		try {
			channelSftp.rm(deleteFile);
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文件夹
	 * @param channelSftp
	 * @param deleteDir
	 */
	public void deleteDir(ChannelSftp channelSftp, String deleteDir) {
		try {
			channelSftp.rm(deleteDir);
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载文件
	 * @param channelSftp
	 * @param downloadFile 下载文件
	 * @param saveFile	保存文件
	 */
	public void downloadFile(ChannelSftp channelSftp, String downloadFile, String saveFile) {
		try {
			File file = new File(saveFile);
			FileOutputStream fileOut = new FileOutputStream(file);
			channelSftp.get(downloadFile, fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得sftp连接
	 * @param ip
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 */
	public Session getConnection(String ip, int port, String username, String password) {
		Session sftpSession = null;
		try {
			JSch jsch = new JSch();
		    sftpSession = jsch.getSession(username, ip, port);
			sftpSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sftpSession.setConfig(sshConfig);
			sftpSession.connect();
			System.out.println("connect success!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sftpSession;
	}
	
	public ChannelSftp getChannelSftp(Session sftpSession) {
		ChannelSftp channelSftp = null;
		try {
			Channel channel = sftpSession.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channelSftp;
	}
	
	public static void main(String[] args) {
		SFTPUtil util = new SFTPUtil();
		try {
			Session sftpSession = util.getConnection("211.140.17.111", 21, "hd0003", "hd0003#0715");
//			Channel channel = sftpSession.openChannel("sftp");
//			channel.connect();
//			ChannelSftp channelSftp = (ChannelSftp) channel;	
//			util.copyRemoteDirectory(channelSftp, "/ikanshu/www/RechargeManage/res/My97DatePicker", "e:/logs/ikanshu/www/RechargeManage/res/My97DatePicker");
//			System.out.println("over.....");
//			util.closeConnection(channelSftp, sftpSession);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
