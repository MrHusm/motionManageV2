package com.manage.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.BookCondition;
import com.manage.model.Book_V3;
import com.manage.model.Channel_Paihangbang;
import com.manage.service.BookService;
import com.manage.service.PhbService;


@Controller
@RequestMapping("/book")
public class BookManageAction extends AbstractAction {

	
	
	@Resource
	private BookService bookService;
	@Resource
	private PhbService phbService;
	
	/**
	 * 根据条件查询书籍信息   zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listBook")
	public String listBook(String phbid,BookCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		try {
			condition.setPhb_name( new String(condition.getPhb_name().getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.bookService.findCountByCondition(condition);
		//封装condition
		condition = (BookCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Book_V3> list1 = bookService.findBindByCondition(condition);
		List<Book_V3>  list = new LinkedList<Book_V3>(); 
		Channel_Paihangbang phb = phbService.getPhbById(phbid);
		String bdIds = phb.getBids();
		if(bdIds!=null&&bdIds!=""){
			String[] split = bdIds.split(",");
			 for (int i = 0; i < split.length; i++) {
				 for (Book_V3 book_V3 : list1) {
					 if(split[i].equals(book_V3.getZc_id())){
						 book_V3.setXl(i);
						 list.add(book_V3);
					 }
					}
			}	
		}
		obj.put("list", list);
		obj.put("condition",condition);
		obj.put("phb",phb);
		return "book/book_list";
	}
	/**
	 * To添加排行榜书籍 zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddBook")
	public String toAddPBook(Channel_Paihangbang phb,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		phb = phbService.getPhbById(phb.getId());
		obj.put("phb", phb);
		return "book/book_add";
	}
	
	/**
	 * 添加排行榜书籍 zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addBook")
	public String addBook(String ids,Channel_Paihangbang phb, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		phb.setUpdateDate(new Date());
		if(phb.getBdIds()!=null&&phb.getBdIds()!=""){//添加子榜单id
			phb.setBdIds(phb.getBdIds()+","+ids);
		}else{
			phb.setBdIds(ids);
		}
		this.phbService.updatePhb(phb);
		return "redirect:/phb/listPhb";
	}
}
