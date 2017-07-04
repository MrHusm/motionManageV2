package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;




import com.manage.dao.QuDongDao;
import com.manage.domain.QdCondition;
import com.manage.model.Channel_Paihangbang_Qd;

@Service("qudongService")
public class QuDongService {

	@Resource
	private QuDongDao qudongDao;
	
	/**
	 * 根据条件查询驱动规则列表
	 * @param condition
	 * @return
	 */
	public List<Channel_Paihangbang_Qd> findQdByCondition(QdCondition condition){
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		
		StringBuffer sql = new StringBuffer("select * from channel_paihangbang_qd qd where 1=1   ");
		
		List<Object> args = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(name)){
			sql.append(" and qd.name = ?");
			args.add("%"+name+"%");
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Channel_Paihangbang_Qd> list = this.qudongDao.find(sql.toString(), args.toArray());
		return list;
	}
	
	/**
	 * 根据条件查询驱动规则数量
	 * @param condition
	 * @return
	 */
	public int findCountByCondition(QdCondition condition){
		String name = condition.getName();
		StringBuffer sql = new StringBuffer("select count(*) from Channel_Paihangbang_Qd where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name = ?");
			args.add("%"+name+"%");
		}
		return this.qudongDao.findForInt(sql.toString(), args.toArray());
	}
	
	/**
	 * 查询所有驱动
	 * @return
	 */
	public List<Channel_Paihangbang_Qd> findQudongList(){
		StringBuffer sql = new StringBuffer("select * from channel_paihangbang_qd");
		List<Channel_Paihangbang_Qd> list = qudongDao.find(sql.toString());
		return list;
	}

	/**
	 * 根据ID获取驱动
	 * @param valueOf
	 * @return
	 */
	public Channel_Paihangbang_Qd getQdById(String id) {
		return this.qudongDao.get("id", id);
	}
}
