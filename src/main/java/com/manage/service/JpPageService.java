package com.manage.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.manage.constant.Constant;
import com.manage.dao.JpPageDao;
import com.manage.domain.JpPageCondition;
import com.manage.model.JP_BangDan;
import com.manage.model.JP_BangDan_Book;
import com.manage.model.JP_BangDan_Label;
import com.manage.model.JP_Banner_Position;
import com.manage.model.JP_Page;
import com.manage.util.RedisUtil;

@Service("jpPageService")
public class JpPageService{

    @Resource
    private JpPageDao jpPageDao;
    
	@Resource
	private JpBannerPositionService jpBannerPositionService;
	
	@Resource
	private JpBangService jpBangService;
	
	@Resource
	private JpBangBookService jpBangBookService;
	
	@Resource
	private JpBangLabelService jpBangLabelService;
	
    /**
     * 根据条件查询精品页列表
     * @param condition
     * @return
     */
	public List<JP_Page> findBindByCondition(JpPageCondition condition) {
		String pageType = condition.getPageType();
		String source = condition.getSource();
		String orderKey = condition.getOrderKey();
		StringBuffer sql = new StringBuffer("select * from resource_process.jp_page where 1 = 1 ");
		
		List<Object> args = new ArrayList<Object>();
		if(StringUtils.isNotBlank(pageType)){
			sql.append(" and pageType = ? ");
			args.add(pageType);
		}
		if(StringUtils.isNotBlank(source)){
			sql.append(" and source = ? ");
			args.add(source);
		}
		if(StringUtils.isNotBlank(orderKey)){
			sql.append("order by case when "+ orderKey + " is null then 99999 else idx end,idx asc");
		}
		List<JP_Page> list = this.jpPageDao.find(sql.toString(), args.toArray());
		return list;
	}
	/**
	 * 保存精品页
	 * @param page
	 */
	public void saveJpPage(JP_Page page) {
		this.jpPageDao.insert(page);
	}

	/**
	 * 批量保存精品页
	 * @param page
	 */
	public void saveJpPageList(List<JP_Page> pages) {
		for(JP_Page page : pages){
			this.jpPageDao.insert(page);
		}
	}
	/**
	 * 修改精品页
	 * @param bind
	 */
	public void updateJpPage(JP_Page page) {
		StringBuffer sql = new StringBuffer("update `jp_page` set ") ;
		List<Object> args = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(page.getName())){
			sql.append(" name = ?,");
			args.add(page.getName());
		}
		
		if(page.getKeyId()!=null){
			sql.append(" keyId = ?,");
			args.add(page.getKeyId());
		}
		
		if(page.getType() != null){
			sql.append(" type = ?,");
			args.add(page.getType());
		}
		
		if(page.getUpdateDate() != null ){
			sql.append(" updateDate = ?,");
			args.add(page.getUpdateDate());
		}

		if(page.getIdx() != null){
			sql.append(" idx = ?,");
			args.add(page.getIdx());
		}
		
		String tempSql = sql.substring(0, sql.length() - 1);
		if(page.getId() != null){
			tempSql +=" where id = ?";
			args.add(page.getId());
			this.jpPageDao.update(tempSql, args.toArray());
		}
	}

	/**
	 * 
	 * 删除精品页
	 * @param id
	 */
	public void deleteJpPage(String id) {
		if(!DataUtil.isEmpty(id)){
			this.jpPageDao.delete("jp_page", "id", id);
		}
	}

	/**
	 * 根据ID获取精品页
	 * @param id
	 */
	public JP_Page getJpPageById(String id) {
		String sql = "select * from jp_page where id = ?";
		return this.jpPageDao.findOne(sql, id);
	}
	
	/**
	 * 重置缓存
	 */
	public void resetRedisCache() {
		Jedis jedis = RedisUtil.getJedis();
		String redisKey = Constant.redis_Key_jpPage;
		String redisKeyBook = Constant.redis_Key_jpBangDanBook;
		try{
			JpPageCondition condition = new JpPageCondition();
			for(int i = 1; i<=4; i++){
				condition.setPageType(String.valueOf(i));
				condition.setOrderKey("idx");
				List<JP_Page> list = findBindByCondition(condition);
				for(JP_Page page : list){
					Integer type = page.getType();
					Integer keyId = page.getKeyId();
					if(type == 1){
						//获取榜单
						JP_BangDan bang = this.jpBangService.getJpBangDById(String.valueOf(keyId));
						if(bang != null){
							List<JP_BangDan_Book> books = new ArrayList<JP_BangDan_Book>();
							Object obj = RedisUtil.deserialize(jedis.get(String.format(redisKeyBook, String.valueOf(bang.getId())).getBytes()));
							if(obj != null){
								books = (List<JP_BangDan_Book>)obj;
							}
							bang.setBooks(books);
							//获取榜单标签
							List<JP_BangDan_Label> labels = this.jpBangLabelService.getJpBangDLabelsByBdId(String.valueOf(bang.getId()));
							bang.setLabels(labels);
							page.setBang(bang);
						}
					}else if(type == 2){
						//获取广告位
						JP_Banner_Position position = this.jpBannerPositionService.getBannerPositionById(String.valueOf(keyId));
						page.setPosition(position);
					}
				}
				jedis.set(String.format(redisKey, i).getBytes(),RedisUtil.serialize(list));
				System.out.println(String.format(redisKey, i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			RedisUtil.closeJedis(jedis);
		}
	}
	
	public static List<String> readData(String path){
		List<String> list = new ArrayList<String>();
    	File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	String s = tempString.trim();
            	list.add(s);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
	}
	
	public void done(){
		List<String> list = readData("/data/www/111.txt");
		String result = "";
		for (String data : list) {
			result += data + ",";
		}
		try {
			String sql="SELECT b.zc_id as bookId,b.book_name AS bookName,CASE WHEN (SELECT newIntroduction FROM channel_lib_book_info i WHERE i.channelId = 3000 AND i.bookId=b.zc_id ) IS NULL THEN"
					+" (SELECT intro FROM channel_lib_book c WHERE c.channelId = 3000 AND c.bookId=b.zc_id ) "
					+" ELSE (SELECT newIntroduction FROM channel_lib_book_info i WHERE i.channelId = 3000 AND i.bookId=b.zc_id ) END"
					+" AS newIntroduction  FROM book_v3 b WHERE b.zc_id in ("+result.substring(0, result.length()-1)+")";
			List<Map<String,Object>> data = this.jpPageDao.findForList(sql, null);
			for(Map<String,Object> s : data){
				File file = new File("/data/www/hsm/"+s.get("bookId")+".txt");
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				fw.write(s.get("newIntroduction")+"");
				fw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}