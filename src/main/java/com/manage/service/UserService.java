package com.manage.service;

import java.util.Map;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.manage.constant.Constant;
import com.manage.model.User;
import com.manage.model.UserV2;

import base.ikanshu.util.URLConnectionHelper;


@Service
public class UserService {
	/**
	 * 获取用户信息
	 */
	private static String Url_getUser="/user/get";
	private static String Url_getUser_Parm="name=%s&password=%s&uid=%s";
	public User getUserByCondition(String name,String password,String uid){
		String content = URLConnectionHelper.sendGet(Constant.zwsc_user+Url_getUser, String.format(Url_getUser_Parm,name,password,uid),"utf8");
		User user = null;
		try {
			Gson gson=new Gson();
			Map<String,Object> result=gson.fromJson(content, new TypeToken<Map<String,Object>>(){}.getType());
			if(result.get("data")!=null && StringUtils.isNotBlank(result.get("data").toString())){
				UserV2 userV2= gson.fromJson(gson.toJson(result.get("data")),UserV2.class);
				user = new User();
				user.setId(userV2.getId().intValue());
				user.setName(userV2.getName());
				user.setNickName(userV2.getNickName());
				user.setPassword(userV2.getPassword());
				user.setTel(userV2.getTel());
				user.setEmail(userV2.getEmail());
				user.setLogo(userV2.getLogo());
				user.setSex(userV2.getSex());
				user.setBirthDay(userV2.getBirthDay());
				user.setCreateDate(userV2.getCreateDate());
				user.setUpdateDate(userV2.getUpadteDate());
				
				//获取用户账户信息
				//Useramount useramount = useramountDao.findByUserId(String.valueOf(userV2.getId()));
				//if(useramount != null){
				//	user.setAmount(useramount.getAmount()+useramount.getVirtualAmount());
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
