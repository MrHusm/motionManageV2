package com.manage.domain;

public class PlugInCondition extends Condition{
	
	private String name; //插件名称
	
	private String type;//插件类型 1 pdf 2 语音朗读
	
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
}
