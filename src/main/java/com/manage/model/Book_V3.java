package com.manage.model;

public class Book_V3  implements java.io.Serializable{

	public static final String TABLE_NAME="book_v3";
	
	private int id;
	
	private String book_name;//书名
	
	private String img_url;//封面
	
	private int xl;//序列
	
	private String zc_id;
	
	
	public String getZc_id() {
		return zc_id;
	}

	public void setZc_id(String zc_id) {
		this.zc_id = zc_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public int getXl() {
		return xl;
	}

	public void setXl(int xl) {
		this.xl = xl;
	}
	
	
}
