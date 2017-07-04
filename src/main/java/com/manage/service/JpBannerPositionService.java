package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.JpBannerDao;
import com.manage.dao.JpBannerPositionDao;
import com.manage.domain.JpBannerPositionCondition;
import com.manage.model.JP_Banner_Position;

@Service("jpBannerPositionService")
public class JpBannerPositionService{

	@Resource
	private JpBannerDao jpBannerDao;
	
	@Resource
	private JpBannerPositionDao jpBannerPositionDao;
    
	/**
	 * 根据条件查询广告位数量
	 * @param condition
	 */
	public int findCountByCondition(JpBannerPositionCondition condition) {
		String name = condition.getName();
		String source = condition.getSource();
		StringBuffer sql = new StringBuffer("select count(*) from jp_banner_position where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		if(StringUtils.isNotBlank(source)){
			sql.append(" and source = ?");
			args.add(source);
		}
		return this.jpBannerPositionDao.findForInt(sql.toString(), args.toArray());
	}
    
    /**
     * 根据条件查询广告位列表
     * @param condition
     * @return
     */
	public List<JP_Banner_Position> findBannerPositionByCondition(JpBannerPositionCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		String name = condition.getName();
		String source = condition.getSource();
		StringBuffer sql = new StringBuffer("select * from resource_process.jp_banner_position where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		if(StringUtils.isNotBlank(source)){
			sql.append(" and source = ?");
			args.add(source);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<JP_Banner_Position> list = this.jpBannerPositionDao.find(sql.toString(), args.toArray());
		return list;
	}
	/**
	 * 保存广告位
	 * @param bang
	 */
	public void saveBannerPosition(JP_Banner_Position position) {
		this.jpBannerPositionDao.insert(position);
	}

	/**
	 * 修改广告位
	 * @param bang
	 */
	public void updateBannerPosition(JP_Banner_Position position) {
		String sql = "update `jp_banner_position` set name=?,type=?,updateDate=now() where id=?";
		String sqlPage = "update jp_page set name=? where `type`=2 and keyId=?";
		List<Object> args = new ArrayList<Object>();
		args.add(position.getName());
		args.add(position.getType());
		args.add(position.getId());
		this.jpBannerPositionDao.update(sql, args.toArray());
		
		List<Object> pageArgs = new ArrayList<Object>();
		pageArgs.add(position.getName());
		pageArgs.add(position.getId());
		this.jpBannerPositionDao.update(sqlPage, pageArgs.toArray());
	}

	/**
	 * 
	 * 删除广告位
	 * @param id
	 */
	public void deleteBannerPosition(String id) {
		String sql = "delete from jp_page where type = 2 and keyId=?";
		if(!DataUtil.isEmpty(id)){
			this.jpBannerPositionDao.delete("jp_banner_position", "id", id);
			this.jpBannerDao.delete("jp_banner", "bpId", id);
			this.jpBannerPositionDao.update(sql, id);
		}
	}

	/**
	 * 根据ID获取广告位
	 * @param id
	 */
	public JP_Banner_Position getBannerPositionById(String id) {
		String sql = "select * from jp_banner_position where id = ?";
		return this.jpBannerPositionDao.findOne(sql, id);
	}
}
