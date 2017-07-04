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
import com.manage.dao.QdDao;
import com.manage.domain.QdCondition;
import com.manage.model.Channel_Paihangbang_Qudong;
import com.manage.util.RedisUtil;

@Service("qdService")
public class QdService {

	@Resource
	private QdDao qdDao;
	
	/**
	 * 根据条件查询驱动规则列表
	 * @param condition
	 * @return
	 */
	public List<Channel_Paihangbang_Qudong> findQdByCondition(QdCondition condition){
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		
		StringBuffer sql = new StringBuffer("select qd.id,qd.categoryId,qd.`name`,qd.qdType,qd.startDate,qd.endDate,bookcategory_v3.`name` AS 'category',bookcategory_v3.authorCategory AS 'authory' from channel_paihangbang_qudong AS qd left join bookcategory_v3    on  qd.categoryId=bookcategory_v3.id where 1=1   ");
		
		List<Object> args = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(name)){
			sql.append(" and qd.name = ?");
			args.add(name);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Channel_Paihangbang_Qudong> list = this.qdDao.find(sql.toString(), args.toArray());
		return list;
	}
	
	/**
	 * 根据条件查询驱动规则数量
	 * @param condition
	 * @return
	 */
	public int findCountByCondition(QdCondition condition){
		String name = condition.getName();
		StringBuffer sql = new StringBuffer("select count(*) from channel_paihangbang_qudong where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ?");
			args.add(name);
		}
		return this.qdDao.findForInt(sql.toString(), args.toArray());
	}
	
	
	/**
	 * 保存驱动规则
	 * @param qd
	 */
	public void saveQd(Channel_Paihangbang_Qudong qd){
		this.qdDao.insert(qd);
		//重置redis缓存
		this.resetRedisCache();
	}
	
	/**
	 * 修改驱动规则
	 * @param qd
	 */
	public void updateQd(Channel_Paihangbang_Qudong qd) {
		String sql = "update `channel_paihangbang_qudong` set `name`=?,  `categoryId`=?, `qdType`=?, `free`=?, `status`=?, `startDate`=?, `endDate`=?, `rule`=?,  `updateDate`=? ,`datee` =? where `id` = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(qd.getName());
		list.add(qd.getCategoryId());
		list.add(qd.getQdType());
		list.add(qd.getFree());
		list.add(qd.getStatus());
		list.add(qd.getStartDate());
		list.add(qd.getEndDate());
		list.add(qd.getRule());
		list.add(qd.getUpdateDate());
		list.add(qd.getDatee());
		list.add(qd.getId());
		this.qdDao.update(sql, list.toArray());

	}
	
	
	/**
	 * 
	 * 删除驱动规则
	 * @param id
	 */
	public void deleteQd(String id) {
		if(!DataUtil.isEmpty(id)){
			this.qdDao.delete("channel_paihangbang_qudong", "id", id);
			//重置redis缓存
			this.resetRedisCache();
		}
	}
	
	
	/**
	 * 根据ID获取驱动规则
	 * @param id
	 */
	public Channel_Paihangbang_Qudong getQdById(String id) {
		String sql = "select * from channel_paihangbang_qudong where id = ?";
		Channel_Paihangbang_Qudong findOne = this.qdDao.findOne(sql, id);
		if(findOne.getCategoryId()!=0){//不限

		if(findOne.getCategoryId()==1){//男频不限
			findOne.setTwoId(1);
		}else if(findOne.getCategoryId()==2){//女频不限
			findOne.setTwoId(2);
		}else if(findOne.getCategoryId()==3){//出版物不限
			findOne.setTwoId(3);
		}else{
			String s = String.valueOf(findOne.getCategoryId());
			if(s.substring(s.length()-2,s.length()).equals("00")){
				if(findOne.getCategoryId()<2000){//男频
					findOne.setTwoId(1);
				}else if(findOne.getCategoryId()>2000&&findOne.getCategoryId()<3000){//女频
					findOne.setTwoId(2);
				}else if(findOne.getCategoryId()>3000&&findOne.getCategoryId()<4000){//出版物
					findOne.setTwoId(3);
				}
			}
		}
		}
		return  findOne;
		
	}
	
	/**
	 * 重置redis缓存
	 */
	public void resetRedisCache(){
		RedisUtil util = new RedisUtil();
		Jedis jedis = util.getJedis();
		try{
			String phbSql = "select * from channel_paihangbang_qudong where 1 = 1  ";
			List<Map<String,Object>> phbList = this.qdDao.findForList(phbSql, null);
			if(phbList !=null && phbList.size()>0){
				jedis.set(Constant.redis_Key_getPhbQdList.getBytes(), RedisUtil.serialize(phbList));
			}
		}finally{
			util.closeJedis(jedis);
		}
	}
}
