package com.manage.model;

import java.io.Serializable;

public class Channel_Paihangbang_Qd implements Serializable {

	public static final String TABLE_NAME ="channel_paihangbang_qd";
	
	private int id;
	
	private String name;//驱动名称
	
	private int type;//1最新上架榜-男 2最新上架榜-女 3最新上架榜-出版 4免费榜 5书城热销榜-男 6书城热销榜-女 7书城热销榜-出版 8完结精品榜-男 9完结精品榜-女 10追书榜-男 11追书榜-女 12人气点击榜-日13人气点击榜-周 14人气点击榜-月 15热搜排行榜 16新书飙升榜

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
