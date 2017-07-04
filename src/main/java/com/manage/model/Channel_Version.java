package com.manage.model;

public class Channel_Version implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="channel_version";
	
	private Integer id;

	private String versionCode; //版本
	
	private String type; //版本
	
	private String channelId;//渠道
	
	private String version;//渠道
	
	private String description;//描述

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
