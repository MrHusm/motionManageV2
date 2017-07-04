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
import com.manage.domain.PlugInCondition;
import com.manage.model.Channel_Version_V3;
import com.manage.model.Plug_In;
import com.manage.service.PlugInService;
import com.manage.service.VersionService;
import com.manage.util.FileUtils;

@Controller
@RequestMapping("/plugIn")
public class PlugInManageAction extends AbstractAction{
	@Resource
	private VersionService versionService;
	@Resource
	private PlugInService plugInService;

	/**
	 * 根据条件查询插件信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listPlugIn")
	public String listPlugIn(PlugInCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.plugInService.findCountByCondition(condition);
		//封装condition
		condition = (PlugInCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Plug_In> list = plugInService.findPlugInByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "plugIn/plugIn_list";
	}
	
	/**
	 * 添加插件hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddPlugIn")
	public String toAddPlugIn(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		return "plugIn/plugIn_add";
	}
	
	/**
	 * 添加插件hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addPlugIn")
	public String addPlugIn(Plug_In plugIn, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj,MultipartFile file){
		if(file != null && !file.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("/") + "plugInImage/";
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//截取后缀名
			String replace = UUID.randomUUID().toString().replace("-", "");
			replace+=ext;
			String filePath =path+ replace;
			try {
				file.transferTo(new File(filePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//ftp上传到服务器
			FileUtils.uploadFile(filePath, Constant.PLUGIN_REMOTE_PATH,FileUtils.getSession211());
			plugIn.setUrl(replace);
			plugIn.setSize(String.valueOf(file.getSize()));
			
			plugIn.setCreateDate(new Date());
			plugIn.setUpdateDate(new Date());
			this.plugInService.savePlugIn(plugIn);
		}
		return "redirect:listPlugIn";
	}
	
	/**
	 * 删除插件hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deletePlugIn")
	public String deletePlugIn(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		//删除插件
		Plug_In plugIn = this.plugInService.getPlugInById(id);
		FileUtils.deleteFile(Constant.PLUGIN_REMOTE_PATH+plugIn.getUrl(),FileUtils.getSession211());
		this.plugInService.deletePlugIn(id);
		return "redirect:listPlugIn";
	}
	
	/**
	 * 修改插件hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdatePlugIn")
	public String toUpdatePlugIn(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		Plug_In plugIn = this.plugInService.getPlugInById(id);
		if(StringUtils.isBlank(plugIn.getCpu_type())){
			obj.put("cpuTypes", new String[]{});
		}else{
			obj.put("cpuTypes", plugIn.getCpu_type().split(","));
		}
		obj.put("plugIn", plugIn);
		return "plugIn/plugIn_update";
	}
	
	/**
	 * 修改插件hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updatePlugIn")
	public String updatePlugIn(Plug_In plugIn, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		plugIn.setUpdateDate(new Date());
		this.plugInService.updatePlugIn(plugIn);
		return "redirect:listPlugIn";
	}
}