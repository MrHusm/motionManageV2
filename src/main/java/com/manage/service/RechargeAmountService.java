package com.manage.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;











import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.manage.constant.Constant;
import com.manage.dao.RechargeAmountDao;
import com.manage.domain.RechargeCondition;
import com.manage.model.Recharge_Amount;
import com.manage.model.Recharge_Repay;
import com.manage.util.RedisUtil;

@Service("rechargeAmountService")
public class RechargeAmountService{

    
    @Resource
    private RechargeAmountDao rechargeAmountDao;
    
    @Resource
    private RechargeRepayService rechargeRepayService;
    
	/**
	 * 根据ID获取充值赠送金额
	 * @param id
	 */
	public List<Recharge_Amount> getRechargeAmountByRechargeId(String rechargeId) {
		String sql = "select * from recharge_amount where rechargeRepayId = ? order by amount asc";
		return this.rechargeAmountDao.find(sql, rechargeId);
	}

	public void resetRedisCache() {
		Jedis jedis = RedisUtil.getJedis();
		try{
			String sql = "select *,(select money from cash_coupon where id=r.cashCouponId) as cashMoney "
					+ " from recharge_amount r where r.rechargeRepayId=? order by r.amount desc";
			RechargeCondition condition = new RechargeCondition();
			condition.setStartRow(0);
			condition.setPageSize(9999);
			List<Recharge_Repay> repays = rechargeRepayService.findRechargeRepayByCondition(condition);
			Set<String> newKeys = new HashSet<String>();
			for(Recharge_Repay repay : repays){
				List<Recharge_Amount> amounts = this.rechargeAmountDao.find(sql, repay.getId());
				if(amounts != null && amounts.size() > 0){
					String key = String.format(Constant.redis_Key_getRechargeAmountList, repay.getId()); 
					jedis.set(key.getBytes(), RedisUtil.serialize(amounts));
					newKeys.add(key);
				}
			}
			Set<String> keys = jedis.keys(String.format(Constant.redis_Key_getRechargeAmountList, "*"));
			for(String key : keys){
				if(!newKeys.contains(key)){
					jedis.del(key.getBytes());
					System.out.println("redis删除的amount_key："+key);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			RedisUtil.closeJedis(jedis);
		}		
	}
}