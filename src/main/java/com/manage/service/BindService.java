package com.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.manage.constant.Constant;
import com.manage.dao.BindDao;
import com.manage.domain.BindCondition;
import com.manage.model.Bind_Repay;
import com.manage.util.RedisUtil;

@Service("bindService")
public class BindService{

    @Resource
    private BindDao bindDao;
    
    /**
     * 根据条件查询绑定赠送列表
     * @param condition
     * @return
     */
	public List<Bind_Repay> findBindByCondition(BindCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		String bindType= condition.getBindType();
		String repayType = condition.getRepayType();
		String channelId = condition.getChannelId();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String money = condition.getMoney();
		String byType = condition.getByType();
		StringBuffer sql = new StringBuffer("select * from bind_repay where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ? ");
			args.add(name);
		}
		if(StringUtils.isNotBlank(bindType)){
			sql.append(" and bindType = ? ");
			args.add(bindType);
		}
		if(StringUtils.isNotBlank(repayType)){
			sql.append(" and repayType = ? ");
			args.add(repayType);
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
		if(StringUtils.isNotBlank(money)){
			sql.append(" and money = ? ");
			args.add(money);
		}
		if(StringUtils.isNotBlank(byType)){
			sql.append(" and byType = ? ");
			args.add(byType);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Bind_Repay> list = this.bindDao.find(sql.toString(), args.toArray());
		return list;
	}

	/**
	 * 根据条件查询绑定赠送数量
	 * @param condition
	 */
	public int findCountByCondition(BindCondition condition) {
		String name = condition.getName();
		String bindType= condition.getBindType();
		String repayType = condition.getRepayType();
		String channelId = condition.getChannelId();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		String money = condition.getMoney();
		StringBuffer sql = new StringBuffer("select count(*) from  bind_repay where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ? ");
			args.add(name);
		}
		if(StringUtils.isNotBlank(bindType)){
			sql.append(" and bindType = ? ");
			args.add(bindType);
		}
		if(StringUtils.isNotBlank(repayType)){
			sql.append(" and repayType = ? ");
			args.add(repayType);
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
		if(StringUtils.isNotBlank(money)){
			sql.append(" and money = ? ");
			args.add(money);
		}
		return this.bindDao.findForInt(sql.toString(), args.toArray());
	}

	/**
	 * 保存绑定赠送
	 * @param bind
	 */
	public void saveBind(Bind_Repay bind) {
		this.bindDao.insert(bind);
		//重置redis缓存
		//this.resetRedisCache();
	}
	
	/**
	 * 修改绑定赠送
	 * @param bind
	 */
	public void updateBind(Bind_Repay bind) {
		String sql = "update `bind_repay` set `name`=?, `bindType`=?, `repayType`=?, `cashCouponId`=?, `isCashMoney`=?, `money`=?,"
				+ " `startDate`=?, `endDate`=?, `version`=?, `channelType`=?, `channelIds`=?, `updateDate`=?, `byType`=?, `byId`=?, `day`=?, `content`=? where `id` = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(bind.getName());
		list.add(bind.getBindType());
		list.add(bind.getRepayType());
		list.add(bind.getCashCouponId());
		list.add(bind.getIsCashMoney());
		list.add(bind.getMoney());
		list.add(bind.getStartDate());
		list.add(bind.getEndDate());
		list.add(bind.getVersion());
		list.add(bind.getChannelType());
		list.add(bind.getChannelIds());
		list.add(bind.getUpdateDate());
		list.add(bind.getByType());
		list.add(bind.getById());
		list.add(bind.getDay());
		list.add(bind.getContent());
		list.add(bind.getId());
		this.bindDao.update(sql, list.toArray());
		//重置redis缓存
		//this.resetRedisCache();
	}

	/**
	 * 
	 * 删除绑定赠送
	 * @param id
	 */
	public void deleteBind(String id) {
		if(!DataUtil.isEmpty(id)){
			this.bindDao.delete("bind_repay", "id", id);
			//重置redis缓存
			//this.resetRedisCache();
		}
	}

	/**
	 * 根据ID获取绑定赠送
	 * @param id
	 */
	public Bind_Repay getBindById(String id) {
		String sql = "select * from bind_repay where id = ?";
		return this.bindDao.findOne(sql, id);
	}
	
	/**
	 * 重置redis缓存
	 */
	public void resetRedisCache(){
		Jedis jedis = RedisUtil.getJedis();
		try{
			String redisKey = Constant.redis_Key_bindRepay;
			for(int i = 1;i<=4;i++){
				Map<String,List<Map<String,Object>>> channels = new HashMap<String,List<Map<String,Object>>>();
				String bindSql = "select * from bind_repay where startDate<=now() and endDate>=now() and bindType=? ";
				List<Map<String,Object>> bindList = this.bindDao.findForList(bindSql, i);
				if(bindList !=null && bindList.size()>0){
					for(Map<String,Object> bind : bindList){
						String channelIds = bind.get("channelIds") == null ? "" : (String)bind.get("channelIds");
						if(DataUtil.isEmpty(channelIds)){
							if(channels.get(null) == null){
								List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
								list.add(bind);
								channels.put(null, list);
							}else{
								channels.get(null).add(bind);
							}
						}else{
							String[] cids = channelIds.split(",");
							for(String cid : cids){
								cid = cid.trim();
								if(channels.get(cid) == null){
									List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
									list.add(bind);
									channels.put(cid, list);
								}else{
									channels.get(cid).add(bind);
								}
							}
						}
					}
				}
				for(String key : channels.keySet()) {
					List<Map<String,Object>> list = channels.get(key);
					Map<String,Map<String,Object>> versions = new HashMap<String,Map<String,Object>>();
					for(Map<String,Object> bind : list){
						String version = bind.get("version") == null ? "" : (String)bind.get("version");
						//Date endDate = (Date)bind.get("endDate");
						Date updateDate = (Date)bind.get("updateDate");
						if(DataUtil.isEmpty(version)){
							if(versions.get(null) == null){
								versions.put(null, bind);
							}else{
								Date resultUpdateDate = (Date)versions.get(null).get("updateDate");
								if(updateDate.getTime()>resultUpdateDate.getTime()){
									versions.put(null, bind);
								}
							}
						}else{
							String[] vers = version.split(",");
							for(String ver : vers){
								if(versions.get(ver) == null){
									versions.put(ver, bind);
								}else{
									Date resultUpdateDate = (Date)versions.get(ver).get("updateDate");
									if(updateDate.getTime()>resultUpdateDate.getTime()){
										versions.put(ver, bind);
									}
								}
							}
						}
					}
					
					for(String ver :versions.keySet()){
						//放入redis缓存12小时
						jedis.setex(String.format(redisKey, key,ver,i).getBytes(),60*60*24, RedisUtil.serialize(versions.get(ver)));
						System.out.println("bindkey-----------------------------"+String.format(redisKey, key,ver,i));
						//jedis.setex(String.format(redisKey, key,ver,i).getBytes(),60*3, RedisUtil.serialize(versions.get(ver)));
					}
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			RedisUtil.closeJedis(jedis);
		}
	}
}
