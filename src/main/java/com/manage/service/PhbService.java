package com.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
























import redis.clients.jedis.Jedis;

import com.manage.constant.Constant;
import com.manage.dao.PhbDao;
import com.manage.dao.QdDao;
import com.manage.domain.PhbCondition;
import com.manage.model.Bind_Repay;
import com.manage.model.Channel_Paihangbang;
import com.manage.model.Channel_Paihangbang_Qd;
import com.manage.model.Channel_Paihangbang_Qudong;
import com.manage.util.RedisUtil;

@Service("phbService")
public class PhbService {

	@Resource
	private PhbDao phbDao;
	@Resource
	private QdDao qdDao;
	
	/**
	 * 根据条件查询排行榜主榜单列表
	 * @param condition
	 * @return
	 */
	public List<Channel_Paihangbang> findPhbByCondition(PhbCondition condition){
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		
		StringBuffer sql = new StringBuffer("select * from channel_paihangbang where 1 = 1 and flag = 0 ");
		
		List<Object> args = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ?");
			args.add(name);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Channel_Paihangbang> list = this.phbDao.find(sql.toString(), args.toArray());
		return list;
	}
	/**
	 * 查询排行榜列表最小id数据
	 * @param condition
	 * @return
	 */
	public String getLastPhbId(){
		StringBuffer sql = new StringBuffer("select * from channel_paihangbang order by id desc LIMIT 1 ");
		List<Channel_Paihangbang> find = this.phbDao.find(sql.toString());
		String id ="";
		for (Channel_Paihangbang channel_Paihangbang : find) {
			id  = channel_Paihangbang.getId();
		}
		
		
		return id;
	}
	
	
	
