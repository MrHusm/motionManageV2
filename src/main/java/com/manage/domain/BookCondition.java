package com.manage.domain;

public class BookCondition  extends Condition {

	
	private int id;
	
	private String phb_name;//榜单名
	
	private String book_name;//书名
	
	private String img_url;//封面
	
	private int xl;//序列
	
	private String ids;//榜单下所有书籍Id
	
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public String getPhb_name() {
		return phb_name;
	}

	public void setPhb_name(String phb_name) {
		this.phb_name = phb_name;
	}
	
	
	
}
