package com.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.manage.constant.Constant;
import com.manage.dao.JpBannerDao;
import com.manage.domain.JpBannerCondition;
import com.manage.model.Channel;
import com.manage.model.Channel_Version_V3;
import com.manage.model.JP_Banner;
import com.manage.util.RedisUtil;

@Service("jpBannerService")
public class JpBannerService{

    @Resource
    private JpBannerDao jpBannerDao;
    
    @Resource
    private ChannelService channelService;
    
    @Resource
    private VersionService versionService;
    
	/**
	 * 根据条件查询广告数量
	 * @param condition
	 */
	public int findCountByCondition(JpBannerCondition condition) {
		String name = condition.getName();
		Integer bpId = condition.getBpId();
		StringBuffer sql = new StringBuffer("select count(*) from jp_banner where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(bpId != null){
			sql.append(" and bpId = ?");
			args.add(bpId);
		}
		
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		return this.jpBannerDao.findForInt(sql.toString(), args.toArray());
	}
    
    /**
     * 根据条件查询广告列表
     * @param condition
     * @return
     */
	public List<JP_Banner> findBannerByCondition(JpBannerCondition condition) {
		String name = condition.getName();
		Integer bpId = condition.getBpId();
		StringBuffer sql = new StringBuffer("select * from jp_banner where 1 = 1 ");
		List<Object> args = new ArrayList<Object>();
		if(bpId != null){
			sql.append(" and bpId = ?");
			args.add(bpId);
		}
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like ?");
			args.add("%"+name+"%");
		}
		List<JP_Banner> list = this.jpBannerDao.find(sql.toString(), args.toArray());
		return list;
	}
	/**
	 * 保存广告
	 * @param banner
	 */
	public void saveJpBanner(JP_Banner banner) {
		this.jpBannerDao.insert(banner);
	}

	/**
	 * 修改广告
	 * @param bang
	 */
	public void updateBanner(JP_Banner banner) {
		String sql = "update `jp_banner` set name=?,url=?,content=?,startDate=?,endDate=?,idx=?,remark=?,versions=?,channels=?,updateDate=now()";
		List<Object> args = new ArrayList<Object>();
		args.add(banner.getName());
		args.add(banner.getUrl());
		args.add(banner.getContent());
		args.add(banner.getStartDate());
		args.add(banner.getEndDate());
		args.add(banner.getIdx());
		args.add(banner.getRemark());
		args.add(banner.getVersions());
		args.add(banner.getChannels());
		if(!DataUtil.isEmpty(banner.getImgUrl())){
			sql += ",imgUrl=?";
			args.add(banner.getImgUrl());
		}
		args.add(banner.getId());
		sql += " where id=?";
		this.jpBannerDao.update(sql, args.toArray());
	}

	/**
	 * 
	 * 删除广告
	 * @param id
	 */
	public void deleteBannerById(String id) {
		if(!DataUtil.isEmpty(id)){
			this.jpBannerDao.delete("jp_banner", "id", id);
		}
	}
	
	/**
	 * 
	 * 根据广告位id删除广告
	 * @param id
	 */
	public void deleteBannersBybpId(String bpId) {
		if(!DataUtil.isEmpty(bpId)){
			this.jpBannerDao.delete("jp_banner", "bpId", bpId);
		}
	}

	/**
	 * 根据ID获取广告
	 * @param id
	 */
	public JP_Banner getJpBannerById(String id) {
		String sql = "select * from jp_banner where id = ?";
		return this.jpBannerDao.findOne(sql, id);
	}
	
	public void resetRedisCache(){
		//获取所有的渠道和版本
		List<Channel> cls = this.channelService.getChannels(null);
		List<Channel_Version_V3> cvs = this.versionService.getVersions();
		
		Map<String,List<JP_Banner>> channels = new HashMap<String,List<JP_Banner>>();
		String sql = "select * from jp_banner where startDate<=now() and endDate>=now() and bpId in (select id from jp_banner_position where source=0) order by idx asc";
		List<JP_Banner> banners = this.jpBannerDao.find(sql);
		if(banners !=null && banners.size()>0){
			for(JP_Banner banner : banners){
				String channelIds = banner.getChannels() == null ? "" : banner.getChannels();
				if(StringUtils.isBlank(channelIds)){
					if(channels.get(null) == null){
						List<JP_Banner> list = new ArrayList<JP_Banner>();
						list.add(banner);
						channels.put(null, list);
					}else{
						channels.get(null).add(banner);
					}
				}else{
					String[] cids = channelIds.split(",");
					for(String cid : cids){
						cid = cid.trim();
						if(channels.get(cid) == null){
							List<JP_Banner> list = new ArrayList<JP_Banner>();
							list.add(banner);
							channels.put(cid, list);
						}else{
							channels.get(cid).add(banner);
						}
					}
				}
			}
		}
		Map<String,List<JP_Banner>> result = new HashMap<String,List<JP_Banner>>();
		
		for(String key : channels.keySet()) {
			List<JP_Banner> list = channels.get(key);
			Map<String,List<JP_Banner>> versions = new HashMap<String,List<JP_Banner>>();
			for(JP_Banner banner : list){
				String version = banner.getVersions() == null ? "" : banner.getVersions();
				if(StringUtils.isBlank(version)){
					if(versions.get(null) == null){
						List<JP_Banner> verList = new ArrayList<JP_Banner>();
						verList.add(banner);
						versions.put(null, verList);
					}else{
						versions.get(null).add(banner);
					}
				}else{
					String[] vers = version.split(",");
					for(String ver : vers){
						if(versions.get(ver) == null){
							List<JP_Banner> verList = new ArrayList<JP_Banner>();
							verList.add(banner);
							versions.put(ver, verList);
						}else{
							versions.get(ver).add(banner);
						}
					}
				}
			}
			
			for(String ver :versions.keySet()){
				result.put(key+"_"+ver, versions.get(ver));
			}
		}
		
		Jedis jedis = RedisUtil.getJedis();
		String redisKey = Constant.redis_Key_jpBanner;
		try{
			for(Channel c : cls){
				for(Channel_Version_V3 v : cvs){
					String key = String.valueOf(c.getChannel_id()) + "_" + v.getVcode();
					List<JP_Banner> list = result.get(key);
					List<JP_Banner> redisResult = new ArrayList<JP_Banner>();
					if(DataUtil.isEmpty(list)){
						key = String.valueOf(c.getChannel_id()) + "_" + null;
						list = result.get(key);
						if(DataUtil.isEmpty(list)){
							key = null + "_" + v.getVcode();
							list = result.get(key);
							if(DataUtil.isEmpty(list)){
								key = null + "_" + null;
								list = result.get(key);
								redisResult.addAll(list);
							}else{
								redisResult.addAll(list);
								Set<Integer> positionIds = new HashSet<Integer>();
								for(JP_Banner banner : list){
									positionIds.add(banner.getBpId());
								}
								//无渠道无版本
								key = null + "_" + null;
								List<JP_Banner> n_n_banners = result.get(key);
								if(!DataUtil.isEmpty(n_n_banners)){
									for(JP_Banner banner : n_n_banners){
										if(!positionIds.contains(banner.getBpId())){
											//list.add(banner);
											redisResult.add(banner);
										}
									}
								}
							}
						}else{
							redisResult.addAll(list);
							Set<Integer> positionIds = new HashSet<Integer>();
							for(JP_Banner banner : list){
								positionIds.add(banner.getBpId());
							}
							
							//无渠道有版本
							Set<Integer> n_v_positionIds = new HashSet<Integer>();
							key = null + "_" + v.getVcode();
							List<JP_Banner> n_v_banners = result.get(key);
							if(!DataUtil.isEmpty(n_v_banners)){
								for(JP_Banner banner : n_v_banners){
									if(!positionIds.contains(banner.getBpId())){
										//list.add(banner);
										redisResult.add(banner);
										n_v_positionIds.add(banner.getBpId());
									}
								}	
							}
							positionIds.addAll(n_v_positionIds);
							//无渠道无版本
							key = null + "_" + null;
							List<JP_Banner> n_n_banners = result.get(key);
							if(!DataUtil.isEmpty(n_n_banners)){
								for(JP_Banner banner : n_n_banners){
									if(!positionIds.contains(banner.getBpId())){
										//list.add(banner);
										redisResult.add(banner);
									}
								}
							}
						}
					}else{
						redisResult.addAll(list);
						Set<Integer> positionIds = new HashSet<Integer>();
						for(JP_Banner banner : list){
							positionIds.add(banner.getBpId());
						}
						//有渠道无版本
						Set<Integer> c_n_positionIds = new HashSet<Integer>();
						key = String.valueOf(c.getChannel_id()) + "_" + null;
						List<JP_Banner> c_n_banners = result.get(key);
						if(!DataUtil.isEmpty(c_n_banners)){
							for(JP_Banner banner : c_n_banners){
								if(!positionIds.contains(banner.getBpId())){
									//list.add(banner);
									redisResult.add(banner);
									c_n_positionIds.add(banner.getBpId());
								}
							}	
						}
						positionIds.addAll(c_n_positionIds);
						//无渠道有版本
						Set<Integer> n_v_positionIds = new HashSet<Integer>();
						key = null + "_" + v.getVcode();
						List<JP_Banner> n_v_banners = result.get(key);
						if(!DataUtil.isEmpty(n_v_banners)){
							for(JP_Banner banner : n_v_banners){
								if(!positionIds.contains(banner.getBpId())){
									//list.add(banner);
									redisResult.add(banner);
									n_v_positionIds.add(banner.getBpId());
								}
							}	
						}
						positionIds.addAll(n_v_positionIds);
						//无渠道无版本
						key = null + "_" + null;
						List<JP_Banner> n_n_banners = result.get(key);
						if(!DataUtil.isEmpty(n_n_banners)){
							for(JP_Banner banner : n_n_banners){
								if(!positionIds.contains(banner.getBpId())){
									//list.add(banner);
									redisResult.add(banner);
								}
							}	
						}
					}
					jedis.set(String.format(redisKey, String.valueOf(c.getChannel_id()),v.getVcode()).getBytes(),RedisUtil.serialize(redisResult));
					//System.out.println(String.valueOf(c.getChannel_id()) + "_" + v.getVcode());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			RedisUtil.closeJedis(jedis);
		}
	}
}