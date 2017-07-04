package com.manage.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.manage.constant.Constant;
import com.manage.domain.JpBannerCondition;
import com.manage.model.Channel_Version_V3;
import com.manage.model.JP_Banner;
import com.manage.model.JP_Banner_Position;
import com.manage.service.JpBannerPositionService;
import com.manage.service.JpBannerService;
import com.manage.service.VersionService;
import com.manage.util.FileUtils;

@Controller
@RequestMapping("/jpBanner")
public class JpBannerManageAction extends AbstractAction{
	@Resource
	private JpBannerService jpBannerService;
	
	@Resource
	private JpBannerPositionService jpBannerPositionService;
	
	@Resource
	private VersionService versionService;
	
	/**
	 * 根据条件查询广告信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listBanner")
	public String listBanner(JpBannerCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.jpBannerService.findCountByCondition(condition);
		//封装condition
		condition = (JpBannerCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<JP_Banner> list = jpBannerService.findBannerByCondition(condition);
		JP_Banner_Position position = this.jpBannerPositionService.getBannerPositionById(String.valueOf(condition.getBpId()));
		if(position != null){
			obj.put("bpName", position.getName());
		}
		obj.put("list", list);
		obj.put("condition",condition);
		return "banner/banner_list";
	}
	
	/**
	 * 添加广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddBanner")
	public String toAddBanner(JpBannerCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		obj.put("bpId", condition.getBpId());
		obj.put("type", condition.getType());
		obj.put("source", condition.getSource());
		return "banner/banner_add";
	}
	
	/**
	 * 添加广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addBanner")
	public String addBanner(JP_Banner banner,String type,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj,MultipartFile file){
		if(file != null && !file.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("/") + "jpBannerImage/";
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//截取后缀名
			String replace = UUID.randomUUID().toString().replace("-", "");
			replace+=ext;
			String filePath =path+ replace;
			System.out.println(filePath);
			try {
				file.transferTo(new File(filePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//ftp上传到服务器
			FileUtils.uploadFile(filePath, Constant.JP_REMOTE_PATH,FileUtils.getSession243());
			banner.setImgUrl(replace);
		}
		
		Date now = new Date();
		banner.setCreateDate(now);
		banner.setUpdateDate(now);
		this.jpBannerService.saveJpBanner(banner);
		return "redirect:listBanner?bpId="+banner.getBpId()+"&type="+type+"&source="+source;
	}
	
	/**
	 * 删除广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteBanner")
	public String deleteBanner(String id,String bpId,String type,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		if(!"3".equals(type)){
			//删除上传的广告图片
			JP_Banner oldBanner = this.jpBannerService.getJpBannerById(id);
			if(StringUtils.isNotBlank(oldBanner.getImgUrl())){
				FileUtils.deleteFile(Constant.JP_REMOTE_PATH+oldBanner.getImgUrl(),FileUtils.getSession243());
			}
		}
		this.jpBannerService.deleteBannerById(id);
		return "redirect:listBanner?bpId="+bpId+"&type="+type+"&source="+source;
	}
	
	/**
	 * 修改广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateBanner")
	public String toUpdateBanner(String id,JpBannerCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		JP_Banner banner = this.jpBannerService.getJpBannerById(id);
		
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		obj.put("bpId", condition.getBpId());
		obj.put("type", condition.getType());
		obj.put("source", condition.getSource());
		obj.put("banner", banner);
		return "banner/banner_update";
	}

	/**
	 * 修改广告hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateBanner")
	public String updateBanner(JP_Banner banner,String type,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj,MultipartFile file){
		if(file != null && !file.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("/") + "jpBannerImage/";
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
			FileUtils.uploadFile(filePath, Constant.JP_REMOTE_PATH,FileUtils.getSession243());
			
			//删除以前的文件
			JP_Banner oldBanner = this.jpBannerService.getJpBannerById(String.valueOf(banner.getId()));
			if(StringUtils.isNotBlank(oldBanner.getImgUrl())){
				FileUtils.deleteFile(Constant.JP_REMOTE_PATH+oldBanner.getImgUrl(),FileUtils.getSession243());
			}
			
			banner.setImgUrl(replace);
		}
		
		this.jpBannerService.updateBanner(banner);
		return "redirect:listBanner?bpId="+banner.getBpId()+"&type="+type+"&source="+source;
	}
}
