package com.manage.model;

import java.util.Date;


public class JP_Page implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="jp_page";
	
	private Integer id;

	private String name; //名称
	
    private Integer type;//类型 1 榜单 2 广告
    
    private Integer source;//0 中文书城    1 创新版
    
    private Integer pageType;//页面 1 重磅 2 男频 3 女频 4 出版物
    
    private Integer idx;//排序
    
    private Integer keyId;//榜单ID或广告ID
    
    private Date createDate;//创建时间
    
    private Date updateDate;//修改时间
    
    private JP_Banner_Position position;
    
    private JP_BangDan bang;

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

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Integer getKeyId() {
		return keyId;
	}

	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
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

	public JP_Banner_Position getPosition() {
		return position;
	}

	public void setPosition(JP_Banner_Position position) {
		this.position = position;
	}

	public JP_BangDan getBang() {
		return bang;
	}

	public void setBang(JP_BangDan bang) {
		this.bang = bang;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
}
