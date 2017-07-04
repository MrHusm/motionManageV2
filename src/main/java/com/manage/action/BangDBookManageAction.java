package com.manage.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import redis.clients.jedis.Jedis;

import com.manage.constant.Constant;
import com.manage.domain.JpBangBookCondition;
import com.manage.model.JP_BangDan;
import com.manage.model.JP_BangDan_Book;
import com.manage.service.JpBangBookService;
import com.manage.service.JpBangService;
import com.manage.util.RedisUtil;

@Controller
@RequestMapping("/jpBangDBook")
public class BangDBookManageAction extends AbstractAction{
	@Resource
	private JpBangBookService jpBangBookService;
	
	@Resource
	private JpBangService jpBangService;
	
	
	/**
	 * 根据条件查询榜单图书hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listBangDBook")
	public String listBangD(JpBangBookCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage=request.getParameter("dqPage")== null ? 1: Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		JP_BangDan bang = this.jpBangService.getJpBangDById(condition.getBangDanId());
		List<JP_BangDan_Book> list = new ArrayList<JP_BangDan_Book>();
		if(bang.getBookFrom() == 1){
			//查询总共条数
			totalRow = this.jpBangBookService.findCountByCondition(condition);
			//封装condition
			condition = (JpBangBookCondition)setPageInfo(condition,dqPage,totalRow,20);
			logger.info("condition：{}", condition.toString());
			list = jpBangBookService.findBangBookByCondition(condition);
		}else{
			Jedis jedis = RedisUtil.getJedis();
			try{
				//获取驱动书籍
				String bookKey = String.format(Constant.redis_Key_phqd, bang.getDriveId());
				List<String> jsons = jedis.lrange(bookKey,0,-1);
				List<JP_BangDan_Book> books = this.jpBangBookService.getBooksByIds(jsons);
				if(StringUtils.isNotBlank(condition.getBookId())){
					totalRow = 1;
					condition = (JpBangBookCondition)setPageInfo(condition,dqPage,totalRow,20);
					for(JP_BangDan_Book book : books){
						if(book.getBookId().equals(condition.getBookId())){
							list.add(book);
							break;
						}
					}
				}else{
					totalRow = books.size();
					condition = (JpBangBookCondition)setPageInfo(condition,dqPage,totalRow,20);
					for(int i = (dqPage-1)*20; i< books.size() && i < dqPage*20;i++){
						list.add(books.get(i));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				RedisUtil.closeJedis(jedis);
			}
		}
		obj.put("bangName", bang.getName());
		obj.put("bookFrom", bang.getBookFrom());
		obj.put("driveName", bang.getDriveName());
		obj.put("list", list);
		obj.put("condition",condition);
		return "bang/bangDanBook_list";
	}
	
	/**
	 * 添加榜单图书hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddBangDBook")
	public String toAddBangDBook(String bangDanId,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		obj.put("bangDanId", bangDanId);
		obj.put("source", source);
		return "bang/bangDanBook_add";
	}
	
	/**
	 * 添加榜单图书hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addBingDBook")
	public String addBingDBook(JP_BangDan_Book book,String bookId,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String[] bookIds = bookId.split(",");
		JP_BangDan bang = jpBangService.getJpBangDById(String.valueOf(book.getBangDanId()));
		Date now = new Date();
		for(String bid : bookIds){
			book.setBangDanName(bang.getName());
			book.setBookId(bid);
			Map<String,Object> book_v3 = this.jpBangBookService.getBookById(bid);
			if(!DataUtil.isEmpty(book_v3)){
				book.setBookName(String.valueOf(book_v3.get("book_name")));
				book.setImgUrl(String.valueOf(book_v3.get("img_url")));
				book.setAuthorName(String.valueOf(book_v3.get("author_penname")));
				book.setIntro(String.valueOf(book_v3.get("intro")));
				book.setCreateDate(now);
				book.setUpdateDate(now);
				this.jpBangBookService.saveJpBangDBook(book);
			}
		}
		return "redirect:listBangDBook?bangDanId="+book.getBangDanId()+"&source="+source;
	}
	
	/**
	 * 删除榜单图书hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteBangDBook")
	public String deleteBangDBook(String ids,String bangDanId,String dqPage,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String[] bdBookIds = ids.split(",");
		for(String id : bdBookIds){
			if(StringUtils.isNotBlank(id)){
				this.jpBangBookService.deleteJpBangDBook(id);
			}
		}
		return "redirect:listBangDBook?bangDanId="+bangDanId+"&dqPage="+dqPage+"&source="+source;
	}
	
	/**
	 * 修改榜单图书hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateBangDBook")
	public String toUpdateBangDBook(String id,String bangDanId,String dqPage,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		JP_BangDan_Book book = this.jpBangBookService.getJpBangDBookById(id);
		obj.put("book", book);
		obj.put("bangDanId", bangDanId);
		obj.put("dqPage", dqPage);
		obj.put("source", source);
		return "bang/bangDanBook_update";
	}

	/**
	 * 修改榜单图书hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateBangDBook")
	public String updateBangDBook(JP_BangDan_Book book,String dqPage,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Map<String,Object> book_v3 = this.jpBangBookService.getBookById(book.getBookId());
		if(!DataUtil.isEmpty(book_v3)){
			book.setBookName(String.valueOf(book_v3.get("book_name")));
			book.setImgUrl(String.valueOf(book_v3.get("img_url")));
			book.setAuthorName(String.valueOf(book_v3.get("author_penname")));
			book.setIntro(String.valueOf(book_v3.get("intro")));
			this.jpBangBookService.updateJpBangDBook(book);
		}
		return "redirect:listBangDBook?bangDanId="+book.getBangDanId()+"&dqPage="+dqPage+"&source="+source;
	}
}
