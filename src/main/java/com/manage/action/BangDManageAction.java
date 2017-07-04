package com.manage.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmind.core.util.DataUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.JpBangCondition;
import com.manage.model.Channel_Paihangbang_Qd;
import com.manage.model.Channel_Paihangbang_Qudong;
import com.manage.model.JP_BangDan;
import com.manage.model.JP_BangDan_Label;
import com.manage.service.JpBangBookService;
import com.manage.service.JpBangLabelService;
import com.manage.service.JpBangService;
import com.manage.service.QdService;
import com.manage.service.QuDongService;

@Controller
@RequestMapping("/jpBangd")
public class BangDManageAction extends AbstractAction{
	@Resource
	private JpBangService jpBangService;
	
	@Resource
	private JpBangLabelService jpBangLabelService;
	
	@Resource
	private JpBangBookService jpBangBookService;
	
	@Resource
	private QdService qdService;
	
	@Resource
	private QuDongService qudongService;
	
	/**
	 * 根据条件查询榜单信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listBangD")
	public String listBangD(JpBangCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.jpBangService.findCountByCondition(condition);
		//封装condition
		condition = (JpBangCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<JP_BangDan> list = jpBangService.findBangByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "bang/bangDan_list";
	}
	
	/**
	 * 添加榜单hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddBangD")
	public String toAddBangD(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String source = request.getParameter("source");
		//List<Channel_Paihangbang_Qudong> driveList = phbService.findQdList();
		List<Channel_Paihangbang_Qd> driveList = qudongService.findQudongList();
		obj.put("driveList", driveList);
		obj.put("source", source);
		return "bang/bangDan_add";
	}
	
	/**
	 * 添加榜单hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addBingD")
	public String addBingD(JP_BangDan bang,String names,String types,String urls,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Date now = new Date();
		bang.setCreateDate(now);
		bang.setUpdateDate(now);
		if(bang.getBookFrom() == 2){
			Channel_Paihangbang_Qd qd = this.qudongService.getQdById(String.valueOf(bang.getDriveId()));
			bang.setDriveName(qd.getName());
		}
		//如果选择跳转频道
		if("4".equals(bang.getMoreFlag())){
			if(!"1".equals(bang.getMoreUrl())&&!"2".equals(bang.getMoreUrl())&&!"3".equals(bang.getMoreUrl())&&!"4".equals(bang.getMoreUrl())){
				bang.setMoreUrl("1");
			}
		}
		
		bang = this.jpBangService.saveJpBangD(bang);
		
		if(!DataUtil.isEmpty(names)){
			String[] ns = names.split(",");
			String[] ts = types.split(",");
			String[] us = urls.split(",");
			for(int i = 0; i < ns.length; i++){
				JP_BangDan_Label label = new JP_BangDan_Label();
				label.setName(ns[i]);
				label.setType(Integer.parseInt(ts[i]));
				label.setUrl(us[i]);
				label.setBangDanId(bang.getId());
				label.setCreateDate(now);
				label.setUpdateDate(now);
				this.jpBangLabelService.saveJpBangDLabel(label);
			}
		}
		return "redirect:listBangD?source="+bang.getSource();
	}
	
	/**
	 * 删除榜单hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteBangD")
	public String deleteBangD(String id,String dqPage,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.jpBangService.deleteJpBangD(id);
		this.jpBangBookService.deleteJpBangDBooksByBdId(id);
		return "redirect:listBangD?dqPage="+dqPage+"&source="+source;
	}
	
	/**
	 * 修改榜单hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateBangD")
	public String toUpdateBangD(String id,String dqPage,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		JP_BangDan bang = this.jpBangService.getJpBangDById(id);
		List<JP_BangDan_Label> labels = this.jpBangLabelService.getJpBangDLabelsByBdId(id);
		
		//List<Channel_Paihangbang_Qudong> driveList = phbService.findQdList();
		List<Channel_Paihangbang_Qd> driveList = qudongService.findQudongList();
		obj.put("driveList", driveList);
		obj.put("bang", bang);
		obj.put("labels", labels);
		obj.put("dqPage", dqPage);
		return "bang/bangDan_update";
	}

	/**
	 * 修改榜单hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateBangD")
	public String updateBangD(JP_BangDan bang,String names,String types,String urls,String dqPage,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		if(bang.getBookFrom() == 2){
			Channel_Paihangbang_Qd qd = this.qudongService.getQdById(String.valueOf(bang.getDriveId()));
			bang.setDriveName(qd.getName());
		}else{
			bang.setDriveId(null);
			bang.setDriveName(null);
		}
		//如果选择跳转频道
		if("4".equals(bang.getMoreFlag())){
			if(!"1".equals(bang.getMoreUrl())&&!"2".equals(bang.getMoreUrl())&&!"3".equals(bang.getMoreUrl())&&!"4".equals(bang.getMoreUrl())){
				bang.setMoreUrl("1");
			}
		}
		this.jpBangService.updateJpBangD(bang);
		this.jpBangLabelService.deleteJpBangDLabelsByBdId(String.valueOf(bang.getId()));
		
		Date now = new Date();
		if(!DataUtil.isEmpty(names)){
			String[] ns = names.split(",");
			String[] ts = types.split(",");
			String[] us = urls.split(",");
			for(int i = 0; i < ns.length; i++){
				JP_BangDan_Label label = new JP_BangDan_Label();
				label.setName(ns[i]);
				label.setType(Integer.parseInt(ts[i]));
				label.setUrl(us[i]);
				label.setBangDanId(bang.getId());
				label.setCreateDate(now);
				label.setUpdateDate(now);
				this.jpBangLabelService.saveJpBangDLabel(label);
			}
		}
		return "redirect:listBangD?dqPage="+dqPage+"&source="+bang.getSource();
	}
}
