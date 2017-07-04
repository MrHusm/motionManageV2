package com.manage.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import com.manage.constant.Constant;

public class CommonUtil {
	/**
	 * 生成兑换码code
	 */
	//public static final String[] RandChar =new String[]{"a","b","c","d","e","f","g","h","j","k","l","m","n","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z","2","3","4","5","6","7","8","9"};
	public static final String[] RandChar =new String[]{"A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z","2","3","4","5","6","7","8","9"};
	public static String[] randCodeArr(int pos,int size){
		String[] arr=new String[size];
		int len=RandChar.length;
		for(int i=0;i<size;i++){
			arr[i]=randCode(pos,len);
		}
		
		return arr;
	}
	
	private static String randCode(int pos,int len){
		if(pos==0){
			return "";
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<pos;i++){
			sb.append(RandChar[getRandomNum(len)]);
		}
		return sb.toString();
	}
	
	public static int getRandomNum(int size) {
		int num = Constant.RANDOM.nextInt(size);
		if (num < 0) {
			num = num * -1;
		}
		return num;
	}
	
	
	//外部接口调用方法
	public static String getHttpData(String url) {
		int statusCode;
		String str =url;
		String response="{code:\"-1\"}";
		HttpClient client = new HttpClient();
		HttpMethod getMethod = new GetMethod(str);
		try {
			statusCode = client.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
			    response=getMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return response;
		}
		return response;
	}
}
