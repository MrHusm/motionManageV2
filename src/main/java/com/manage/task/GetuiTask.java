package com.manage.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.manage.constant.Constant;
import com.manage.service.BannerAppService;
import com.manage.util.GetuiUtil;

@Controller
public class GetuiTask {

    @Resource
    private BannerAppService bannerAppService;

    /**
     * 每天20：00，如果用户的每日福利页面中还有尚未领取的奖励，则给这些用户发一条push
     */
	public void pushQiandao() {
        Map<String, Object> jsonMap = new HashMap<String, Object>(); 
        jsonMap.put("action_data", "http://ad_earn_coins?index=2");
        String content = JSONObject.toJSONString(jsonMap);

	    List<Map<String,Object>> clients = bannerAppService.getQiandaoUsers(); // 获取推送列表
	    if(clients != null && clients.size()>0){
	    	GetuiUtil.pushMessageToList(clients, Constant.GETUI_MSG_T001_TITLE, Constant.GETUI_MSG_T001_TEXT, content);
	    }
	}
	
	/**
	 * 新增用户，次日的9:00前未访问客户端，则给这些用户发一条push
	 */
	public void pushNewUser() {
        Map<String, Object> jsonMap = new HashMap<String, Object>(); 
        jsonMap.put("action_data", "http://zwsc.ikanshu.cn/bookv3/xmbd"); 
        String content = JSONObject.toJSONString(jsonMap);

	    List<Map<String,Object>> clients = bannerAppService.getNewUsers(); // 获取推送列表
	    if(clients != null && clients.size()>0){
	    	GetuiUtil.pushMessageToList(clients, Constant.GETUI_MSG_T002_TITLE, Constant.GETUI_MSG_T002_TEXT, content);
	    }
	}
	
	/**
	 * VIP到期前一天10:00，给用户发条push
	 */
	public void pushExpireVipUsers() {
        Map<String, Object> jsonMap = new HashMap<String, Object>(); 
        jsonMap.put("action_data", "http://zwsc.ikanshu.cn/vip/index"); 
        String content = JSONObject.toJSONString(jsonMap);

	    List<Map<String,Object>> clients = bannerAppService.getExpireVipUsers(); // 获取推送列表
	    if(clients != null && clients.size()>0){
	    	GetuiUtil.pushMessageToList(clients, Constant.GETUI_MSG_T003_TITLE, Constant.GETUI_MSG_T003_TEXT, content);
	    }
	}
}