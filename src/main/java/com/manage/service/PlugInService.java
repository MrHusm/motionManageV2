package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.PlugInDao;
import com.manage.domain.PlugInCondition;
import com.manage.model.Plug_In;

@Service("plugInService")
public class PlugInService{

    @Resource
    private PlugInDao plugInDao;
    
    /**
     * 根据条件查询插件列表
     * @param condition
     * @return
     */
	public List<Plug_In> findPlugInByCondition(PlugInCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		String type= condition.getType();
		StringBuffer sql = new StringBuffer("select * from user_center_extra.plug_in where 1 = 1 ");
		
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
		List<Plug_In> list = this.plugInDao.find(sql.toString(), args.toArray());
		return list;
	}

	/**
	 * 根据条件查询插件数量
	 * @param condition
	 */
	public int findCountByCondition(PlugInCondition condition) {
		String name = condition.getName();
		String type= condition.getType();
		StringBuffer sql = new StringBuffer("select count(*) from  user_center_extra.plug_in where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ? ");
			args.add("%"+name+"%");
		}
		if(StringUtils.isNotBlank(type)){
			sql.append(" and type = ? ");
			args.add(type);
		}
		
		return this.plugInDao.findForInt(sql.toString(), args.toArray());
	}

	/**
	 * 保存插件
	 * @param bind
	 */
	public void savePlugIn(Plug_In plugIn) {
		this.plugInDao.insert(plugIn);
	}
	
	/**
	 * 修改插件
	 * @param bind
	 */
	public void updatePlugIn(Plug_In plugIn) {
		String sql = "update user_center_extra.`plug_in` set `name`=?, `in_version`=?, `out_version`=?,"
				+ " `version`=?,cpu_type=?, `updateDate`=? where `id` = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(plugIn.getName());
		list.add(plugIn.getIn_version());
		list.add(plugIn.getOut_version());
		list.add(plugIn.getVersion());
		list.add(plugIn.getCpu_type());
		list.add(plugIn.getUpdateDate());
		list.add(plugIn.getId());
		this.plugInDao.update(sql, list.toArray());
	}

	/**
	 * 
	 * 删除插件
	 * @param id
	 */
	public void deletePlugIn(String id) {
		if(!DataUtil.isEmpty(id)){
			this.plugInDao.delete("user_center_extra.plug_in", "id", id);
		}
	}

	/**
	 * 根据ID获取插件
	 * @param id
	 */
	public Plug_In getPlugInById(String id) {
		String sql = "select * from user_center_extra.plug_in where id = ?";
		return this.plugInDao.findOne(sql, id);
	}
}
