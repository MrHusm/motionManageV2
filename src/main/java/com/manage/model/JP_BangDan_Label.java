package com.manage.model;

import java.util.Date;


public class JP_BangDan_Label implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="jp_bangdan_label";
	
	private Integer id;
	
	private String name;//名称
	
	private Integer type;//类型 1跳转链接 2榜单 3标签
	
	private String url;//链接
	
	private Integer bangDanId;//榜单ID
	
    private Date createDate;//创建时间
    
    private Date updateDate;//修改时间

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getBangDanId() {
		return bangDanId;
	}

	public void setBangDanId(Integer bangDanId) {
		this.bangDanId = bangDanId;
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
