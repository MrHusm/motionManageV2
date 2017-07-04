package com.manage.task;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.manage.action.AbstractAction;
import com.manage.service.BindService;
import com.manage.service.JpBangBookService;
import com.manage.service.JpBannerService;
import com.manage.service.JpPageService;
import com.manage.service.RechargeAmountService;
import com.manage.service.RechargeRepayService;


@Controller
public class InitRedisKey extends AbstractAction{

	@Resource
	private RechargeRepayService rechargeRepayService;
	
	@Resource
	private RechargeAmountService rechargeAmountService;
	
	@Resource
	private BindService bindService;
	
	@Resource
	private JpPageService jpPageService;
	
	@Resource
	private JpBannerService jpBannerService;
	
	@Resource
	private JpBangBookService jpBangBookService;
	
	/**
	 * 重置 精品缓存 每隔1小时执行
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	public void resetRedisCacheEveryOne(){
		//每隔一小时重置精品缓存 执行顺序不能变
		jpBangBookService.resetRedisCache();
		jpBannerService.resetRedisCache();
		jpPageService.resetRedisCache();
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	public void resetRedisCacheAtOne(){
		logger.info("------start_resetRedisCacheAtOne---");
		//每天一点执行
		//绑定手机号赠送
		bindService.resetRedisCache();
		//充值缓存
		rechargeRepayService.resetRedisCache();
		rechargeAmountService.resetRedisCache();
		//图书自动下架
		jpBangBookService.deleteChannelLibBook();
		logger.info("------end_resetRedisCacheAtOne---");
	}

}