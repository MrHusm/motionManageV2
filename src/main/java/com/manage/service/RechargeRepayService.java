package com.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.manage.constant.Constant;
import com.manage.dao.RechargeAmountDao;
import com.manage.dao.RechargeRepayDao;
import com.manage.domain.RechargeCondition;
import com.manage.model.Channel;
import com.manage.model.Channel_Version_V3;
import com.manage.model.Recharge_Amount;
import com.manage.model.Recharge_Repay;
import com.manage.util.RedisUtil;

@Service("rechargeRepayService")
public class RechargeRepayService{

    @Resource
    private RechargeRepayDao rechargeRepayDao;
    
    @Resource
    private RechargeAmountDao rechargeAmountDao;
    
    @Resource
    private ChannelService channelService;
    
    @Resource
    private VersionService versionService;
    
    /**
     * 根据条件查询充值赠送列表
     * @param condition
     * @return
     */
	public List<Recharge_Repay> findRechargeRepayByCondition(RechargeCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		String rechargeType = condition.getRechargeType();
		String repayType = condition.getRepayType();
		String activityType = condition.getActivityType();
		String channelId = condition.getChannelId();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String firstFlag = condition.getFirstFlag();
		StringBuffer sql = new StringBuffer("select * from recharge_repay where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ? ");
			args.add(name);
		}
		if(StringUtils.isNotBlank(activityType)){
			sql.append(" and activityType = ? ");
			args.add(activityType);
		}
		if(StringUtils.isNotBlank(repayType)){
			sql.append(" and repayType = ?");
			args.add(repayType);
		}
		if(StringUtils.isNotBlank(rechargeType)){
			sql.append(" and rechargeType = ?");
			args.add(rechargeType);
		}
		if(StringUtils.isNotBlank(channelId)){
			sql.append(" and channelIds like ? ");
			args.add("%"+channelId+"%");
		}
		if(StringUtils.isNotBlank(startDate)){
			sql.append(" and startDate <= ? ");
			args.add(startDate);
		}
		if(StringUtils.isNotBlank(endDate)){
			sql.append(" and endDate >= ? ");
			args.add(endDate);
		}
		if(StringUtils.isNotBlank(firstFlag)){
			sql.append(" and firstFlag = ? ");
			args.add(firstFlag);
		}
		
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Recharge_Repay> list = this.rechargeRepayDao.find(sql.toString(), args.toArray());
		return list;
	}

	/**
	 * 根据条件查询充值赠送数量
	 * @param condition
	 */
	public int findCountByCondition(RechargeCondition condition) {
		String name = condition.getName();
		String rechargeType = condition.getRechargeType();
		String repayType = condition.getRepayType();
		String activityType = condition.getActivityType();
		String channelId = condition.getChannelId();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String firstFlag = condition.getFirstFlag();
		StringBuffer sql = new StringBuffer("select count(*) from  recharge_repay where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ? ");
			args.add(name);
		}
		if(StringUtils.isNotBlank(activityType)){
			sql.append(" and activityType = ? ");
			args.add(activityType);
		}
		if(StringUtils.isNotBlank(repayType)){
			sql.append(" and repayType = ?");
			args.add(repayType);
		}
		if(StringUtils.isNotBlank(rechargeType)){
			sql.append(" and rechargeType = ?");
			args.add(rechargeType);
		}
		if(StringUtils.isNotBlank(channelId)){
			sql.append(" and channelIds like ? ");
			args.add("%"+channelId+"%");
		}
		if(StringUtils.isNotBlank(startDate)){
			sql.append(" and startDate <= ? ");
			args.add(startDate);
		}
		if(StringUtils.isNotBlank(endDate)){
			sql.append(" and endDate >= ? ");
			args.add(endDate);
		}
		if(StringUtils.isNotBlank(firstFlag)){
			sql.append(" and firstFlag = ? ");
			args.add(firstFlag);
		}
		return this.rechargeRepayDao.findForInt(sql.toString(), args.toArray());
	}

	/**
	 * 保存充值赠送
	 * @param recharge
	 */
	public void saveRechargeAndAmount(Recharge_Repay recharge,List<Recharge_Amount> list) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(this.rechargeRepayDao.getJdbc(0));
		simpleJdbcInsert.withTableName("recharge_repay");
		long id = (Long)this.rechargeRepayDao.insertAndReturnId(simpleJdbcInsert, recharge);
		for(Recharge_Amount rechargeAmount : list){
			rechargeAmount.setRechargeRepayId(String.valueOf(id));
			this.rechargeAmountDao.insert(rechargeAmount);
		}
		
		//重置redis缓存
		//this.resetRedisCache();
	}
	
	/**
	 * 修改充值赠送
	 * @param recharge
	 */
	public void updateRechargeAndAmount(Recharge_Repay recharge,List<Recharge_Amount> list) {
		String sqlRecharge = "update `recharge_repay` set `name`=?,`repayType`=?,`activityType`=?,`startDate`=?,`endDate`=?,`version`=?,`channelType`=?,`channelIds`=?,`isCashMoney`=?,`maxMoney`=?,`content`=?,`firstContent`=?,`updateDate`=? where id=?";
		List<Object> args = new ArrayList<Object>();
		args.add(recharge.getName());
		args.add(recharge.getRepayType());
		args.add(recharge.getActivityType());
		args.add(recharge.getStartDate());
		args.add(recharge.getEndDate());
		args.add(recharge.getVersion());
		args.add(recharge.getChannelType());
		args.add(recharge.getChannelIds());
		args.add(recharge.getIsCashMoney());
		args.add(recharge.getMaxMoney());
		args.add(recharge.getContent());
		args.add(recharge.getFirstContent());
		args.add(recharge.getUpdateDate());
		args.add(recharge.getId());
		this.rechargeRepayDao.update(sqlRecharge, args.toArray());
		
		String sqlAmount = "update `recharge_amount` set `cashCouponId`=?,`isCashMoney`=?,`money`=?,`updateDate`=? where id=?";
		for(Recharge_Amount amount : list){
			args.clear();
			args.add(amount.getCashCouponId());
			args.add(amount.getIsCashMoney());
			args.add(amount.getMoney());
			args.add(amount.getUpdateDate());
			args.add(amount.getId());
			this.rechargeAmountDao.update(sqlAmount, args.toArray());
		}
		//重置redis缓存
		//this.resetRedisCache();
	}

	/**
	 * 
	 * 删除充值赠送
	 * @param id
	 */
	public void deleteRechargeAndAmount(String id) {
		if(!DataUtil.isEmpty(id)){
			this.rechargeRepayDao.delete("recharge_repay", "id", id);
			this.rechargeAmountDao.delete("recharge_amount", "rechargeRepayId", id);
		}
		
		//重置redis缓存
		//this.resetRedisCache();
	}

	/**
	 * 根据ID获取充值赠送
	 * @param id
	 */
	public Recharge_Repay getRechargeRepayById(String id) {
		String sql = "select * from recharge_repay where id = ?";
		return this.rechargeRepayDao.findOne(sql, id);
	}
	
	/**
	 * 重置redis缓存
	 */
	public void resetRedisCache(){
		//获取所有的渠道和版本
		String[] types = {"4"};
		List<Channel> cls = this.channelService.getChannels(types);
		List<Channel_Version_V3> cvs = this.versionService.getVersions();
		
		Jedis jedis = RedisUtil.getJedis();
		try{
			//免费版的渠道ID
			String freeCnidKey = Constant.redis_Key_free_cnid;
			List<String> freeChannelIds = this.channelService.getFreeChannelIds();
			jedis.set(freeCnidKey.getBytes(), RedisUtil.serialize(freeChannelIds));
			
			String redisKey = Constant.redis_Key_rechargeRepay_new;
			for(int i = 0; i <= 1; i++){
				for(int j = 1; j <=7; j++){
					Map<String,List<Recharge_Repay>> channels = new HashMap<String,List<Recharge_Repay>>();
					String repaySql = "select * from recharge_repay where startDate<=now() and endDate>=now() and firstFlag = ? and rechargeType=?";
					List<Recharge_Repay> repayList = this.rechargeRepayDao.find(repaySql, i,j);
					if(repayList !=null && repayList.size()>0){
						for(Recharge_Repay repay : repayList){
							String channelIds = repay.getChannelIds() == null ? "" : repay.getChannelIds();
							if(DataUtil.isEmpty(channelIds)){
								if(channels.get(null) == null){
									List<Recharge_Repay> list = new ArrayList<Recharge_Repay>();
									list.add(repay);
									channels.put(null, list);
								}else{
									channels.get(null).add(repay);
								}
							}else{
								String[] cids = channelIds.split(",");
								for(String cid : cids){
									cid = cid.trim();
									if(channels.get(cid) == null){
										List<Recharge_Repay> list = new ArrayList<Recharge_Repay>();
										list.add(repay);
										channels.put(cid, list);
									}else{
										channels.get(cid).add(repay);
									}
								}
							}
						}
					}
					
					Map<String,Recharge_Repay> result = new HashMap<String,Recharge_Repay>();
					
					for(String key : channels.keySet()) {
						List<Recharge_Repay> list = channels.get(key);
						Map<String,Recharge_Repay> versions = new HashMap<String,Recharge_Repay>();
						for(Recharge_Repay repay : list){
							String version = repay.getVersion() == null ? "" : repay.getVersion();
							//Date endDate = (Date)repay.get("endDate");
							Date updateDate = repay.getUpdateDate();
							if(DataUtil.isEmpty(version)){
								if(versions.get(null) == null){
									versions.put(null, repay);
								}else{
									Date resultUpdateDate = versions.get(null).getUpdateDate();
									if(updateDate.getTime()>resultUpdateDate.getTime()){
										versions.put(null, repay);
									}
								}
							}else{
								String[] vers = version.split(",");
								for(String ver : vers){
									if(versions.get(ver) == null){
										versions.put(ver, repay);
									}else{
										Date resultUpdateDate = versions.get(ver).getUpdateDate();
										if(updateDate.getTime()>resultUpdateDate.getTime()){
											versions.put(ver, repay);
										}
									}
								}
							}
						}
						
						for(String ver :versions.keySet()){
							result.put(key+"_"+ver, versions.get(ver));
						}
					}
					
					for(Channel c : cls){
						for(Channel_Version_V3 v : cvs){
							String key = String.valueOf(c.getChannel_id()) + "_" + v.getVcode();
							Recharge_Repay recharge = result.get(key);
							if(recharge == null){
								key = String.valueOf(c.getChannel_id()) + "_" + null;
								recharge = result.get(key);
								if(recharge == null){
									key = null + "_" + v.getVcode();
									recharge = result.get(key);
									if(recharge == null){
										key = null + "_" + null;
										recharge = result.get(key);
									}
								}
							}
							if(recharge != null){
								jedis.set(String.format(redisKey, String.valueOf(c.getChannel_id()),v.getVcode(),String.valueOf(i),String.valueOf(j)).getBytes(),RedisUtil.serialize(recharge));
								//System.out.println(String.format(redisKey, String.valueOf(c.getChannel_id()),v.getVcode(),String.valueOf(i),String.valueOf(j)));
							}else{
								jedis.del(String.format(redisKey, String.valueOf(c.getChannel_id()),v.getVcode(),String.valueOf(i),String.valueOf(j)).getBytes());
							}
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			RedisUtil.closeJedis(jedis);
		}
	}
}