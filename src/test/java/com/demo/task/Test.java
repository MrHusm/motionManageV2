package com.demo.task;

import java.util.ArrayList;
import java.util.List;



public class Test {
	static String  one = "eq";
	static int two = 10;
	
	public static void main(String[] args) {
		change(one,two);
		System.out.println(one + " ---" + two);
	}
	
	
	public static void change(String one,int two){
		two = two+1;
		one = one +"1111";
	}
}
