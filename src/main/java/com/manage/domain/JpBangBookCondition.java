package com.manage.domain;

public class JpBangBookCondition extends Condition{
	private String bookId;
	
	private String bookName;//图书名称

	private String bangDanId;
	
	private String source="0";//0 中文书城 1 创新版
	
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBangDanId() {
		return bangDanId;
	}

	public void setBangDanId(String bangDanId) {
		this.bangDanId = bangDanId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
