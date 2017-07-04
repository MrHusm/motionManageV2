package com.manage.model;

import java.util.Date;

public class Api_Advert  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME="api_advert";
	
	private int id;
	
	private String name;//广告商名称
	
	private String remark;//备注
	
	private Date createDate;
	
	private Date updateDate;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
