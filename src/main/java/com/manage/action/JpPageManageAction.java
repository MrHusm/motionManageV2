package com.manage.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmind.core.util.DataUtil;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.domain.JpPageCondition;
import com.manage.model.Channel_Book_Review;
import com.manage.model.JP_BangDan;
import com.manage.model.JP_Banner_Position;
import com.manage.model.JP_Page;
import com.manage.service.BookReviewService;
import com.manage.service.JpBangBookService;
import com.manage.service.JpBangService;
import com.manage.service.JpBannerPositionService;
import com.manage.service.JpBannerService;
import com.manage.service.JpPageService;

@Controller
@RequestMapping("/jpPage")
public class JpPageManageAction extends AbstractAction{
	@Resource
	private JpPageService jpPageService;
	
	@Resource
	private JpBannerService jpBannerService;
	
	@Resource
	private JpBangService jpBangService;
	
	@Resource
	private JpBannerPositionService jpBannerPositionService;
	
	@Resource
	private JpBangBookService jpBangBookService;
	
	@Resource
	private BookReviewService bookReviewService;

	/**
	 * 根据条件查询精品页信息hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/listPage")
	public String listPage(JpPageCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String pageType = condition.getPageType();
		if(DataUtil.isEmpty(pageType)){
			condition.setPageType("1");
		}
		List<JP_Page> list = jpPageService.findBindByCondition(condition);
		obj.put("list", list);
		obj.put("condition",condition);
		return "page/page_list";
	}
	
	/**
	 * 添加精品页hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddJpPage")
	public String toAddJpPage(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String pageType = request.getParameter("pageType");
		String source = request.getParameter("source");
		obj.put("pageType", pageType);
		obj.put("source", source);
		return "page/page_add";
	}
	
	/**
	 * 添加精品页hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addJpPage")
	public String addJpPage(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		String type = request.getParameter("type");
		String pageType = request.getParameter("pageType");
		String source = request.getParameter("source");
		String ids = request.getParameter("ids");
		String[] keyIds = ids.split(",");
		Date now = new Date();
		List<JP_Page> pages = new ArrayList<JP_Page>();
		for(String keyId : keyIds){
			JP_Page page = new JP_Page();
			page.setKeyId(Integer.parseInt(keyId));
			JP_BangDan bang = null;
			JP_Banner_Position position = null;
			if("1".equals(type)){
				bang = this.jpBangService.getJpBangDById(keyId);
				if(bang != null){
					page.setName(bang.getName());
				}
			}else if("2".equals(type)){
				position = this.jpBannerPositionService.getBannerPositionById(keyId);
				if(position != null){
					page.setName(position.getName());
				}
			}
			if(bang != null || position != null){
				page.setPageType(Integer.parseInt(pageType));
				page.setSource(Integer.parseInt(source));
				page.setCreateDate(now);
				page.setUpdateDate(now);
				page.setType(Integer.parseInt(type));
				pages.add(page);
			}
		}
		this.jpPageService.saveJpPageList(pages);
		return "redirect:listPage?pageType="+pageType+"&source="+source;
	}
	
	/**
	 * 删除精品页hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deleteJpPage")
	public String deleteJpPage(String id,String pageType,String source,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.jpPageService.deleteJpPage(id);
		return "redirect:listPage?pageType="+pageType+"&source="+source;
	}
	
	/**
	 * 修改精品页hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdateJpPage")
	public String toUpdateJpPage(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		JP_Page page = this.jpPageService.getJpPageById(id);
		obj.put("page", page);
		return "page/page_update";
	}

	/**
	 * 修改精品页hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updateJpPage")
	public String updateJpPage(JP_Page page,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Integer keyId = page.getKeyId();
		Integer type = page.getType();
		if(keyId != null){
			if(type==1){
				JP_BangDan bang = this.jpBangService.getJpBangDById(String.valueOf(keyId));
				page.setName(bang.getName());
			}else if(type==2){
				JP_Banner_Position position = this.jpBannerPositionService.getBannerPositionById(String.valueOf(keyId));
				page.setName(position.getName());
			}
		}
		page.setUpdateDate(new Date());
		this.jpPageService.updateJpPage(page);
		return "redirect:listPage?pageType="+page.getPageType()+"&source="+page.getSource();
	}
	
	
	/**
	 * 重置rediskey hushengmeng
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/resetJpRedisCache")
	public String resetJpRedisCache(String pageType,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		jpBangBookService.resetRedisCache();
		jpBannerService.resetRedisCache();
		jpPageService.resetRedisCache();
		return "redirect:listPage?pageType="+pageType;
	}
	
	/**
	 * 导入图书评论
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/importBookReview")
	public String importBookReview(String pageType,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		List<Integer> list = new ArrayList<Integer>();
		list.add(21319890);
		list.add(80909690);
		list.add(22327959);
		list.add(85646108);
		list.add(22490636);
		list.add(85658430);
		list.add(85651697);
		list.add(22490636);
		list.add(149661);
		list.add(13301275);

		try {
			XSSFWorkbook xwb = new XSSFWorkbook("/data/www/111.xlsx");
			//XSSFWorkbook xwb = new XSSFWorkbook("d:/111.xlsx");
			XSSFRow row = null;
			XSSFSheet sheet = xwb.getSheetAt(0);
			Random random = new Random();
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				String bookId = row.getCell(0).toString();
				String title = row.getCell(1).toString();
				String content = row.getCell(2).toString();
				
				Channel_Book_Review bookReview = new Channel_Book_Review();
				bookReview.setReviewId(0);
				bookReview.setReviewUserId(0);
				bookReview.setLevelReviewId(0);
				bookReview.setBookId(bookId);
				bookReview.setUserId(list.get(random.nextInt(10)));
				bookReview.setTitle(title);
				bookReview.setContent(content);
				bookReview.setChannelId(3000);
				bookReview.setCreateDate(new Date());
				bookReview.setUpdateDate(new Date());
				bookReview.setAudit(1);
				bookReview.setType(5);
				bookReview.setReviewStatus(0);
				bookReviewService.saveBookReview(bookReview);
				System.out.println("---success----"+i+":"+bookId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:listPage?pageType="+pageType;
	}
	
	
	@RequestMapping("/makeIntro")
	public String makeIntro(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		this.jpPageService.done();
		return "redirect:listPage?pageType=1";
	}
}
