package com.manage.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.CouponCondition;
import com.manage.model.Cash_Coupon;
import com.manage.service.CouponService;

@Controller
@RequestMapping("/coupon")
public class CouponManageAction extends AbstractAction{
	@Resource
	private CouponService couponService;
	
	/**
	 * 根据条件查询代金券信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listCoupon")
	public String listCoupon(CouponCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		//查询总共条数
		int totalRow = this.couponService.findCountByCondition(condition);
		//封装condition
		condition = (CouponCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Cash_Coupon> list = couponService.findCouponByCondition(condition);
		obj.put("condition",condition);
		obj.put("list", list);
		return "coupon/coupon_list";
	}
	
	/**
	 * 添加代金券hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddCoupon")
	public String toAddCoupon(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		return "coupon/coupon_add";
	}
	
	/**
	 * 添加代金券hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addCoupon")
	public String addCoupon(Cash_Coupon coupon, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		coupon.setDiscount("10");
		coupon.setChannelId("0");
		if("0".equals(coupon.getType())){
			coupon.setEndDate(coupon.getEndDate()+" 23:59:59");
		}
		coupon.setCreateDate(new Date());
		coupon.setUpdateDate(new Date());
		this.couponService.saveCoupon(coupon);
		
		return "redirect:listCoupon";
	}
	
	/**
	 * 删除代金券hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteCoupon")
	public String deleteCoupon(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.couponService.deleteCoupon(id);
		return "redirect:listCoupon";
	}
	
	
	/**
	 * 修改代金券hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateCoupon")
	public String toUpdateCoupon(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Cash_Coupon coupon = this.couponService.getCouponById(id);
		obj.put("coupon", coupon);
		return "coupon/coupon_update";
	}
	
	/**
	 * 修改代金券hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateCoupon")
	public String updateCoupon(Cash_Coupon coupon, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		coupon.setUpdateDate(new Date());
		if("0".equals(coupon.getType())){
			coupon.setEndDate(coupon.getEndDate()+" 23:59:59");
		}
		this.couponService.updateCoupon(coupon);
		return "redirect:listCoupon";
	}
}