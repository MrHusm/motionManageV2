package com.manage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmind.core.util.DataUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.CodeCondition;
import com.manage.model.Exchange_Code;
import com.manage.model.Exchange_Code_Rule;
import com.manage.service.CodeRuleService;
import com.manage.service.CodeService;
import com.manage.util.CommonUtil;

@Controller
@RequestMapping("/code")
public class CodeManageAction extends AbstractAction{
	@Resource
	private CodeService codeService;
	
	@Resource
	private CodeRuleService codeRuleService;
	
	/**
	 * 根据条件查询兑换码信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listCode")
	public String listCode(CodeCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String type = condition.getType();
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		if("1".equals(type)){
			//查询总共条数
			totalRow = this.codeService.findCountByCondition(condition);
			//封装condition
			condition = (CodeCondition)setPageInfo(condition,dqPage,totalRow,20);
			logger.info("condition：{}", condition.toString());
			List<Exchange_Code> list = codeService.findCodeByCondition(condition);
			obj.put("list", list);
		}else if("2".equals(type)){
			//查询总共条数
			totalRow = this.codeRuleService.findCountByCondition(condition);
			//封装condition
			condition = (CodeCondition)setPageInfo(condition,dqPage,totalRow,20);
			logger.info("condition：{}", condition.toString());
			List<Exchange_Code_Rule> list = codeRuleService.findCodeRuleByCondition(condition);
			for(Exchange_Code_Rule codeRule : list){
				String ecrId = codeRule.getId();
				CodeCondition con = new CodeCondition();
				con.setEcrId(ecrId);
				int count = this.codeService.findCountByCondition(con);
				if(count > 0){
					codeRule.setFlag("true");
				}else{
					codeRule.setFlag("false");
				}
			}
			obj.put("list", list);
		}
		obj.put("condition",condition);
		if("2".equals(type)){
			return "code/code_rule_list";
		}else{
			return "code/code_list";
		}
	}
	
	/**
	 * 导出数据
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/exportCode")
	public String exportCode(CodeCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		logger.info("condition：{}", condition.toString());
		condition.setStartRow(0);
		condition.setPageSize(999999);
		List<Exchange_Code> list = codeService.findCodeByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "code/code_export";
	}
	
	/**
	 * 添加兑换码hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddCode")
	public String toAddCode(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		return "code/code_rule_add";
	}
	
	/**
	 * 添加公共兑换码hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addCode")
	public String addCode(Exchange_Code code, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String endDate = code.getEndDate();
		code.setEndDate(endDate+" 23:59:59");
		code.setCode(CommonUtil.randCodeArr(6,1)[0]);
		code.setChannelId("0");
		code.setEcrId("0");
		code.setCreateDate(new Date());
		code.setUpdateDate(new Date());

		if(DataUtil.isEmpty(code.getMoney())){
			code.setMoney("0");
		}
		if(code.getByIds() == null){
			code.setByIds("");
		}
		if(code.getChpIds() == null){
			code.setChpIds("");
		}
		if(code.getBookIds() == null){
			code.setBookIds("");
		}
		
		String bids = "";
		String cids = "";
		if(!DataUtil.isEmpty(code.getBookIds()) && !DataUtil.isEmpty(code.getChpIds())){
			String[] bookIds = code.getBookIds().split(",");
			String[] chpIds = code.getChpIds().split(",");
			for(int i = 0; i < bookIds.length; i++){
				String chpId = chpIds[i];
				if("0".equals(chpId)){
					bids += bookIds[i]+",";
				}else{
					cids += bookIds[i]+",0,"+chpId+";";
				}
			}
			if(!DataUtil.isEmpty(bids)){
				code.setBookIds(bids.substring(0, bids.length()-1));
			}else{
				code.setBookIds("");
			}
			
			if(!DataUtil.isEmpty(cids)){
				code.setChpIds(cids.substring(0, cids.length()-1));
			}else{
				code.setChpIds("");
			}
		}
		
		if(DataUtil.isEmpty(code.getCashCouponId())){
			code.setCashCouponId("0");
		}
		if(DataUtil.isEmpty(code.getVipDay())){
			code.setVipDay("0");
		}
		this.codeService.saveCode(code);
		return "redirect:listCode?type=1";
	}
	
	/**
	 * 添加单一兑换码hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addCodeRule")
	public String addCodeRule(Exchange_Code_Rule codeRule, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String endDate = codeRule.getEndDate();
		codeRule.setEndDate(endDate+" 23:59:59");
		codeRule.setChannelId("0");
		codeRule.setCreateDate(new Date());
		codeRule.setUpdateDate(new Date());

		if(DataUtil.isEmpty(codeRule.getMoney())){
			codeRule.setMoney("0");
		}
		if(codeRule.getByIds() == null){
			codeRule.setByIds("");
		}
		
		if(codeRule.getBookIds() == null){
			codeRule.setBookIds("");
		}
		
		if(codeRule.getChpIds() == null){
			codeRule.setChpIds("");
		}
		
		String bids = "";
		String cids = "";
		if(!DataUtil.isEmpty(codeRule.getBookIds()) && !DataUtil.isEmpty(codeRule.getChpIds())){
			String[] bookIds = codeRule.getBookIds().split(",");
			String[] chpIds = codeRule.getChpIds().split(",");
			for(int i = 0; i < bookIds.length; i++){
				String chpId = chpIds[i];
				if("0".equals(chpId)){
					bids += bookIds[i]+",";
				}else{
					cids += bookIds[i]+",0,"+chpId+";";
				}
			}
			if(!DataUtil.isEmpty(bids)){
				codeRule.setBookIds(bids.substring(0, bids.length()-1));
			}else{
				codeRule.setBookIds("");
			}
			
			if(!DataUtil.isEmpty(cids)){
				codeRule.setChpIds(cids.substring(0, cids.length()-1));
			}else{
				codeRule.setChpIds("");
			}
		}
		
		if(DataUtil.isEmpty(codeRule.getCashCouponId())){
			codeRule.setCashCouponId("0");
		}
		if(DataUtil.isEmpty(codeRule.getVipDay())){
			codeRule.setVipDay("0");
		}
		this.codeRuleService.saveCodeRule(codeRule);
		
		String[] arr=CommonUtil.randCodeArr(6-codeRule.getTop_name().length(),Integer.parseInt(codeRule.getCc()));
		List<Exchange_Code> list = new ArrayList<Exchange_Code>();
		for(String item:arr){
			Exchange_Code code = new Exchange_Code();
			BeanUtils.copyProperties(codeRule, code);
			code.setId(null);
			code.setCode(codeRule.getTop_name()+item);
			code.setCc(codeRule.getAllCc());
			code.setExchgNum("0");
			code.setEcrId(codeRule.getId());
			code.setCreateDate(new Date());
			code.setUpdateDate(new Date());
			list.add(code);
		}
		this.codeService.saveCodeRuleList(list);
		
		return "redirect:listCode?type=2";
	}
	
	/**
	 * 删除兑换码hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteCode")
	public String deleteCode(String id,String type, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		if("1".equals(type)){
			this.codeService.deleteCode(id);
		}else if("2".equals(type)){
			this.codeRuleService.deleteCodeRule(id);
			this.codeService.deleteCodeByRule(id);
		}
		return "redirect:listCode?type=" + type;
	}
	
	
	/**
	 * 修改兑换码hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateCode")
	public String toUpdateCode(String id,String type,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String bookId = "";
		String chpId = "";
		if("1".equals(type)){
			Exchange_Code code = this.codeService.getCodeById(id);
			bookId = code.getBookIds() == null ? "" : code.getBookIds();
			chpId = code.getChpIds() == null ? "" : code.getChpIds();
			obj.put("topName", "");
			obj.put("code", code);
		}else if("2".equals(type)){
			Exchange_Code_Rule code = this.codeRuleService.getCodeRuleById(id);
			bookId = code.getBookIds() == null ? "" : code.getBookIds();
			chpId = code.getChpIds() == null ? "" : code.getChpIds();
			
			String ecrId = code.getId();
			CodeCondition con = new CodeCondition();
			con.setEcrId(ecrId);
			int count = this.codeService.findCountByCondition(con);
			if(count > 0){
				obj.put("flag", "true");
			}else{
				obj.put("flag", "false");
			}
			
			obj.put("topName", code.getTop_name());
			obj.put("code", code);
		}
		List<String[]> ids = new ArrayList<String[]>();
		String[] bookIds = bookId.split(",");
		String[] chpIds = chpId.split(";");
		
		for(String bid : bookIds){
			if(!DataUtil.isEmpty(bid)){
				String[] bcid = new String[2];
				bcid[0] = bid;
				bcid[1] = "0";
				ids.add(bcid);
			}
		}
		for(String cid : chpIds){
			if(!DataUtil.isEmpty(cid)){
				String[] bcid = new String[2];
				bcid[0] = cid.split(",")[0];
				bcid[1] = cid.split(",")[2];
				ids.add(bcid);
			}
		}
		obj.put("ids", ids);
		obj.put("type", type);
		return "code/code_rule_update";
	}
	
	/**
	 * 修改公共兑换码hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateCode")
	public String updateCode(Exchange_Code code, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		code.setEndDate(code.getEndDate()+" 23:59:59");
		code.setUpdateDate(new Date());
		if(DataUtil.isEmpty(code.getMoney())){
			code.setMoney("0");
		}
		if(code.getByIds() == null){
			code.setByIds("");
		}
		if(code.getChpIds() == null){
			code.setChpIds("");
		}
		if(code.getBookIds() == null){
			code.setBookIds("");
		}
		
		String bids = "";
		String cids = "";
		if(!DataUtil.isEmpty(code.getBookIds()) && !DataUtil.isEmpty(code.getChpIds())){
			String[] bookIds = code.getBookIds().split(",");
			String[] chpIds = code.getChpIds().split(",");
			for(int i = 0; i < bookIds.length; i++){
				String chpId = chpIds[i];
				if("0".equals(chpId)){
					bids += bookIds[i]+",";
				}else{
					cids += bookIds[i]+",0,"+chpId+";";
				}
			}
			if(!DataUtil.isEmpty(bids)){
				code.setBookIds(bids.substring(0, bids.length()-1));
			}else{
				code.setBookIds("");
			}
			
			if(!DataUtil.isEmpty(cids)){
				code.setChpIds(cids.substring(0, cids.length()-1));
			}else{
				code.setChpIds("");
			}
		}
		
		if(DataUtil.isEmpty(code.getCashCouponId())){
			code.setCashCouponId("0");
		}
		if(DataUtil.isEmpty(code.getVipDay())){
			code.setVipDay("0");
		}
		this.codeService.updateCode(code);
		return "redirect:listCode?type=1";
	}
	
	/**
	 * 修改单一兑换码hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateCodeRule")
	public String updateCodeRule(Exchange_Code_Rule codeRule, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		codeRule.setEndDate(codeRule.getEndDate()+" 23:59:59");
		codeRule.setUpdateDate(new Date());
		if(DataUtil.isEmpty(codeRule.getMoney())){
			codeRule.setMoney("0");
		}
		if(codeRule.getByIds() == null){
			codeRule.setByIds("");
		}
		if(codeRule.getChpIds() == null){
			codeRule.setChpIds("");
		}
		if(codeRule.getBookIds() == null){
			codeRule.setBookIds("");
		}
		
		String bids = "";
		String cids = "";
		if(!DataUtil.isEmpty(codeRule.getBookIds()) && !DataUtil.isEmpty(codeRule.getChpIds())){
			String[] bookIds = codeRule.getBookIds().split(",");
			String[] chpIds = codeRule.getChpIds().split(",");
			for(int i = 0; i < bookIds.length; i++){
				String chpId = chpIds[i];
				if("0".equals(chpId)){
					bids += bookIds[i]+",";
				}else{
					cids += bookIds[i]+",0,"+chpId+";";
				}
			}
			if(!DataUtil.isEmpty(bids)){
				codeRule.setBookIds(bids.substring(0, bids.length()-1));
			}else{
				codeRule.setBookIds("");
			}
			
			if(!DataUtil.isEmpty(cids)){
				codeRule.setChpIds(cids.substring(0, cids.length()-1));
			}else{
				codeRule.setChpIds("");
			}
		}
		
		if(DataUtil.isEmpty(codeRule.getCashCouponId())){
			codeRule.setCashCouponId("0");
		}
		if(DataUtil.isEmpty(codeRule.getVipDay())){
			codeRule.setVipDay("0");
		}
		this.codeRuleService.updateCodeRule(codeRule);
		this.codeService.updateCode(codeRule);
		return "redirect:listCode?type=2";
	}
	
	
	
	/**
	 * 生成公共兑换码hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/makeCodeByCodeRule")
	public String makeCodeByCodeRule(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Exchange_Code_Rule codeRule = this.codeRuleService.getCodeRuleById(id);
		String[] arr=CommonUtil.randCodeArr(8-codeRule.getTop_name().length(),Integer.parseInt(codeRule.getCc()));
		List<Exchange_Code> list = new ArrayList<Exchange_Code>();
		for(String item:arr){
			Exchange_Code code = new Exchange_Code();
			BeanUtils.copyProperties(codeRule, code);
			code.setId(null);
			code.setCode(codeRule.getTop_name()+item);
			code.setCc(codeRule.getUserCc());
			code.setEcrId(codeRule.getId());
			code.setCreateDate(new Date());
			code.setUpdateDate(new Date());
			list.add(code);
		}
		this.codeService.saveCodeRuleList(list);
		return "redirect:listCode?type=2";
	}
}