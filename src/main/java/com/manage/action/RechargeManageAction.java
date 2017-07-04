package com.manage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmind.core.util.DataUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.RechargeCondition;
import com.manage.model.Cash_Coupon;
import com.manage.model.Channel_Version_V3;
import com.manage.model.Recharge_Amount;
import com.manage.model.Recharge_Repay;
import com.manage.service.CouponService;
import com.manage.service.RechargeAmountService;
import com.manage.service.RechargeRepayService;
import com.manage.service.VersionService;

@Controller
@RequestMapping("/recharge")
public class RechargeManageAction extends AbstractAction{
	@Resource
	private RechargeRepayService rechargeRepayService;
	
	@Resource
	private RechargeAmountService rechargeAmountService;
	
	@Resource
	private CouponService couponService;

	@Resource
	private VersionService versionService;
	/**
	 * 根据条件查询充值赠送信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listRecharge")
	public String listRecharge(RechargeCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.rechargeRepayService.findCountByCondition(condition);
		//封装condition
		condition = (RechargeCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Recharge_Repay> list = rechargeRepayService.findRechargeRepayByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "recharge/recharge_list";
	}
	
	/**
	 * 添加充值赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddRecharge")
	public String toAddRecharge(String firstFlag,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		String view;
		if("1".equals(firstFlag)){
			view = "recharge/recharge_first_add";
		}else{
			view = "recharge/recharge_add";
		}
		return view;
	}
	
	/**
	 * 添加充值赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addRecharge")
	public String addRecharge(Recharge_Repay recharge,String amounts,String moneys,String cashCouponIds,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		recharge.setCreateDate(new Date());
		recharge.setUpdateDate(new Date());
		String endDate = recharge.getEndDate();
		recharge.setEndDate(endDate+" 23:59:59");
		if(DataUtil.isEmpty(recharge.getVersion())){
			recharge.setVersion("");
		}
		
		List<Recharge_Amount> rechargeAmounts = new ArrayList<Recharge_Amount>();
		String[] amount = amounts.split(",");
		
		int maxMoney = 0;
		for(int i = 0; i < amount.length; i++){
			Recharge_Amount rechargeAmount = new Recharge_Amount();
			if(!DataUtil.isEmpty(moneys)){
				String[] money = moneys.split(",");
				rechargeAmount.setMoney(Integer.parseInt(money[i]));
				if(maxMoney < Integer.parseInt(money[i])){
					maxMoney = Integer.parseInt(money[i]);
				}
			}
			if(!DataUtil.isEmpty(cashCouponIds)){
				String[] cashCouponId = cashCouponIds.split(",");
				rechargeAmount.setCashCouponId(cashCouponId[i]);
				//如果使用代金券金额
				if("1".equals(recharge.getIsCashMoney())){
					Cash_Coupon coupon = this.couponService.getCouponById(cashCouponId[i]);
					if(coupon != null && maxMoney < Integer.parseInt(coupon.getMoney())){
						maxMoney = Integer.parseInt(coupon.getMoney());
					}
				}
			}
			rechargeAmount.setAmount(Integer.parseInt(amount[i]));
			rechargeAmount.setCreateDate(new Date());
			rechargeAmount.setUpdateDate(new Date());
			rechargeAmount.setIsCashMoney(recharge.getIsCashMoney());
			rechargeAmounts.add(rechargeAmount);
		}
		recharge.setMaxMoney(maxMoney);
		this.rechargeRepayService.saveRechargeAndAmount(recharge,rechargeAmounts);
		return "redirect:listRecharge?firstFlag="+recharge.getFirstFlag();
	}
	
	/**
	 * 删除充值赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteRecharge")
	public String deleteRecharge(String id,String firstFlag, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.rechargeRepayService.deleteRechargeAndAmount(id);
		return "redirect:listRecharge?firstFlag="+firstFlag;
	}
	
	
	/**
	 * 修改充值赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateRecharge")
	public String toUpdateRecharge(String id,String firstFlag,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Recharge_Repay recharge = this.rechargeRepayService.getRechargeRepayById(id);
		List<Recharge_Amount> amounts = this.rechargeAmountService.getRechargeAmountByRechargeId(id);
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		obj.put("recharge", recharge);
		obj.put("amounts", amounts);
		String view;
		if("1".equals(firstFlag)){
			view = "recharge/recharge_first_update";
		}else{
			view = "recharge/recharge_update";
		}
		return view;
	}
	
	/**
	 * 修改充值赠送hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateRecharge")
	public String updateRecharge(Recharge_Repay recharge,String amountIds,String amounts,String moneys,String cashCouponIds, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		recharge.setUpdateDate(new Date());
		String endDate = recharge.getEndDate();
		recharge.setEndDate(endDate+" 23:59:59");
		if(DataUtil.isEmpty(recharge.getVersion())){
			recharge.setVersion("");
		}
		
		List<Recharge_Amount> rechargeAmounts = new ArrayList<Recharge_Amount>();
		String[] amount = amounts.split(",");
		String[] amountId = amountIds.split(",");
		
		int maxMoney = 0;
		for(int i = 0; i < amount.length; i++){
			Recharge_Amount rechargeAmount = new Recharge_Amount();
			if(!DataUtil.isEmpty(moneys)){
				String[] money = moneys.split(",");
				rechargeAmount.setMoney(Integer.parseInt(money[i]));
				if(maxMoney < Integer.parseInt(money[i])){
					maxMoney = Integer.parseInt(money[i]);
				}
			}
			if(!DataUtil.isEmpty(cashCouponIds)){
				String[] cashCouponId = cashCouponIds.split(",");
				rechargeAmount.setCashCouponId(cashCouponId[i]);
				//如果使用代金券金额
				if("1".equals(recharge.getIsCashMoney())){
					Cash_Coupon coupon = this.couponService.getCouponById(cashCouponId[i]);
					if(coupon != null && maxMoney < Integer.parseInt(coupon.getMoney())){
						maxMoney = Integer.parseInt(coupon.getMoney());
					}
				}
			}
			rechargeAmount.setId(amountId[i]);
			rechargeAmount.setAmount(Integer.parseInt(amount[i]));
			rechargeAmount.setRechargeRepayId(recharge.getId());
			rechargeAmount.setUpdateDate(new Date());
			rechargeAmount.setIsCashMoney(recharge.getIsCashMoney());
			rechargeAmounts.add(rechargeAmount);
		}
		recharge.setMaxMoney(maxMoney);
		this.rechargeRepayService.updateRechargeAndAmount(recharge, rechargeAmounts);
		return "redirect:listRecharge?firstFlag="+recharge.getFirstFlag();
	}
	
	/**
	 * 重置rediskey hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/resetRedisCache")
	public String resetJpRedisCache(String firstFlag,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		rechargeRepayService.resetRedisCache();
		rechargeAmountService.resetRedisCache();
		
		return "redirect:listRecharge?firstFlag="+firstFlag;
	}
}