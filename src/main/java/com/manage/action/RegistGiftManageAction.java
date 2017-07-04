package com.manage.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.RegistGiftCondition;
import com.manage.model.User_Register_Gift;
import com.manage.service.RegistGiftService;

/**
 * 
 * @author hushengmeng
 * 新手礼包后台配置
 */
@Controller
@RequestMapping("/registGift")
public class RegistGiftManageAction extends AbstractAction{
	@Resource
	private RegistGiftService registGiftService;

	/**
	 * 根据条件查询新手礼包信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listRegistGift")
	public String listRegistGift(RegistGiftCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.registGiftService.findCountByCondition(condition);
		//封装condition
		condition = (RegistGiftCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<User_Register_Gift> list = registGiftService.findRegistGiftByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "gift/regist_gift_list";
	}
	
	/**
	 * 添加新手礼包hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddRegistGift")
	public String toAddRegistGift(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		return "gift/regist_gift_add";
	}
	
	/**
	 * 添加新手礼包hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addRegistGift")
	public String addRegistGift(User_Register_Gift registGift, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		registGift.setCreateDate(new Date());
		registGift.setUpdateDate(new Date());
		this.registGiftService.saveRegistGift(registGift);
		return "redirect:listRegistGift";
	}
	
	/**
	 * 删除新手礼包hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteRegistGift")
	public String deleteRegistGift(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.registGiftService.deleteRegistGift(id);
		return "redirect:listRegistGift";
	}
	
	/**
	 * 修改新手礼包hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateRegistGift")
	public String toUpdateRegistGift(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		User_Register_Gift registGift = this.registGiftService.getRegistGiftById(id);
		obj.put("gift", registGift);
		return "gift/regist_gift_update";
	}
	
	/**
	 * 修改新手礼包hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateRegistGift")
	public String updateVersionUp(User_Register_Gift registGift, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.registGiftService.updateRegistGift(registGift);
		return "redirect:listRegistGift";
	}
}