package com.manage.model;

public class Bookcategory_V3  implements java.io.Serializable{

	
	public static final String TABLE_NAME="bookcategory_v3";
	
	private int id;
	
	private String name;//图书分类
	
	private String authorCategory;//作者分类

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorCategory() {
		return authorCategory;
	}

	public void setAuthorCategory(String authorCategory) {
		this.authorCategory = authorCategory;
	}
	
	
	
}
