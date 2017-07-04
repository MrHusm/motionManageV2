package com.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.manage.dao.ChannelDao;
import com.manage.model.Channel;

@Service("channelService")
public class ChannelService{

    
    @Resource
    private ChannelDao channelDao;
    
	/**
	 * 获取所有渠道
	 * @param 
	 */
	public List<Channel> getChannels(String[] types) {
		//0 "html5" 1 "单本" 2 "插件" 3 "客户端" 4 "免费版" 5 "静态版"
		StringBuffer sql = new StringBuffer("select * from channel where 1=1 ");
		if(types != null){
			for(int i=0; i<types.length; i++){
				sql.append(" and type != '"+ types[i]+"'");
			}
		}
		return this.channelDao.find(sql.toString());
	}
	
	/**
	 * 获取免费版渠道ID
	 * @return
	 */
	public List<String> getFreeChannelIds(){
		String sql = "select channel_id from channel where type = ?";
		List<String> channelIds = this.channelDao.findColumValues(sql, String.class, "4");
		return channelIds;
	}
}