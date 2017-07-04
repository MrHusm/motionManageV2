package com.manage.model;  

import org.apache.commons.lang.StringUtils;

public class User implements java.io.Serializable {  
	
	public static final String TABLE_NAME="user";  
	private java.lang.Integer id;    
	private java.lang.String channel;    
	private java.lang.String name;    
	private java.lang.String password;    
	private java.lang.String nickName;      
	private java.lang.Integer qiyeId;  
	private java.lang.Short status;
	private java.lang.String logo;
	private java.lang.String email;
	private java.lang.String tel; 
	private java.lang.String info;
	private java.lang.String imsi;
	private java.lang.String imei;
	private java.lang.String pckName;
	private java.lang.String headBg="";
	private java.lang.Short sex;
	private java.util.Date birthDay;
	private java.util.Date createDate;
	private java.util.Date updateDate;
	private java.util.Date lastLoginTime;
	private java.util.Date reviewLastTime;
	
	private int amount=0;
	private int cashCoupon=0;
	private int vip=0;
	//最新的包月
	private int byFlag=0;
	//最新的礼品
	private int awardFlag=0;
	private String qq="";
	private String weibo="";
	private String wx="";
	private int level=0;
	private String vipStr="";
	
	public java.lang.Integer getId()  {       
		return this.id;  }   
	public void setId(java.lang.Integer id)  {     
		this.id = id;  }   
	public java.lang.String getChannel()  {      
		return this.channel;  }   
	public void setChannel(java.lang.String channel)  {   
		this.channel = channel;  }    
	public java.lang.String getName()  {        
		return this.name;  }     
	public void setName(java.lang.String name)  {    
		this.name = name;  }     
	public java.lang.String getPassword()  {     
		return this.password;  }    
	public void setPassword(java.lang.String password)  {   
		this.password = password;  }      
	public java.lang.String getNickName()  {       
		return this.nickName;  }    
	public void setNickName(java.lang.String nickName)  {       
		this.nickName = nickName;  }     
	public java.lang.Integer getQiyeId()  {     
		return this.qiyeId;  }     
	public void setQiyeId(java.lang.Integer qiyeId)  {         this.qiyeId = qiyeId;  }            public java.lang.Short getStatus()  {         return this.status;  }      public void setStatus(java.lang.Short status)  {         this.status = status;  }            public java.lang.String getLogo()  {         return this.logo;  }      public void setLogo(java.lang.String logo)  {         this.logo = logo;  }            public java.lang.String getEmail()  {         return this.email;  }      public void setEmail(java.lang.String email)  {         this.email = email;  }           public java.lang.String getTel()  {         return this.tel;  }      public void setTel(java.lang.String tel)  {         this.tel = tel;  }            public java.lang.String getInfo()  {         return this.info;  }      public void setInfo(java.lang.String info)  {         this.info = info;  }            public java.lang.String getImsi()  {         return this.imsi;  }      public void setImsi(java.lang.String imsi)  {         this.imsi = imsi;  }            public java.lang.String getImei()  {         return this.imei;  }      public void setImei(java.lang.String imei)  {         this.imei = imei;  }            public java.lang.String getPckName()  {         return this.pckName;  }      public void setPckName(java.lang.String pckName)  {         this.pckName = pckName;  }            public java.lang.String getHeadBg()  {         return this.headBg;  }      public void setHeadBg(java.lang.String headBg)  {         this.headBg = headBg;  }            public java.lang.Short getSex()  {         return this.sex;  }      public void setSex(java.lang.Short sex)  {         this.sex = sex;  }           public java.util.Date getBirthDay()  {         return this.birthDay;  }      public void setBirthDay(java.util.Date birthDay)  {         this.birthDay = birthDay;  }            public java.util.Date getCreateDate()  {         return this.createDate;  }      public void setCreateDate(java.util.Date createDate)  {         this.createDate = createDate;  }            public java.util.Date getUpdateDate()  {         return this.updateDate;  }      public void setUpdateDate(java.util.Date updateDate)  {         this.updateDate = updateDate;  }            public java.util.Date getLastLoginTime()  {         return this.lastLoginTime;  }      public void setLastLoginTime(java.util.Date lastLoginTime)  {         this.lastLoginTime = lastLoginTime;  }            public java.util.Date getReviewLastTime()  {         return this.reviewLastTime;  }      public void setReviewLastTime(java.util.Date reviewLastTime)  {         this.reviewLastTime = reviewLastTime;  }      @Override     public String toString()     {         return "id = " + this.id + ""               + "channel = " + this.channel + ""               + "name = " + this.name + ""               + "password = " + this.password + ""               + "nickName = " + this.nickName + ""               + "qiyeId = " + this.qiyeId + ""               + "status = " + this.status + ""               + "logo = " + this.logo + ""               + "email = " + this.email + ""               + "tel = " + this.tel + ""               + "info = " + this.info + ""               + "imsi = " + this.imsi + ""               + "imei = " + this.imei + ""               + "pckName = " + this.pckName + ""               + "headBg = " + this.headBg + ""               + "sex = " + this.sex + ""               + "birthDay = " + this.birthDay + ""               + "createDate = " + this.createDate + ""               + "updateDate = " + this.updateDate + ""               + "lastLoginTime = " + this.lastLoginTime + ""               + "reviewLastTime = " + this.reviewLastTime + "" ;  } 

		


public String getVipStr() {
	return vipStr;
}

public void setVipStr(String vipStr) {
	this.vipStr = vipStr;
}


public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}
public String getIcon(){
	if(headBg==null ||StringUtils.isEmpty(headBg)){
		return "/images/icon/0.png";
	}
	return "/images/icon/"+headBg+".png";
}

public int getVip() {
	return vip;
}
public void setVip(int vip) {
	this.vip = vip;
}


public int getByFlag() {
	return byFlag;
}


public void setByFlag(int byFlag) {
	this.byFlag = byFlag;
}


public int getAwardFlag() {
	return awardFlag;
}


public void setAwardFlag(int awardFlag) {
	this.awardFlag = awardFlag;
}


public String getQq() {
	return qq;
}


public void setQq(String qq) {
	this.qq = qq;
}


public String getWeibo() {
	return weibo;
}


public void setWeibo(String weibo) {
	this.weibo = weibo;
}


public int getCashCoupon() {
	return cashCoupon;
}


public void setCashCoupon(int cashCoupon) {
	this.cashCoupon = cashCoupon;
}


public int getLevel() {
	return level;
}


public void setLevel(int level) {
	this.level = level;
}
public String getWx() {
	return wx;
}
public void setWx(String wx) {
	this.wx = wx;
}
}