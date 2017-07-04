package com.manage.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.manage.constant.Constant;
import com.manage.domain.BannerAppCondition;
import com.manage.model.Banner_App;
import com.manage.model.Channel_Version_V3;
import com.manage.service.BannerAppService;
import com.manage.service.VersionService;
import com.manage.util.FileUtils;

@Controller
@RequestMapping("/bannerApp")
public class BannerAppManageAction extends AbstractAction{
	@Resource
	private BannerAppService bannerAppService;
	@Resource
	private VersionService versionService;

	/**
	 * 根据条件查询广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listBannerApp")
	public String listBannerApp(BannerAppCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.bannerAppService.findCountByCondition(condition);
		//封装condition
		condition = (BannerAppCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Banner_App> list = bannerAppService.findBannerAppByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "bannerApp/banner_app_list";
	}
	
	/**
	 * 添加广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddBannerApp")
	public String toAddBannerApp(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		return "bannerApp/banner_app_add";
	}
	
	/**
	 * 添加广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addBannerApp")
	public String addBannerApp(Banner_App bannerApp, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj,MultipartFile file1,String url1,MultipartFile file2,String url2){
		MultipartFile file = null;
		Integer type = bannerApp.getType();
		Integer appType = bannerApp.getAppType();
		Integer repayType = bannerApp.getRepayType();
		Integer channelType = bannerApp.getChannelType();
		if(type == 1){
			bannerApp.setSdkInfo(null);
			if(appType == 1){
				file = file1;
				bannerApp.setUrl(url1);
			}else if(appType ==2){
				file = file2;
				bannerApp.setUrl(url2);
				bannerApp.setAppName(null);
				bannerApp.setAppVersion(null);
				bannerApp.setAppIntro(null);
				bannerApp.setSize(null);
				bannerApp.setAppPackName(null);
			}
		}else{
			bannerApp.setAppName(null);
			bannerApp.setAppVersion(null);
			bannerApp.setAppIntro(null);
			bannerApp.setSize(null);
			bannerApp.setAppPackName(null);
		}
		if(repayType == 1){
			bannerApp.setCashCouponId(null);
		}else{
			bannerApp.setMoney(null);
		}
		if(channelType == 0){
			bannerApp.setChannelIds(null);
		}
		
		if(file != null && !file.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("/") + "bannerAppImage/";
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//截取后缀名
			String replace = UUID.randomUUID().toString().replace("-", "");
			replace+=ext;
			String filePath =path+ replace;
			System.out.println(filePath);
			try {
				file.transferTo(new File(filePath));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//ftp上传到服务器
			FileUtils.uploadFile(filePath, Constant.BANNER_APP_REMOTE_PATH,FileUtils.getSession243());
			bannerApp.setImg(replace);
		}
		bannerApp.setCreateDate(new Date());
		bannerApp.setUpdateDate(new Date());
		this.bannerAppService.saveBannerApp(bannerApp);
		return "redirect:listBannerApp?pageType="+bannerApp.getPageType();
	}
	
	/**
	 * 删除广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteBannerApp")
	public String deleteBannerApp(String id,String pageType, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.bannerAppService.deleteBannerAppById(id);
		return "redirect:listBannerApp?pageType="+pageType;
	}
	
	/**
	 * 修改广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateBannerApp")
	public String toUpdateApiAd(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Banner_App bannerApp = this.bannerAppService.getBannerAppById(id);
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		obj.put("bannerApp", bannerApp);
		return "bannerApp/banner_app_update";
	}
	
	/**
	 * 修改广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateBannerApp")
	public String updateBannerApp(Banner_App bannerApp, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj,String url1,String url2){
		Integer type = bannerApp.getType();
		Integer appType = bannerApp.getAppType();
		Integer repayType = bannerApp.getRepayType();
		Integer channelType = bannerApp.getChannelType();		
		if(type == 1){
			bannerApp.setSdkInfo(null);
			if(appType == 1){
				bannerApp.setUrl(url1);
			}else if(appType ==2){
				bannerApp.setUrl(url2);
				bannerApp.setAppName(null);
				bannerApp.setAppVersion(null);
				bannerApp.setAppIntro(null);
				bannerApp.setSize(null);
				bannerApp.setAppPackName(null);
			}
		}else{
			bannerApp.setAppName(null);
			bannerApp.setAppVersion(null);
			bannerApp.setAppIntro(null);
			bannerApp.setSize(null);
			bannerApp.setAppPackName(null);
		}
		if(repayType == 1){
			bannerApp.setCashCouponId(null);
		}else{
			bannerApp.setMoney(null);
		}
		if(channelType == 0){
			bannerApp.setChannelIds(null);
		}
		this.bannerAppService.updateBannerApp(bannerApp);
		return "redirect:listBannerApp?pageType="+bannerApp.getPageType();
	}
	
	/**
	 * 修改广告排序hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateBannerAppIdx")
	public String updateBannerAppIdx(String id,String idx,String pageType, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.bannerAppService.updateBannerAppIdx(id,idx);
		return "redirect:listBannerApp?pageType="+pageType;
	}
}