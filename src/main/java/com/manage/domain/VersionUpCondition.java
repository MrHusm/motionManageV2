package com.manage.domain;

public class VersionUpCondition extends Condition{
	
	private String version; //版本
	
	private String channelId;//渠道

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
}
