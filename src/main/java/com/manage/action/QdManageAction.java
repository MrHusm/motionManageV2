package com.manage.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;










import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.manage.domain.QdCondition;
import com.manage.model.Bookcategory_V3;
import com.manage.model.Channel_Paihangbang_Qudong;
import com.manage.service.BookcategoryService;
import com.manage.service.QdService;

@Controller
@RequestMapping("/qd")
public class QdManageAction  extends AbstractAction{

	@Resource
	private QdService qdService;
	@Resource
	private BookcategoryService bookcategoryService;
	
	/**
	 * 根据条件查询驱动规则信息  zhangzw
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
		totalRow = this.qdService.findCountByCondition(condition);
		//封装condition
		condition = (QdCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Channel_Paihangbang_Qudong> list = qdService.findQdByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "qd/qd_list";
	}
	
	/**
	 * To添加驱动规则  zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddQd")
	public String toAddQd(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		return "qd/qd_add";
	}
	/**
	 * 获得书籍二级分类  zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getBookTwoType")
	public void getBookTwoType(int id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj) throws IOException{
		List<Bookcategory_V3> bookCategorysList = bookcategoryService.findBookCategorysTwo(id);
		
		String json = JSONArray.toJSONString(bookCategorysList);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(json);
	}
	
	/**
	 * 添加驱动规则  zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addQd")
	public String addQd(String categoryIdTwo,String categoryIdTwo3,String categoryIdTwo2,Channel_Paihangbang_Qudong qd, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		qd.setCreateDate(new Date());
		qd.setUpdateDate(new Date());
		if(qd.getCategoryId()==0){
			if(!(categoryIdTwo3.equals("-1"))){
				qd.setCategoryId(Integer.valueOf(categoryIdTwo3));
				if(!(categoryIdTwo2.equals("-1"))&&!(categoryIdTwo2.equals("0"))){
					qd.setCategoryId(Integer.valueOf(categoryIdTwo2));
				}	
			}
			
		}else{
			if(StringUtils.isNotBlank(categoryIdTwo)&&!(categoryIdTwo.equals("-1"))&&!(categoryIdTwo.equals("0"))){
				qd.setCategoryId(Integer.valueOf(categoryIdTwo));
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if(qd.getEndDate().equals("")){
			qd.setEndDate(sdf.format(date));
		}
		if(qd.getStartDate().equals("")){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, -qd.getDatee());
			date = calendar.getTime();		
			qd.setStartDate(sdf.format(date));
		}
		this.qdService.saveQd(qd);
		return "redirect:listQd";
	}
	
	/**
	 * 删除驱动规则  zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteQd")
	public String deleteQd(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.qdService.deleteQd(id);
		return "redirect:listQd";
	}
	
	
	/**
	 * To修改驱动规则  zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateQd")
	public String toUpdateQd(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Channel_Paihangbang_Qudong qd = this.qdService.getQdById(id);
		List<Bookcategory_V3> bookCategorysList = bookcategoryService.findBookCategorys1();
		obj.put("bookCategorysList", bookCategorysList);
		obj.put("qd", qd);
		return "qd/qd_update";
	}

	
	
	/**
	 * 修改驱动规则  zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateQd")
	public String updateQd(String categoryIdTwo,String categoryIdTwo3,String categoryIdTwo2,Channel_Paihangbang_Qudong qd, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		qd.setUpdateDate(new Date());
		if(qd.getCategoryId()==0){
			if(!(categoryIdTwo3.equals("-1"))){
				qd.setCategoryId(Integer.valueOf(categoryIdTwo3));
				if(!(categoryIdTwo2.equals("-1"))&&!(categoryIdTwo2.equals("0"))){
					qd.setCategoryId(Integer.valueOf(categoryIdTwo2));
				}	
			}
			
		}else{
			if(StringUtils.isNotBlank(categoryIdTwo)&&!(categoryIdTwo.equals("-1"))&&!(categoryIdTwo.equals("0"))){
				qd.setCategoryId(Integer.valueOf(categoryIdTwo));
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if(qd.getEndDate().equals("")){
			qd.setEndDate(sdf.format(date));
		}
		if(qd.getStartDate().equals("")){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, -qd.getDatee());
			date = calendar.getTime();		
			qd.setStartDate(sdf.format(date));
		}
		this.qdService.updateQd(qd);
		return "redirect:listQd";
	}
	
}
