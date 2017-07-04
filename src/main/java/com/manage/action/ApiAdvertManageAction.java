package com.manage.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.ApiAdvertCondition;
import com.manage.model.Api_Advert;
import com.manage.service.ApiAdvertService;

@Controller
@RequestMapping("/apiAd")
public class ApiAdvertManageAction extends AbstractAction{
	@Resource
	private ApiAdvertService apiAdService;

	/**
	 * 根据条件查询广告APIhushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listApiAd")
	public String listApiAd(ApiAdvertCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.apiAdService.findCountByCondition(condition);
		//封装condition
		condition = (ApiAdvertCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Api_Advert> list = apiAdService.findApiAdByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "clientAd/apiAd_list";
	}
	
	/**
	 * 添加广告APIhushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddApiAd")
	public String toAddApiAd(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		return "clientAd/apiAd_add";
	}
	
	/**
	 * 添加广告APIhushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addApiAd")
	public String addApiAd(Api_Advert apiAd, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		apiAd.setCreateDate(new Date());
		apiAd.setUpdateDate(new Date());
		this.apiAdService.saveApiAd(apiAd);
		return "redirect:listApiAd";
	}
	
	/**
	 * 删除广告APIhushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteApiAd")
	public String deleteApiAd(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.apiAdService.deleteApiAdById(id);
		return "redirect:listApiAd";
	}
	
	/**
	 * 修改广告APIhushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateApiAd")
	public String toUpdateApiAd(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Api_Advert apiAd = this.apiAdService.getApiAdById(id);
		obj.put("apiAd", apiAd);
		return "clientAd/apiAd_update";
	}
	
	/**
	 * 修改广告APIhushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateApiAd")
	public String updateApiAd(Api_Advert apiAd, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.apiAdService.updateApiAd(apiAd);
		return "redirect:listApiAd";
	}
}