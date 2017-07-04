package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.RemindDao;
import com.manage.domain.RemindCondition;
import com.manage.model.Exit_Remind;

@Service("remindService")
public class RemindService{

    @Resource
    private RemindDao remindDao;
    
    /**
     * 根据条件查询退出提醒列表
     * @param condition
     * @return
     */
	public List<Exit_Remind> findRemindByCondition(RemindCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		String type= condition.getType();
		StringBuffer sql = new StringBuffer("select * from user_center_extra.exit_remind where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ? ");
			args.add("%"+name+"%");
		}
		if(StringUtils.isNotBlank(type)){
			sql.append(" and type = ? ");
			args.add(type);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Exit_Remind> list = this.remindDao.find(sql.toString(), args.toArray());
		return list;
	}

	/**
	 * 根据条件查询退出提醒数量
	 * @param condition
	 */
	public int findCountByCondition(RemindCondition condition) {
		String name = condition.getName();
		String type= condition.getType();
		String startDate = condition.getStartDate();
		String endDate = condition.getEndDate();
		StringBuffer sql = new StringBuffer("select count(*) from  user_center_extra.exit_remind where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ? ");
			args.add("%"+name+"%");
		}
		if(StringUtils.isNotBlank(type)){
			sql.append(" and type = ? ");
			args.add(type);
		}
		if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){
			sql.append(" and ((startDate <= ? and endDate >= ?) or (startDate >= ? and startDate < ?) or (endDate > ? and endDate <= ?))");
			args.add(startDate);
			args.add(endDate);
			args.add(startDate);
			args.add(endDate);
			args.add(startDate);
			args.add(endDate);
		}
		
		System.out.println(sql.toString());
		return this.remindDao.findForInt(sql.toString(), args.toArray());
	}

	/**
	 * 保存退出提醒
	 * @param bind
	 */
	public void saveRemind(Exit_Remind remind) {
		this.remindDao.insert(remind);
	}
	
	/**
	 * 修改退出提醒
	 * @param bind
	 */
	public void updateRemind(Exit_Remind remind) {
		String sql = "update user_center_extra.`exit_remind` set `name`=?, `content`=?, `btn_content`=?,"
				+ " `btn_url`=?, `updateDate`=? where `id` = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(remind.getName());
		list.add(remind.getContent());
		list.add(remind.getBtnContent());
		list.add(remind.getBtnUrl());
		list.add(remind.getUpdateDate());
		list.add(remind.getId());
		this.remindDao.update(sql, list.toArray());
	}

	/**
	 * 
	 * 删除退出提醒
	 * @param id
	 */
	public void deleteRemind(String id) {
		if(!DataUtil.isEmpty(id)){
			this.remindDao.delete("user_center_extra.exit_remind", "id", id);
		}
	}

	/**
	 * 根据ID获取退出提醒
	 * @param id
	 */
	public Exit_Remind getRemindById(String id) {
		String sql = "select * from user_center_extra.exit_remind where id = ?";
		return this.remindDao.findOne(sql, id);
	}
}
