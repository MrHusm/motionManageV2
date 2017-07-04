package com.manage.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.JpBannerPositionCondition;
import com.manage.model.JP_Banner_Position;
import com.manage.service.JpBannerPositionService;
import com.manage.service.JpBannerService;

@Controller
@RequestMapping("/jpBannerPosition")
public class BannerPositionManageAction extends AbstractAction{
	@Resource
	private JpBannerService jpBannerService;
	
	@Resource
	private JpBannerPositionService jpBannerPositionService;

	/**
	 * 根据条件查询广告位信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listBannerPosition")
	public String listBannerPosition(JpBannerPositionCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.jpBannerPositionService.findCountByCondition(condition);
		//封装condition
		condition = (JpBannerPositionCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<JP_Banner_Position> list = jpBannerPositionService.findBannerPositionByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "banner/bannerPosition_list";
	}
	
	/**
	 * 添加广告位hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddBannerPosition")
	public String toAddBannerPosition(String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		obj.put("source", source);
		return "banner/bannerPosition_add";
	}
	
	/**
	 * 添加广告位hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addBannerPosition")
	public String addBannerPosition(JP_Banner_Position position,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Date now = new Date();
		position.setCreateDate(now);
		position.setUpdateDate(now);
		this.jpBannerPositionService.saveBannerPosition(position);
		return "redirect:listBannerPosition?source="+position.getSource();
	}
	
	/**
	 * 删除广告位hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteBannerPosition")
	public String deleteBannerPosition(String id,String dqPage,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.jpBannerPositionService.deleteBannerPosition(id);
		return "redirect:listBannerPosition?dqPage="+dqPage+"&source="+source;
	}
	
	/**
	 * 修改广告位hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateBannerPosition")
	public String toUpdateBannerPosition(String id,String dqPage,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		JP_Banner_Position position = this.jpBannerPositionService.getBannerPositionById(id);
		obj.put("position", position);
		obj.put("dqPage", dqPage);
		return "banner/bannerPosition_update";
	}

	/**
	 * 修改广告位hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateBannerPosition")
	public String updateBannerPosition(JP_Banner_Position position,String names,String types,String urls,String dqPage,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.jpBannerPositionService.updateBannerPosition(position);
		return "redirect:listBannerPosition?dqPage="+dqPage+"&source="+position.getSource();
	}
}
