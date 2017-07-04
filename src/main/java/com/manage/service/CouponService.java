package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.CouponDao;
import com.manage.domain.CouponCondition;
import com.manage.model.Cash_Coupon;

@Service("couponService")
public class CouponService{

    @Resource
    private CouponDao couponDao;
    
    /**
     * 根据条件查询代金券列表
     * @param condition
     * @return
     */
	public List<Cash_Coupon> findCouponByCondition(CouponCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		String money = condition.getMoney();
		String type = condition.getType();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String day = condition.getDay();
		StringBuffer sql = new StringBuffer("select id,name,money,discount,"
			+ "type,startDate,endDate,day,createDate,updateDate "
			+ "from cash_coupon where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ? ");
			args.add(name);
		}
		if(StringUtils.isNotBlank(money)){
			sql.append(" and money = ? ");
			args.add(Integer.parseInt(money));
		}
		if(StringUtils.isNotBlank(type) && !"3".equals(type)){
			sql.append(" and type = ? ");
			args.add(Integer.parseInt(type));
		}
		if(StringUtils.isNotBlank(startDate)){
			sql.append(" and startDate <= ? ");
			args.add(startDate);
		}
		if(StringUtils.isNotBlank(endDate)){
			sql.append(" and endDate >= ? ");
			args.add(endDate);
		}
		if(StringUtils.isNotBlank(day)){
			sql.append(" and day = ? ");
			args.add(day);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Cash_Coupon> list = this.couponDao.find(sql.toString(), args.toArray());
		return list;
	}

	/**
	 * 根据条件查询代金券数量
	 * @param condition
	 */
	public int findCountByCondition(CouponCondition condition) {
		String name = condition.getName();
		String money = condition.getMoney();
		String type = condition.getType();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String day = condition.getDay();
		StringBuffer sql = new StringBuffer("select count(*) from cash_coupon where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ? ");
			args.add(name);
		}
		if(StringUtils.isNotBlank(money)){
			sql.append(" and money = ? ");
			args.add(Integer.parseInt(money));
		}
		if(StringUtils.isNotBlank(type) && !"3".equals(type)){
			sql.append(" and type = ? ");
			args.add(Integer.parseInt(type));
		}
		if(StringUtils.isNotBlank(startDate)){
			sql.append(" and startDate <= ? ");
			args.add(startDate);
		}
		if(StringUtils.isNotBlank(endDate)){
			sql.append(" and endDate >= ? ");
			args.add(endDate);
		}
		if(StringUtils.isNotBlank(day)){
			sql.append(" and day = ? ");
			args.add(day);
		}
		return this.couponDao.findForInt(sql.toString(), args.toArray());
	}

	/**
	 * 保存代金券
	 * @param coupon
	 */
	public void saveCoupon(Cash_Coupon coupon) {
		this.couponDao.insert(coupon);
	}
	
	/**
	 * 保存代金券
	 * @param coupon
	 */
	public void updateCoupon(Cash_Coupon coupon) {
		String sql = "update `cash_coupon` set `name`=?, `money`=?, `type`=?, `startDate`=?, `endDate`=?, `day`=?,`couponType`=?,`updateDate`=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(coupon.getName());
		list.add(coupon.getMoney());
		list.add(coupon.getType());
		list.add(coupon.getStartDate());
		list.add(coupon.getEndDate());
		list.add(coupon.getDay());
		list.add(coupon.getCouponType());
		list.add(coupon.getUpdateDate());
		list.add(coupon.getId());
		this.couponDao.update(sql, list.toArray());
	}

	/**
	 * 
	 * 删除代金券
	 * @param id
	 */
	public void deleteCoupon(String id) {
		if(!DataUtil.isEmpty(id)){
			this.couponDao.delete("cash_coupon", "id", id);
		}
	}

	/**
	 * 根据ID获取代金券
	 * @param id
	 */
	public Cash_Coupon getCouponById(String id) {
		String sql = "select * from cash_coupon where id = ?";
		return this.couponDao.findOne(sql, id);
	}
}
