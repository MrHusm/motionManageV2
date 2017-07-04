package com.manage.model;

public class Prize_Exchange implements java.io.Serializable {
    private static final long serialVersionUID = -8202599156624191916L;
    public static final String TABLE_NAME = "prize_exchange";
    public String id;
    public String hdId;  // 抽奖活动id
    public String hdName;  // 抽奖名
	public String prizeId;  // 奖品ID
	public String prizeName;  //奖品名称
	public String userId;  // 用户ID
	public String prizeType;  //奖品类型
	public String maxNum;  //奖品最大数量/代金券ID
	public String trueName;  //真实姓名
	public String linktel;  //联系电话
	public String address;  //邮寄地址
	public String beMobleTel;  //被充值手机
	public String telWd;  //手机网段
	public String bak;  //备注
	public String dhTime;  //兑换时间
	public String dhstatue;  //兑换状态 0:未领取；1:已领取奖励；
	public String dhinfo1;  //兑换信息1
	public String dhinfo2;  //兑换信息2
	public String dhinfo3;  //兑换信息3
	public String updateTime;  
	public String createTime;
	public String userName;
	public String nickName;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getHdId() {
        return hdId;
    }
    public void setHdId(String hdId) {
        this.hdId = hdId;
    }
    public String getHdName() {
        return hdName;
    }
    public void setHdName(String hdName) {
        this.hdName = hdName;
    }
    public String getPrizeId() {
        return prizeId;
    }
    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }
    public String getPrizeName() {
        return prizeName;
    }
    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPrizeType() {
        return prizeType;
    }
    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }
    public String getMaxNum() {
        return maxNum;
    }
    public void setMaxNum(String maxNum) {
        this.maxNum = maxNum;
    }
    public String getTrueName() {
        return trueName;
    }
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
    public String getLinktel() {
        return linktel;
    }
    public void setLinktel(String linktel) {
        this.linktel = linktel;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBeMobleTel() {
        return beMobleTel;
    }
    public void setBeMobleTel(String beMobleTel) {
        this.beMobleTel = beMobleTel;
    }
    public String getTelWd() {
        return telWd;
    }
    public void setTelWd(String telWd) {
        this.telWd = telWd;
    }
    public String getBak() {
        return bak;
    }
    public void setBak(String bak) {
        this.bak = bak;
    }
    public String getDhTime() {
        return dhTime;
    }
    public void setDhTime(String dhTime) {
        this.dhTime = dhTime;
    }
    public String getDhstatue() {
        return dhstatue;
    }
    public void setDhstatue(String dhstatue) {
        this.dhstatue = dhstatue;
    }
    public String getDhinfo1() {
        return dhinfo1;
    }
    public void setDhinfo1(String dhinfo1) {
        this.dhinfo1 = dhinfo1;
    }
    public String getDhinfo2() {
        return dhinfo2;
    }
    public void setDhinfo2(String dhinfo2) {
        this.dhinfo2 = dhinfo2;
    }
    public String getDhinfo3() {
        return dhinfo3;
    }
    public void setDhinfo3(String dhinfo3) {
        this.dhinfo3 = dhinfo3;
    }
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
