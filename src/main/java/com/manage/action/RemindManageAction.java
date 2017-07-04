package com.manage.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.manage.constant.Constant;
import com.manage.domain.RemindCondition;
import com.manage.model.Exit_Remind;
import com.manage.service.RemindService;
import com.manage.util.FileUtils;

@Controller
@RequestMapping("/remind")
public class RemindManageAction extends AbstractAction{
	@Resource
	private RemindService remindService;

	/**
	 * 根据条件查询退出提醒信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listRemind")
	public String listRemind(RemindCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.remindService.findCountByCondition(condition);
		//封装condition
		condition = (RemindCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Exit_Remind> list = remindService.findRemindByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "remind/remind_list";
	}
	
	/**
	 * 添加退出提醒hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddRemind")
	public String toAddRemind(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		boolean mrFlag = true;
		boolean qdFlag = true;
		RemindCondition condition = new RemindCondition();
		condition.setType("0");
		int mrCount = this.remindService.findCountByCondition(condition);
		if(mrCount > 0){
			mrFlag = false;
		}
		condition.setType("1");
		int qdCount = this.remindService.findCountByCondition(condition);
		if(qdCount > 0){
			qdFlag = false;
		}
		request.setAttribute("mrFlag", mrFlag);
		request.setAttribute("qdFlag", qdFlag);
		return "remind/remind_add";
	}
	
	/**
	 * 添加退出提醒hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addRemind")
	public String addRemind(Exit_Remind remind, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj,MultipartFile file){
		if(!"2".equals(remind.getType())){
			remind.setStartDate(null);
			remind.setEndDate(null);
		}
		if("0".equals(remind.getIsImg())){
			remind.setContent(null);
			if(file != null && !file.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath("/") + "remindImage/";
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
				FileUtils.uploadFile(filePath, Constant.REMIND_REMOTE_PATH,FileUtils.getSession243());
				remind.setImgUrl(replace);
			}
		}
		remind.setCreateDate(new Date());
		remind.setUpdateDate(new Date());
		this.remindService.saveRemind(remind);
		return "redirect:listRemind";
	}
	
	/**
	 * 删除退出提醒hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteRemind")
	public String deleteRemind(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		//删除上传的图片
		Exit_Remind remind = this.remindService.getRemindById(id);
		if("0".equals(remind.getIsImg()) && StringUtils.isNotBlank(remind.getImgUrl())){
			FileUtils.deleteFile(Constant.REMIND_REMOTE_PATH+remind.getImgUrl(),FileUtils.getSession243());
		}
		
		this.remindService.deleteRemind(id);
		return "redirect:listRemind";
	}
	
	/**
	 * 修改退出提醒hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateRemind")
	public String toUpdateRemind(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Exit_Remind remind = this.remindService.getRemindById(id);
		obj.put("remind", remind);
		return "remind/remind_update";
	}
	
	/**
	 * 修改退出提醒hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateRemind")
	public String updateRemind(Exit_Remind remind, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		remind.setUpdateDate(new Date());
		this.remindService.updateRemind(remind);
		return "redirect:listRemind";
	}
	
	/**
	 * 判断起止日期是否有交叉
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/isValidDate")
	public void isValidDate(String startDate,String endDate, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Map<String, Boolean> map = new HashMap<String,Boolean>();
		RemindCondition condition = new RemindCondition();
		condition.setStartDate(startDate);
		condition.setEndDate(endDate);
		int count = this.remindService.findCountByCondition(condition);
		if(count>0){
			map.put("flag", true);
		}else{
			map.put("flag", false);
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		this.responseText(response, request, jsonObject.toString());
	}
}