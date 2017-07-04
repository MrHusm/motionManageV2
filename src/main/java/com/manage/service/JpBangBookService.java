package com.manage.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.manage.constant.Constant;
import com.manage.dao.JpBangBookDao;
import com.manage.domain.JpBangBookCondition;
import com.manage.domain.JpBangCondition;
import com.manage.model.JP_BangDan;
import com.manage.model.JP_BangDan_Book;
import com.manage.util.CommonUtil;
import com.manage.util.RedisUtil;

@Service("jpBangBookService")
public class JpBangBookService{

    @Resource
    private JpBangBookDao jpBangBookDao;
    
	@Resource
	private JpBangService jpBangService;
    
    /**
     * 根据条件查询榜单图书数量
     * @param condition
     * @return
     */
    public int findCountByCondition(JpBangBookCondition condition) {
		String bookId = condition.getBookId();
		String bookName = condition.getBookName();
		String bangDanId = condition.getBangDanId();
		StringBuffer sql = new StringBuffer("select count(*) from jp_bangdan_book where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(bookId)){
			sql.append(" and bookId = ? ");
			args.add(bookId);
		}
		if(StringUtils.isNotBlank(bookName)){
			sql.append(" and bookName like ? ");
			args.add("%"+bookName+"%");
		}
		if(StringUtils.isNotBlank(bangDanId)){
			sql.append(" and bangDanId = ? ");
			args.add(bangDanId);
		}
		return this.jpBangBookDao.findForInt(sql.toString(), args.toArray());
	}
    
    /**
     * 根据条件分页查询榜单图书
     * @param condition
     * @return
     */
    public List<JP_BangDan_Book> findBangBookByCondition(JpBangBookCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
    	String bookId = condition.getBookId();
		String bookName = condition.getBookName();
		String bangDanId = condition.getBangDanId();
		StringBuffer sql = new StringBuffer("select * from jp_bangdan_book where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(bookId)){
			sql.append(" and bookId = ? ");
			args.add(bookId);
		}
		if(StringUtils.isNotBlank(bookName)){
			sql.append(" and bookName like ? ");
			args.add("%"+bookName+"%");
		}
		if(StringUtils.isNotBlank(bangDanId)){
			sql.append(" and bangDanId = ? ");
			args.add(bangDanId);
		}
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		List<JP_BangDan_Book> list = this.jpBangBookDao.find(sql.toString(), args.toArray());
		return list;
	}
    
    /**
     * 保存榜单图书
     * @param label
     */
    public void saveJpBangDBook(JP_BangDan_Book book){
    	this.jpBangBookDao.insert(book);
    }
    
    /**
     * 根据ID获取榜单图书
     * @param id
     * @return
     */
    public JP_BangDan_Book getJpBangDBookById(String id){
    	String sql = "select * from jp_bangdan_book where id = ?";
		return this.jpBangBookDao.findOne(sql, id);
    }
    
    /**
     * 根据ID获取图书
     * @param id
     * @return
     */
    public Map<String,Object> getBookById(String id){
    	String sql = "select img_url,author_penname,CASE WHEN c.newBookName IS NULL OR c.newBookName='' THEN b.book_name ELSE c.newBookName END book_name,"
    			+ "CASE WHEN c.intro IS NULL OR c.intro='' THEN b.introduction ELSE c.intro END intro from book_v3 b,channel_lib_book c where b.zc_id=c.bookId and c.channelId=3000 and b.zc_id = ?";
		return this.jpBangBookDao.findForMap(sql, id);
    }
    
    /**
     * 获取驱动榜单中图书
     * @param jsons
     */
	public List<JP_BangDan_Book> getBooksByIds(List<String> jsons) {
		String sql = "select zc_id,img_url,author_penname,CASE WHEN c.newBookName IS NULL OR c.newBookName='' THEN b.book_name ELSE c.newBookName END book_name,"
    			+ "CASE WHEN c.intro IS NULL OR c.intro='' THEN b.introduction ELSE c.intro END intro from book_v3 b,channel_lib_book c where b.zc_id=c.bookId and c.channelId=3000 and zc_id=?";
		List<JP_BangDan_Book> books = new ArrayList<JP_BangDan_Book>();
		if(jsons != null && jsons.size() > 0){
			for(int i = 0; i < jsons.size(); i++){
				String json = jsons.get(i);
				JSONObject jsonObject = JSONObject.fromObject(json);
				String bid = (String)jsonObject.get("bid");
				Map<String,Object> book_v3 = this.jpBangBookDao.findForMap(sql, new Object[]{bid});
				if(book_v3 != null && !book_v3.isEmpty()){
					JP_BangDan_Book book = new JP_BangDan_Book();
					book.setBookId(String.valueOf(book_v3.get("zc_id")));
					book.setBookName(String.valueOf(book_v3.get("book_name")));
					book.setImgUrl(String.valueOf(book_v3.get("img_url")));
					book.setAuthorName(String.valueOf(book_v3.get("author_penname")));
					book.setIntro(String.valueOf(book_v3.get("intro")));
					books.add(book);
				}
				
			}
		}
		return books;
	}
    
    /**
     * 根据榜单ID获取榜单图书
     * @param bdId
     * @return
     */
    public List<JP_BangDan_Book> getJpBangDBooksByBdId(String bdId){
    	String sql = "select * from jp_bangdan_book where bangDanId = ? and startDate<=now() and endDate >=now() order by case when idx is null then 99999 else idx end,idx asc";
    	List<JP_BangDan_Book> books = this.jpBangBookDao.find(sql, bdId);
    	return books;
    }
    
