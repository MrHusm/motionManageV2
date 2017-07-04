package com.manage.model;

import java.util.Date;

public class Recharge_Amount implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="recharge_amount";
	
	private String id;

	private Integer amount; //充值金额
	
	private String cashCouponId;//代金劵 id
	
	private String cashMoney;//代金券金额
	
	private String isCashMoney;//是否使用代金券金额 1 使用 2 不使用
	
	private Integer money; //赠送金额（单位：铜币）
	
	private Date createDate; //创建时间
	
	private Date updateDate; //更新时间
	
	private String rechargeRepayId;//充值赠送ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCashCouponId() {
		return cashCouponId;
	}

	public void setCashCouponId(String cashCouponId) {
		this.cashCouponId = cashCouponId;
	}
	
	public String getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(String cashMoney) {
		this.cashMoney = cashMoney;
	}

	public String getIsCashMoney() {
		return isCashMoney;
	}

	public void setIsCashMoney(String isCashMoney) {
		this.isCashMoney = isCashMoney;
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

	public String getRechargeRepayId() {
		return rechargeRepayId;
	}

	public void setRechargeRepayId(String rechargeRepayId) {
		this.rechargeRepayId = rechargeRepayId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
}