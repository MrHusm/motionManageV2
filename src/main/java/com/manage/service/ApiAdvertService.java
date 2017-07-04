package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.ApiAdvertDao;
import com.manage.domain.ApiAdvertCondition;
import com.manage.model.Api_Advert;

@Service
public class ApiAdvertService {
	@Resource
	private ApiAdvertDao apiAdvertDao;
	
	/**
	 * 根据条件查询广告API数量
	 * @param condition
	 */
	public int findCountByCondition(ApiAdvertCondition condition) {
		String name = condition.getName();
		StringBuffer sql = new StringBuffer("select count(*) from api_advert where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		return this.apiAdvertDao.findForInt(sql.toString(), args.toArray());
	}
    
    /**
     * 根据条件查询广告API列表
     * @param condition
     * @return
     */
	public List<Api_Advert> findApiAdByCondition(ApiAdvertCondition condition) {
		String name = condition.getName();
		StringBuffer sql = new StringBuffer("select * from api_advert where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		List<Api_Advert> list = this.apiAdvertDao.find(sql.toString(), args.toArray());
		return list;
	}
	/**
	 * 保存广告API
	 * @param banner
	 */
	public void saveApiAd(Api_Advert apiAd) {
		this.apiAdvertDao.insert(apiAd);
	}

	/**
	 * 修改广告API
	 * @param bang
	 */
	public void updateApiAd(Api_Advert apiAd) {
		String sql = "update `api_advert` set name=?,remark=?,updateDate=now() where id=?";
		List<Object> args = new ArrayList<Object>();
		args.add(apiAd.getName());
		args.add(apiAd.getRemark());
		args.add(apiAd.getId());
		this.apiAdvertDao.update(sql, args.toArray());
	}

	/**
	 * 
	 * 删除广告API
	 * @param id
	 */
	public void deleteApiAdById(String id) {
		if(!DataUtil.isEmpty(id)){
			this.apiAdvertDao.delete("api_advert", "id", id);
		}
	}
	

	/**
	 * 根据ID获取广告API
	 * 广告APIaram id
	 */
	public Api_Advert getApiAdById(String id) {
		String sql = "select * from api_advert where id = ?";
		return this.apiAdvertDao.findOne(sql, id);
	}
}
