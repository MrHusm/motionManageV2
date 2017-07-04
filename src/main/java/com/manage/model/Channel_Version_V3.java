package com.manage.model;

import java.io.Serializable;

public class Channel_Version_V3 implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="channel_version_v3";
	
	private Integer id;
	
	private String vcode;
	
	private Integer vtype;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public Integer getVtype() {
		return vtype;
	}

	public void setVtype(Integer vtype) {
		this.vtype = vtype;
	}
}
