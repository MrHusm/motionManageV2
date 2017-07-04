package com.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.manage.dao.BannerAppDao;
import com.manage.domain.BannerAppCondition;
import com.manage.model.Banner_App;

@Service
public class BannerAppService {
	@Resource
	private BannerAppDao bannerAppDao;
	
	/**
	 * 根据条件查询广告数量
	 * @param condition
	 */
	public int findCountByCondition(BannerAppCondition condition) {
		String name = condition.getName();
		String pageType = condition.getPageType();
		StringBuffer sql = new StringBuffer("select count(*) from banner_app where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		
		if(StringUtils.isNotBlank(pageType)){
			sql.append(" and pageType = ?");
			args.add(pageType);
		}
		return this.bannerAppDao.findForInt(sql.toString(), args.toArray());
	}
    
    /**
     * 根据条件查询广告列表
     * @param condition
     * @return
     */
	public List<Banner_App> findBannerAppByCondition(BannerAppCondition condition) {
		String name = condition.getName();
		String pageType = condition.getPageType();
		StringBuffer sql = new StringBuffer("select *,CASE WHEN startDate <=NOW() AND endDate >= NOW() THEN 1 WHEN endDate < NOW() THEN 2 ELSE 0 END AS state from banner_app where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		
		if(StringUtils.isNotBlank(pageType)){
			sql.append(" and pageType = ?");
			args.add(pageType);
		}
		List<Banner_App> list = this.bannerAppDao.find(sql.toString(), args.toArray());
		return list;
	}
	
	/**
	 * 保存广告
	 * @param bannerApp
	 */
	public void saveBannerApp(Banner_App bannerApp) {
		this.bannerAppDao.insert(bannerApp);
	}

	/**
	 * 修改广告
	 * @param bang
	 */
	public void updateBannerApp(Banner_App bannerApp) {
		String sql = "update `banner_app` set pageType=?,name=?,appName=?,appVersion=?,"
				+ "appIntro=?,url=?,size=?,appPackName=?,sdkInfo=?,repayType=?,money=?,cashCouponId=?,"
				+ "versions=?,channelType=?,channelIds=?,startDate=?,endDate=?,updateDate=now() where id=?";
		List<Object> args = new ArrayList<Object>();
		args.add(bannerApp.getPageType());
		args.add(bannerApp.getName());
		args.add(bannerApp.getAppName());
		args.add(bannerApp.getAppVersion());
		args.add(bannerApp.getAppIntro());
		args.add(bannerApp.getUrl());
		args.add(bannerApp.getSize());
		args.add(bannerApp.getAppPackName());
		args.add(bannerApp.getSdkInfo());
		args.add(bannerApp.getRepayType());
		args.add(bannerApp.getMoney());
		args.add(bannerApp.getCashCouponId());
		args.add(bannerApp.getVersions());
		args.add(bannerApp.getChannelType());
		args.add(bannerApp.getChannelIds());
		args.add(bannerApp.getStartDate());
		args.add(bannerApp.getEndDate());
		args.add(bannerApp.getId());
		this.bannerAppDao.update(sql, args.toArray());
	}
	
	/**
	 * 修改广告排序
	 * @param bang
	 */
	public void updateBannerAppIdx(String id,String idx) {
		String sql = "update `banner_app` set idx=?,updateDate=now() where id=?";
		List<Object> args = new ArrayList<Object>();
		args.add(idx);
		args.add(id);
		this.bannerAppDao.update(sql, args.toArray());
	}

	/**
	 * 
	 * 删除广告
	 * @param id
	 */
	public void deleteBannerAppById(String id) {
		if(!DataUtil.isEmpty(id)){
			this.bannerAppDao.delete("banner_app", "id", id);
		}
	}
	
	/**
	 * 根据ID获取广告
	 * 广告 id
	 */
	public Banner_App getBannerAppById(String id) {
		String sql = "select * from banner_app where id = ?";
		return this.bannerAppDao.findOne(sql, id);
	}
	
	/**
	 * 获取今天未签到用户
	 * @return
	 */
	public List<Map<String,Object>> getQiandaoUsers(){
//		String sql = "SELECT cid AS clientId FROM client_user_center.`user_client` WHERE id IN("
//					+"SELECT MAX(id) FROM client_user_center.`user_client` WHERE uid IN("
//					+"SELECT DISTINCT(uid) FROM user_qiandao_log q "
//					+" WHERE DATEDIFF(NOW(),q.createDate) <= 5 AND  DATEDIFF(NOW(),q.createDate)>0 "
//					+ " AND uid NOT IN(SELECT uid FROM user_qiandao_log WHERE DATEDIFF(NOW(), createDate) = 0)) GROUP BY uid)";
		String sql = "select d.cid as clientId  from client_user_center.`user_client`  d, ("
					+" select max(id) id  from ("
					+" select cid as clientId,a.uid , a.id from client_user_center.`user_client`  a ," 
					+" (SELECT DISTINCT(uid) uid FROM user_qiandao_log q  "
					+" WHERE DATEDIFF(NOW(),q.createDate) <= 5 AND  DATEDIFF(NOW(),q.createDate)>0"  
					+" AND uid NOT IN(SELECT uid FROM user_qiandao_log WHERE DATEDIFF(NOW(), createDate) = 0)) b" 
					+" where a.uid = b.uid )c group by uid ) f "
					+" where d.id = f.id ";
		System.out.println("---qiandao_push---:"+sql);
		return this.bannerAppDao.findForList(sql, new Object[]{});
	}
	
	/**
	 * 获取今天未登陆的新增用户
	 * @return
	 */
	public List<Map<String,Object>> getNewUsers(){
		String sql = "SELECT u.cid AS clientId FROM client_user_center.`user_client` u,"
				+ "(SELECT MAX(id) AS id FROM client_user_center.`user_client`  WHERE uid IN("					 
				+" SELECT uid FROM user_day_add  WHERE DATEDIFF(NOW(),createDate)=1 ) GROUP BY uid) a "
				+ "WHERE a.id = u.`id`	";
		System.out.println("---new_user---:"+sql);
		return this.bannerAppDao.findForList(sql, new Object[]{});
	}
	
	/**
	 * 获取即将到期的VIP用户
	 * @return
	 */
	public List<Map<String,Object>> getExpireVipUsers(){
		String sql = "SELECT u.cid AS clientId FROM client_user_center.`user_client` u,"
				+"(SELECT MAX(id) AS id FROM client_user_center.`user_client`  WHERE uid IN("
				+" SELECT uid FROM client_user_center.`user_vip` WHERE DATEDIFF(endDate,NOW())=1)" 
				+" GROUP BY uid) a  WHERE a.id = u.`id`";
		System.out.println("---expire_vip_user---:"+sql);
		return this.bannerAppDao.findForList(sql, new Object[]{});
	}
}