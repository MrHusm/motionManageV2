package com.manage.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmind.core.util.DataUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.BindCondition;
import com.manage.model.Bind_Repay;
import com.manage.model.Channel_Version_V3;
import com.manage.service.BindService;
import com.manage.service.VersionService;

@Controller
@RequestMapping("/bind")
public class BindManageAction extends AbstractAction{
	@Resource
	private BindService bindService;
	
	@Resource
	private VersionService versionService;

	/**
	 * 根据条件查询绑定赠送信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listBind")
	public String listBind(BindCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.bindService.findCountByCondition(condition);
		//封装condition
		condition = (BindCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Bind_Repay> list = bindService.findBindByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "bind/bind_list";
	}
	
	/**
	 * 添加绑定赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddBind")
	public String toAddBind(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		return "bind/bind_add";
	}
	
	/**
	 * 添加绑定赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addBind")
	public String addBind(Bind_Repay bind, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		bind.setCreateDate(new Date());
		bind.setUpdateDate(new Date());
		if(DataUtil.isEmpty(bind.getVersion())){
			bind.setVersion("");
		}
		this.bindService.saveBind(bind);
		return "redirect:listBind";
	}
	
	/**
	 * 删除绑定赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteBind")
	public String deleteBind(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.bindService.deleteBind(id);
		return "redirect:listBind";
	}
	
	
	/**
	 * 修改绑定赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateBind")
	public String toUpdateBind(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Bind_Repay bind = this.bindService.getBindById(id);
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		obj.put("bind", bind);
		return "bind/bind_update";
	}
	
	/**
	 * 修改绑定赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateBind")
	public String updateBind(Bind_Repay bind, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		bind.setUpdateDate(new Date());
		if(DataUtil.isEmpty(bind.getVersion())){
			bind.setVersion("");
		}
		this.bindService.updateBind(bind);
		return "redirect:listBind";
	}
	
	/**
	 * 重置rediskey hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/resetRedisCache")
	public String resetRedisCache(String pageType,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.bindService.resetRedisCache();
		return "redirect:listBind";
	}
}