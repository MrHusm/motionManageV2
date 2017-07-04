package com.manage.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.QdCondition;
import com.manage.model.Channel_Paihangbang_Qd;
import com.manage.service.QuDongService;

/**
 * 
 * @author hushengmeng
 *
 */
@Controller
@RequestMapping("/qudong")
public class QuDongManageAction extends AbstractAction{
	@Resource
	private QuDongService qudongService;
	
	/**
	 * 根据条件查询驱动规则信息 hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listQd")
	public String listQd(QdCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.qudongService.findCountByCondition(condition);
		//封装condition
		condition = (QdCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Channel_Paihangbang_Qd> list = qudongService.findQdByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "qudong/qd_list";
	}
}