	/**
	 * 根据条件查询排行榜主榜单数量
	 * @param condition
	 * @return
	 */
	public int findCountByCondition(PhbCondition condition){
		String name = condition.getName();
		StringBuffer sql = new StringBuffer("select count(*) from channel_paihangbang where 1 = 1 and flag = 0 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ?");
			args.add(name);
		}
		return this.phbDao.findForInt(sql.toString(), args.toArray());
	}
	
	public List<Channel_Paihangbang_Qudong> findQdList(){
		StringBuffer sql = new StringBuffer("select * from channel_paihangbang_qudong ");
		List<Channel_Paihangbang_Qudong> list = qdDao.find(sql.toString());
		return list;
	}
	
	/**
	 * 根据条件查询排行榜子榜单列表
	 * @param condition
	 * @return
	 */
	public List<Channel_Paihangbang> findzPhbByCondition(String id,PhbCondition condition){
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		
		List<Channel_Paihangbang> list =null;
		StringBuffer sql = new StringBuffer("select bdids from channel_paihangbang t2 where id= ?");
		List<Object> args = new ArrayList<Object>();
		args.add(id);
		List<Channel_Paihangbang> find = this.phbDao.find(sql.toString(), args.toArray());
		String bdids = "";
		for (Channel_Paihangbang channel_Paihangbang : find) {
			bdids = channel_Paihangbang.getBdIds();
		}
		if(!("".equals(bdids))){
			String[] split = bdids.split(",");
			StringBuffer sql1 = new StringBuffer("select * from  channel_paihangbang where 1=1 and flag = 1 ");
			List<Object> args1 = new ArrayList<Object>();
			for (int i = 0; i < split.length; i++) {
				if(i==0){
					if(StringUtils.isNotBlank(split[i])){
						sql1.append(" and id = ?");
						args1.add(split[i]);
					}
				}else{
					if(StringUtils.isNotBlank(split[i])){
						sql1.append(" or id = ?");
						args1.add(split[i]);
					}
				}
			}
			
			if(StringUtils.isNotBlank(name)){
				sql1.append(" and name like ?");
				args1.add(name);
			}
			sql1.append(" order by id desc limit ?,?");
			args1.add(start);
			args1.add(limit);
			list = this.phbDao.find(sql1.toString(), args1.toArray());
		}else{
			list = new ArrayList();
		}
		
		return list;
	}
	
	/**
	 * 根据条件查询排行榜子榜单数量
	 * @param condition
	 * @return
	 */
	public int findCountByConditionz(String id,PhbCondition condition){
		String name = condition.getName();
		
		StringBuffer sql = new StringBuffer("select bdids from channel_paihangbang t2 where id= ?");
		List<Object> args = new ArrayList<Object>();
		args.add(id);
		List<Channel_Paihangbang> find = this.phbDao.find(sql.toString(), args.toArray());
		String bdids = "";
		for (Channel_Paihangbang channel_Paihangbang : find) {
			bdids = channel_Paihangbang.getBdIds();
		}
		if(!("".equals(bdids))){
		String[] split = bdids.split(",");
		StringBuffer sql1 = new StringBuffer("select count(*) from  channel_paihangbang where 1=1 and flag = 1 ");
		List<Object> args1 = new ArrayList<Object>();
		for (int i = 0; i < split.length; i++) {
			if(i==0){
				if(StringUtils.isNotBlank(split[i])){
					sql1.append(" and id = ?");
					args1.add(split[i]);
				}
			}else{
				if(StringUtils.isNotBlank(split[i])){
					sql1.append(" or id = ?");
					args1.add(split[i]);
				}
			}
		}
		if(StringUtils.isNotBlank(name)){
			sql1.append(" and name like  ? ");
			args1.add(name);
		}
		return this.phbDao.findForInt(sql1.toString(), args1.toArray());
		}else{
			return 0;
		}
	
	}
	/**
	 * 保存排行榜
	 * @param phb
	 */
	public void savePhb(Channel_Paihangbang phb){
		this.phbDao.insert(phb);
		//重置redis缓存
		this.resetRedisCache();
	}
	
	/**
	 * 修改排行榜
	 * @param phb
	 */
	public void updatePhb(Channel_Paihangbang phb) {
		String sql = "update `channel_paihangbang` set `name`=?,  `icon`=?, `dataType`=?, `css`=?,"
				+ " `intro`=?,  `cc`=?, `qdId`=?,   `idx`=?,  `updateDate`=?,`bids` = ?,`bdIds` = ? where `id` = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(phb.getName());
		list.add(phb.getIcon());
		list.add(phb.getDataType());
		list.add(phb.getCss());
		list.add(phb.getIntro());
		list.add(phb.getCc());
		list.add(phb.getQdId());
		list.add(phb.getIdx());
		list.add(phb.getUpdateDate());
		list.add(phb.getBids());
		list.add(phb.getBdIds());
		list.add(phb.getId());
		this.phbDao.update(sql, list.toArray());
		//重置redis缓存
		this.resetRedisCache();
	}
	
	/**
	 * 删除图书
	 * @param phb
	 */
	public void updatePhbBook(Channel_Paihangbang phb) {
		String sql = "update `channel_paihangbang` set `name`=?,  `icon`=?, `dataType`=?, `css`=?,"
				+ " `intro`=?,  `cc`=?, `qdId`=?,   `idx`=?,`bids` =? ,   `updateDate`=? where `id` = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(phb.getName());
		list.add(phb.getIcon());
		list.add(phb.getDataType());
		list.add(phb.getCss());
		list.add(phb.getIntro());
		list.add(phb.getCc());
		list.add(phb.getQdId());
		list.add(phb.getIdx());
		list.add(phb.getBids());
		list.add(phb.getUpdateDate());
		list.add(phb.getId());
		this.phbDao.update(sql, list.toArray());
		//重置redis缓存
		this.resetRedisCache();
	}
	/**
	 * 
	 * 删除排行榜
	 * @param id
	 */
	public void deletePhb(String id) {
		if(!DataUtil.isEmpty(id)){
			this.phbDao.delete("channel_paihangbang", "id", id);
			//重置redis缓存
			this.resetRedisCache();
		}
	}
	
	/**
	 * 根据榜单Id查询出驱动排序规则ID
	 */
	public String getPxidByid(String phbId){
	    
	    StringBuffer sql = new StringBuffer("select * from channel_paihangbang_qudong where id =(select qdId from channel_paihangbang where id=? ) LIMIT 1 ");
		List<Channel_Paihangbang_Qudong> find = this.qdDao.find(sql.toString(),phbId);
		String pxid ="";
		for (Channel_Paihangbang_Qudong channel_Paihangbang_qudong : find) {
			pxid  = String.valueOf(channel_Paihangbang_qudong.getRule());
		}
		return pxid;
				
	}
	
	/**
	 * 根据ID获取排行榜
	 * @param id
	 */
	public Channel_Paihangbang getPhbById(String id) {
		String sql = "select * from channel_paihangbang where id = ?";
		return this.phbDao.findOne(sql, id);
	}
	/**
	 * 重置redis缓存
	 */
	public void resetRedisCache(){
		RedisUtil util = new RedisUtil();
		Jedis jedis = util.getJedis();
		try{
			String phbSql = "select * from channel_paihangbang where 1 = 1  ";
			List<Map<String,Object>> phbList = this.phbDao.findForList(phbSql, null);
			if(phbList !=null && phbList.size()>0){
				jedis.set(Constant.redis_Key_getPhbList.getBytes(), RedisUtil.serialize(phbList));
			}
		}finally{
			util.closeJedis(jedis);
		}
	}
	
}
