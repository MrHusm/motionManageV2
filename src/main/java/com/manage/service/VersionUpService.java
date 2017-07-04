package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.VersionUpDao;
import com.manage.domain.VersionUpCondition;
import com.manage.model.Channel_Version;

@Service("versionUpService")
public class VersionUpService{

    @Resource
    private VersionUpDao versionUpDao;
    
    /**
     * 根据条件查询版本升级列表
     * @param condition
     * @return
     */
	public List<Channel_Version> findVersionUpByCondition(VersionUpCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String version = condition.getVersion();
		String channelId = condition.getChannelId();
		StringBuffer sql = new StringBuffer("select * from channel_version where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(version)){
			sql.append(" and version = ? ");
			args.add(version);
		}
		if(StringUtils.isNotBlank(channelId)){
			sql.append(" and channelId = ? ");
			args.add(channelId);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<Channel_Version> list = this.versionUpDao.find(sql.toString(), args.toArray());
		return list;
	}

	/**
	 * 根据条件查询版本升级数量
	 * @param condition
	 */
	public int findCountByCondition(VersionUpCondition condition) {
		String version = condition.getVersion();
		String channelId = condition.getChannelId();
		StringBuffer sql = new StringBuffer("select count(*) from  channel_version where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(version)){
			sql.append(" and version = ? ");
			args.add(version);
		}
		if(StringUtils.isNotBlank(channelId)){
			sql.append(" and channelId = ? ");
			args.add(channelId);
		}
		return this.versionUpDao.findForInt(sql.toString(), args.toArray());
	}

	/**
	 * 保存版本升级
	 * @param bind
	 */
	public void saveVersionUp(Channel_Version version) {
		this.versionUpDao.insert(version);
	}
	
	/**
	 * 修改版本升级
	 * @param bind
	 */
	public void updateVersionUp(Channel_Version verisonUp) {
		String sql = "update channel_version set versionCode=?,`description`=? ,version=? where channelId=?";
		List<Object> list = new ArrayList<Object>();
		list.add(verisonUp.getVersionCode());
		list.add(verisonUp.getDescription());
		list.add(verisonUp.getVersion());
		list.add(verisonUp.getChannelId());
		this.versionUpDao.update(sql, list.toArray());
	}

	/**
	 * 
	 * 删除版本升级
	 * @param id
	 */
	public void deleteVersionUp(String id) {
		if(!DataUtil.isEmpty(id)){
			this.versionUpDao.delete("channel_version", "id", id);
		}
	}

	/**
	 * 根据ID获取版本升级
	 * @param id
	 */
	public Channel_Version getVersionUpById(String id) {
		String sql = "select * from channel_version where id = ?";
		return this.versionUpDao.findOne(sql, id);
	}
}
