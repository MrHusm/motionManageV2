package com.manage.model;

import java.util.Date;

public class Exit_Remind implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="exit_remind";
	
	private Integer id;

	private String name; //配置名称
	
	private String type;//配置类型 0默认 1签到 2活动
	
	private String isImg;//是否图片 0是 1否
	
	private String content;//文案
	
	private String imgUrl;//图片地址
	
	private String btnContent; //按钮文案
	
	private String btnUrl;//按钮链接
	
	private String startDate; //开始日期
	
	private String endDate; //结束日期	
	
	private Date createDate; //创建时间
	
	private Date updateDate; //更新时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsImg() {
		return isImg;
	}

	public void setIsImg(String isImg) {
		this.isImg = isImg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getBtnContent() {
		return btnContent;
	}

	public void setBtnContent(String btnContent) {
		this.btnContent = btnContent;
	}

	public String getBtnUrl() {
		return btnUrl;
	}

	public void setBtnUrl(String btnUrl) {
		this.btnUrl = btnUrl;
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
}
