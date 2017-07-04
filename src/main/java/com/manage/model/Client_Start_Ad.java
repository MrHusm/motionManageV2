package com.manage.model;

import java.util.Date;

public class Client_Start_Ad implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="client_start_ad";
	
	private Integer id;

	private String name; //启动图名称
	
	private Integer type;//类型 0运营配置 1广告api
	
	private String imgUrl;//图片地址
	
	private String url;//图片跳转链
	
	private Integer pauseTime;//停顿时间
	
	private String startDate; //开始日期
	
	private String endDate; //结束日期	
	
	private String channelIds; //渠道
	
	private String versions;//版本
	
	private Integer apiId;//api广告商ID
	
	private Integer state;//是否已经开始 0 未开始 1开始
	
	private Date createDate; //创建时间
	
	private Date updateDate; //更新时间

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPauseTime() {
		return pauseTime;
	}

	public void setPauseTime(Integer pauseTime) {
		this.pauseTime = pauseTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public Integer getApiId() {
		return apiId;
	}

	public void setApiId(Integer apiId) {
		this.apiId = apiId;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
