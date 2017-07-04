package com.manage.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.VersionUpCondition;
import com.manage.model.Channel_Version_V3;
import com.manage.model.Channel_Version;
import com.manage.service.VersionUpService;
import com.manage.service.VersionService;

@Controller
@RequestMapping("/versionUp")
public class VersionUpManageAction extends AbstractAction{
	@Resource
	private VersionUpService versionUpService;
	@Resource
	private VersionService versionService;

	/**
	 * 根据条件查询版本升级信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listVersionUp")
	public String listVersionUp(VersionUpCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.versionUpService.findCountByCondition(condition);
		//封装condition
		condition = (VersionUpCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Channel_Version> list = versionUpService.findVersionUpByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		obj.put("versions", versions);
		return "remind/version_up_list";
	}
	
	/**
	 * 添加版本升级hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddVersionUp")
	public String toAddVersionUp(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		obj.put("versions", versions);
		return "remind/version_up_add";
	}
	
	/**
	 * 添加版本升级hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addVersionUp")
	public String addVersionUp(Channel_Version versionUp, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String version = versionUp.getVersion();
		version = version.replaceAll("\\.", "");
		versionUp.setVersion(version);
		versionUp.setType("1");
		String[] channelIds = versionUp.getChannelId().split(",");
		VersionUpCondition condition = new VersionUpCondition();
		//condition.setVersion(versionUp.getVersion());
		for(String channelId : channelIds){
			channelId = channelId.trim();
			condition.setChannelId(channelId);
			versionUp.setChannelId(channelId);
			int count = this.versionUpService.findCountByCondition(condition);
			if(count > 0){
				this.versionUpService.updateVersionUp(versionUp);
			}else{
				this.versionUpService.saveVersionUp(versionUp);
			}
		}
		return "redirect:listVersionUp";
	}
	
	/**
	 * 删除版本升级hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteVersionUp")
	public String deleteVerRemind(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.versionUpService.deleteVersionUp(id);
		return "redirect:listVersionUp";
	}
	
	/**
	 * 修改版本升级hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateVersionUp")
	public String toUpdateVersionUp(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Channel_Version versionUp = this.versionUpService.getVersionUpById(id);
		obj.put("versionUp", versionUp);
		return "remind/version_up_update";
	}
	
	/**
	 * 修改版本升级hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateVersionUp")
	public String updateVersionUp(String id,String description, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Channel_Version versionUp = this.versionUpService.getVersionUpById(id);
		versionUp.setDescription(description);
		this.versionUpService.updateVersionUp(versionUp);
		return "redirect:listVersionUp";
	}
}