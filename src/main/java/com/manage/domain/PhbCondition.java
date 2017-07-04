package com.manage.domain;

public class PhbCondition extends Condition {

	private String id;
	
	private int idx;//序列
	
	private String name; //榜单名称
	
	private String bdIds;//子榜单id
	
	private String pxId;
	
	
	


	public String getPxId() {
		return pxId;
	}

	public void setPxId(String pxId) {
		this.pxId = pxId;
	}

	public String getBdIds() {
		return bdIds;
	}

	public void setBdIds(String bdIds) {
		this.bdIds = bdIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
}
