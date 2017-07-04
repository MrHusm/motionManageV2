package com.manage.domain;

public class LotteryCondition extends Condition{
    String prizeName = null;
    String userName = null;
    String userNickName = null;
    String userTel = null;
    String prizeType = null;
    public String getPrizeName() {
        return prizeName;
    }
    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserNickName() {
        return userNickName;
    }
    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
    public String getUserTel() {
        return userTel;
    }
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
    public String getPrizeType() {
        return prizeType;
    }
    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }
    
}