    /**
     * 根据榜单ID删除榜单图书
     * @param bdId
     */
    public void deleteJpBangDBooksByBdId(String bdId){
    	this.jpBangBookDao.delete("jp_bangdan_book", "bangDanId", bdId);
    }
    
    /**
     * 根据ID删除榜单图书
     * @param id
     */
    public void deleteJpBangDBook(String id){
    	this.jpBangBookDao.delete("jp_bangdan_book", "id", id);
    }
    
	public void updateJpBangDBook(JP_BangDan_Book book) {
		String sql = "update `jp_bangdan_book` set bookId=?,bookName=?,imgUrl=?,authorName=?,intro=?,startDate=?,endDate=?,mark=?,markColor=?,idx=?,updateDate=now() where id=?";
		List<Object> args = new ArrayList<Object>();
		args.add(book.getBookId());
		args.add(book.getBookName());
		args.add(book.getImgUrl());
		args.add(book.getAuthorName());
		args.add(book.getIntro());
		args.add(book.getStartDate());
		args.add(book.getEndDate());
		args.add(book.getMark());
		args.add(book.getMarkColor());
		args.add(book.getIdx());
		args.add(book.getId());
		this.jpBangBookDao.update(sql, args.toArray());
	}
	
	/**
	 * 重置缓存
	 */
	public void resetRedisCache() {
		Jedis jedis = RedisUtil.getJedis();
		String redisKey = Constant.redis_Key_jpBangDanBook;
		try{
			JpBangCondition condition = new JpBangCondition();
			condition.setStartRow(0);
			condition.setPageSize(99999);
			List<JP_BangDan> bangDans = jpBangService.findBangByCondition(condition);
			
			Set<String> newKeys = new HashSet<String>();
			for(JP_BangDan bang : bangDans){
				int bangDanId = bang.getId();
				String key = String.format(redisKey, String.valueOf(bangDanId));
				Integer bookFrom = bang.getBookFrom();
				List<JP_BangDan_Book> books = new ArrayList<JP_BangDan_Book>();
				List<JP_BangDan_Book> resultBooks = new ArrayList<JP_BangDan_Book>();
				//书籍来源为人工
				if(bookFrom == 1){
					//获取榜单人工图书
					books = this.getJpBangDBooksByBdId(String.valueOf(bang.getId()));
				}else{
					//获取驱动书籍
					String bookKey = String.format(Constant.redis_Key_phqd, bang.getDriveId());
					List<String> jsons = jedis.lrange(bookKey,0,-1);
					books = this.getBooksByIds(jsons);
				}
				resultBooks.addAll(books);
				//图书去重
				String otherIds = bang.getOtherIds();
				if(StringUtils.isNotBlank(otherIds)){
					for(String otherId : otherIds.split(",")){
						JP_BangDan otherBang = this.jpBangService.getJpBangDById(otherId);
						if(otherBang != null){
							Integer otherBookFrom = otherBang.getBookFrom();
							List<JP_BangDan_Book> otherBooks = new ArrayList<JP_BangDan_Book>();
							//书籍来源为人工
							if(otherBookFrom == 1){
								//获取榜单人工图书
								otherBooks = this.getJpBangDBooksByBdId(String.valueOf(otherBang.getId()));
							}else{
								//获取驱动书籍
								String bookKey = String.format(Constant.redis_Key_phqd, otherBang.getDriveId());
								List<String> jsons = jedis.lrange(bookKey,0,-1);
								otherBooks = this.getBooksByIds(jsons);
							}
							//获取前几本书
							otherBooks = otherBooks.subList(0, otherBooks.size()>otherBang.getBookNum() ? otherBang.getBookNum() : otherBooks.size());
							
							for(JP_BangDan_Book book : books){
								for(JP_BangDan_Book otherBook : otherBooks){
									if(book.getBookId().equals(otherBook.getBookId())){
										resultBooks.remove(book);
									}
								}
							}
						}
					}
				}
				
				jedis.set(key.getBytes(),RedisUtil.serialize(resultBooks));
				newKeys.add(key);
				System.out.println("redis保存的key："+key);
			}

			Set<String> keys = jedis.keys(String.format(redisKey, "*"));
			for(String key : keys){
				if(!newKeys.contains(key)){
					jedis.del(key.getBytes());
					System.out.println("redis删除的bangBook_key："+key);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			RedisUtil.closeJedis(jedis);
		}
	}
	
	
	/**
	 * 自动下架3000,5000,6000,7000,8000,9999渠道图书 hsm
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	public void deleteChannelLibBook(){
		String sql_flyme="SELECT bookId FROM resource_process.book_v3 b,resource_process.channel_lib_book c"
					   +" WHERE b.STATUS > 0 AND b.copyRightTime < NOW() AND c.channelId = 8000 AND b.zc_id=c.bookId";
		List<Map<String,Object>> books =  this.jpBangBookDao.findForList(sql_flyme, new Object[]{});
		for(Map<String,Object> book : books){
			String bookId = (String)book.get("bookId");
			CommonUtil.getHttpData("http://flyme.ikanshu.cn/flyme/noticeUpdate?id="+bookId+"&state=0");
		}
		String sql = "DELETE FROM resource_process.channel_lib_book WHERE channelId IN (3000,5000,6000,7000,8000,9999) AND bookId IN "
				+ " (SELECT zc_id FROM resource_process.book_v3 WHERE STATUS > 0 AND copyRightTime < NOW())";
		this.jpBangBookDao.update(sql);
	}
}
