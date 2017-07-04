package com.manage.model;

import java.io.Serializable;
import java.util.Date;

public class Channel_Paihangbang implements Serializable{

	public static final String TABLE_NAME ="channel_paihangbang";
	
	private String id;
	
	private String name;//榜单名称
	
	private String icon;//榜单icon
	
	private int dataType;//榜单书籍来源：1 人工 2驱动
	
	private int css;//榜单样式flag 1展示书籍 2列表 3小图 4指数图
	
	private int cc;//展示书籍数量
	
	private String qdId;//驱动规则id
	
	private String keyword;//指数关键词
	
	private String bdIds;//子榜单id
	
	private int idx;//序列
	
	private Date createDate;//创建时间
	
	private Date updateDate;//更新时间

	private String intro;//榜单介绍
	
	private String bids;//榜单手工图书ids
	
	private int flag;//0主榜单 1子榜单
	
	private String pxId;
	
	
	
	
	
	
	
	public String getPxId() {
		return pxId;
	}

	public void setPxId(String pxId) {
		this.pxId = pxId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getBids() {
		return bids;
	}

	public void setBids(String bids) {
		this.bids = bids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getCss() {
		return css;
	}

	public void setCss(int css) {
		this.css = css;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}


	public String getQdId() {
		return qdId;
	}

	public void setQdId(String qdId) {
		this.qdId = qdId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getBdIds() {
		return bdIds;
	}

	public void setBdIds(String bdIds) {
		this.bdIds = bdIds;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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
