package com.demo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AnalysisData {
	public static void main(String[] args) {
		analysis("c:\\apk.log");
	}
	
	
	public static void analysis(String path){
		Map<String,Integer> channleStr = new HashMap<String,Integer>(); 
		Map<String,Integer> testStr = new HashMap<String,Integer>();
		Map<String,Integer> otherStr = new HashMap<String,Integer>();
		File file = new File(path);
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	int start = tempString.indexOf("/211/apk/");
	        	
	        	if(start != -1){
	        		int end = tempString.indexOf("HTTP/1.1");
	        		if(end == -1){
	        			end = tempString.indexOf("HTTP/1.0");
	        		}
	        		String s = tempString.substring(start+9, end);
	        		if(!s.startsWith("test")){
	        			int num = s.indexOf("/");
	        			if(num != -1){
	        				String other = s.substring(0,s.indexOf("/"));
	        				if(!isNumeric(other)){
	        					if(otherStr.get(other)!=null){
	        						otherStr.put(other, otherStr.get(other)+1);
	        					}else{
	        						otherStr.put(other, 1);
	        					}
	        				}else{
	        					if(channleStr.get(other)!=null){
	        						channleStr.put(other, channleStr.get(other)+1);
	        					}else{
	        						channleStr.put(other, 1);
	        					}
	        				}
	        			}else{
	        				if(otherStr.get(s)!=null){
	        					otherStr.put(s, otherStr.get(s)+1);
	        				}else{
	        					otherStr.put(s, 1);
	        				}
	        			}
	        		}else{
	        			String key = s.substring(5);
	        			if(testStr.get(key)!=null){
	        				testStr.put(key, testStr.get(key)+1);
	        			}else{
	        				testStr.put(key, 1);
	        			}
	        		}
	        	}
	        }
	        for (String key : channleStr.keySet()) {
	        	   System.out.println("数字类的："+ key + " 数量:" + channleStr.get(key));
	        }
	        
	        for (String key : testStr.keySet()) {
	        	   System.out.println("test类的："+ key + " 数量：" + testStr.get(key));
	        }
	        
	        for (String key : otherStr.keySet()) {
	        	   System.out.println("其他："+ key + " 数量：" + otherStr.get(key));
	        }
	        
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	            }
	        }
	    }
	}
	
	public static boolean isNumeric(String str){ 
	     Pattern pattern = Pattern.compile("[0-9]*"); 
	     return pattern.matcher(str).matches();    
	} 
}
