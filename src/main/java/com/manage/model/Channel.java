package com.manage.model;

public class Channel  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME="channel";
	
	private int id;
	
	private Integer channel_id;//书名

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Integer channel_id) {
		this.channel_id = channel_id;
	}
}
