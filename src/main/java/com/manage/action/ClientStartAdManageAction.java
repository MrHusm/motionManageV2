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
import com.manage.domain.ApiAdvertCondition;
import com.manage.domain.ClientStartAdCondition;
import com.manage.model.Api_Advert;
import com.manage.model.Channel_Version_V3;
import com.manage.model.Client_Start_Ad;
import com.manage.service.ApiAdvertService;
import com.manage.service.ClientStartAdService;
import com.manage.service.VersionService;
import com.manage.util.FileUtils;

@Controller
@RequestMapping("/clientAd")
public class ClientStartAdManageAction extends AbstractAction{
	@Resource
	private ClientStartAdService clientStartAdService;
	@Resource
	private ApiAdvertService apiAdService;
	@Resource
	private VersionService versionService;

	/**
	 * 根据条件查询启动图hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listClientAd")
	public String listClientAd(ClientStartAdCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.clientStartAdService.findCountByCondition(condition);
		//封装condition
		condition = (ClientStartAdCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Client_Start_Ad> list = clientStartAdService.findClientAdByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "clientAd/clientAd_list";
	}
	
	/**
	 * 添加启动图hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddClientAd")
	public String toAddClientAd(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		List<Api_Advert> apis = apiAdService.findApiAdByCondition(new ApiAdvertCondition());
		obj.put("versions", versions);
		obj.put("apis", apis);
		return "clientAd/clientAd_add";
	}
	
	/**
	 * 添加启动图hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addClientAd")
	public String addClientAd(Client_Start_Ad clientAd, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj,MultipartFile file){
		if(0==clientAd.getType()){
			if(file != null && !file.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath("/") + "startImage/";
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
				FileUtils.uploadFile(filePath, Constant.START_AD_REMOTE_PATH,FileUtils.getSession243());
				clientAd.setImgUrl(replace);
			}
		}else{
			clientAd.setImgUrl(null);
			clientAd.setUrl(null);
		}
		clientAd.setCreateDate(new Date());
		clientAd.setUpdateDate(new Date());
		this.clientStartAdService.saveClientAd(clientAd);
		return "redirect:listClientAd";
	}
	
	/**
	 * 删除启动图hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteClientAd")
	public String deleteClientAd(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		//删除上传的图片
		Client_Start_Ad clientAd = this.clientStartAdService.getClientAdById(id);
		if(0 == clientAd.getType() && StringUtils.isNotBlank(clientAd.getImgUrl())){
			FileUtils.deleteFile(Constant.START_AD_REMOTE_PATH+clientAd.getImgUrl(),FileUtils.getSession243());
		}
		
		this.clientStartAdService.deleteClientAdById(id);
		return "redirect:listClientAd";
	}
	
	/**
	 * 修改启动图hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateClientAd")
	public String toUpdateClientAd(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Client_Start_Ad clientAd = this.clientStartAdService.getClientAdById(id);
		List<Channel_Version_V3> versions = this.versionService.getVersions();
		List<Api_Advert> apis = apiAdService.findApiAdByCondition(new ApiAdvertCondition());
		obj.put("apis", apis);
		obj.put("versions", versions);
		obj.put("clientAd", clientAd);
		return "clientAd/clientAd_update";
	}
	
	/**
	 * 修改启动图hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateClientAd")
	public String updateClientAd(Client_Start_Ad clientAd, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.clientStartAdService.updateClientAd(clientAd);
		return "redirect:listClientAd";
	}
}