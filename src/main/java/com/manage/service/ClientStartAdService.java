package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.ClientStartAdDao;
import com.manage.domain.ClientStartAdCondition;
import com.manage.model.Client_Start_Ad;

@Service
public class ClientStartAdService {
	@Resource
	private ClientStartAdDao clientStartAdDao;
	
	/**
	 * 根据条件查询启动图数量
	 * @param condition
	 */
	public int findCountByCondition(ClientStartAdCondition condition) {
		String name = condition.getName();
		String type = condition.getType();
		StringBuffer sql = new StringBuffer("select count(*) from client_start_ad where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		
		if(StringUtils.isNotBlank(type)){
			sql.append(" and type = ?");
			args.add(type);
		}
		return this.clientStartAdDao.findForInt(sql.toString(), args.toArray());
	}
    
    /**
     * 根据条件查询启动图列表
     * @param condition
     * @return
     */
	public List<Client_Start_Ad> findClientAdByCondition(ClientStartAdCondition condition) {
		String name = condition.getName();
		String type = condition.getType();
		StringBuffer sql = new StringBuffer("select *,CASE WHEN startDate <=NOW() AND endDate >= NOW() THEN 1 WHEN endDate < NOW() THEN 2 ELSE 0 END AS state from client_start_ad where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		if(StringUtils.isNotBlank(type)){
			sql.append(" and type = ?");
			args.add(type);
		}
		sql.append("order by id desc");
		List<Client_Start_Ad> list = this.clientStartAdDao.find(sql.toString(), args.toArray());
		return list;
	}
	/**
	 * 保存启动图
	 * @param banner
	 */
	public void saveClientAd(Client_Start_Ad clientAd) {
		this.clientStartAdDao.insert(clientAd);
	}

	/**
	 * 修改启动图
	 * @param bang
	 */
	public void updateClientAd(Client_Start_Ad clientAd) {
		String sql = "update `client_start_ad` set name=?,pauseTime=?,startDate=?,endDate=?,channelIds=?,versions=?,updateDate=now() where id=?";
		List<Object> args = new ArrayList<Object>();
		args.add(clientAd.getName());
		args.add(clientAd.getPauseTime());
		args.add(clientAd.getStartDate());
		args.add(clientAd.getEndDate());
		args.add(clientAd.getChannelIds());
		args.add(clientAd.getVersions());
		args.add(clientAd.getId());
		this.clientStartAdDao.update(sql, args.toArray());
	}

	/**
	 * 
	 * 删除启动图
	 * @param id
	 */
	public void deleteClientAdById(String id) {
		if(!DataUtil.isEmpty(id)){
			this.clientStartAdDao.delete("client_start_ad", "id", id);
		}
	}
	

	/**
	 * 根据ID获取启动图
	 * 启动图aram id
	 */
	public Client_Start_Ad getClientAdById(String id) {
		String sql = "select * from client_start_ad where id = ?";
		return this.clientStartAdDao.findOne(sql, id);
	}
}
