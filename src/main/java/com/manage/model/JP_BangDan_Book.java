package com.manage.model;

import java.util.Date;

public class JP_BangDan_Book implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="jp_bangdan_book";
	
	private Integer id;
	
	private Integer bangDanId;//榜单ID
	
	private String bangDanName;//榜单名称
	
	private String bookId;//图书id
	
    private String bookName;//图书名称
    
    private String imgUrl;//图书封面地址
    
    private String authorName;//作者姓名
    
    private String intro;//简介
    
    private String startDate;//轮播开始时间
    
    private String endDate;//轮播结束时间
    
    private Integer idx;//排序
    
    private String mark;//角标
    
    private String markColor;//角标颜色
    
    private Date createDate;
    
    private Date updateDate;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBangDanId() {
		return bangDanId;
	}

	public void setBangDanId(Integer bangDanId) {
		this.bangDanId = bangDanId;
	}

	public String getBangDanName() {
		return bangDanName;
	}

	public void setBangDanName(String bangDanName) {
		this.bangDanName = bangDanName;
	}

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getMarkColor() {
		return markColor;
	}

	public void setMarkColor(String markColor) {
		this.markColor = markColor;
	}
}
