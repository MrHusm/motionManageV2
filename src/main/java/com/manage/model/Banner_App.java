package com.manage.model;

import java.util.Date;

public class Banner_App {
    private Integer id;
  
    private Integer pageType;//1 应用推荐页 2每日福利页
    
    private String name;//广告名称
  
    private Integer type;//方式 1 直投 2 sdk
  
    private Integer appType;//直投广告类型  1 应用推荐 2 图片
  
    private String appName;//应用名称
  
    private String appVersion;//应用版本
  
    private String appIntro;//应用简介
  
    private String img;//图片地址
  
    private String url;//应用地址或图片跳转地址
  
    private String size;//应用大小
  
    private String appPackName;//应用包名
  
    private String sdkInfo;//sdk信息
  
    private Integer repayType;//赠送类型 1铜币 2 代金券
  
    private Integer money;//赠送金额
  
    private Integer cashCouponId;//代金券ID
  
    private String versions;//发布版本
  
    private Integer channelType;//渠道限制 0全部 1仅在 2仅不在
    
    private String channelIds;//发布渠道
  
    private String startDate; //开始日期

    private String endDate; //结束日期	
    
    private Integer idx;//顺序
    
    private Integer state;//0未开始 1已开始 2已结束
  
    private Date createDate;//创建时间
    
    private Date updateDate;//修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
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

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppIntro() {
		return appIntro;
	}

	public void setAppIntro(String appIntro) {
		this.appIntro = appIntro;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getAppPackName() {
		return appPackName;
	}

	public void setAppPackName(String appPackName) {
		this.appPackName = appPackName;
	}

	public String getSdkInfo() {
		return sdkInfo;
	}

	public void setSdkInfo(String sdkInfo) {
		this.sdkInfo = sdkInfo;
	}

	public Integer getRepayType() {
		return repayType;
	}

	public void setRepayType(Integer repayType) {
		this.repayType = repayType;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getCashCouponId() {
		return cashCouponId;
	}

	public void setCashCouponId(Integer cashCouponId) {
		this.cashCouponId = cashCouponId;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
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
	
	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
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

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
