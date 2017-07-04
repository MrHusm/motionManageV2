package com.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jmind.core.util.DataUtil;

import org.springframework.stereotype.Service;

import com.manage.dao.Book_V3Dao;
import com.manage.domain.BookCondition;
import com.manage.model.Book_V3;

@Service("bookService")
public class BookService {

	@Resource
	private Book_V3Dao book_V3Dao;
	
	/**
     * 根据条件查询书籍列表
     * @param condition
     * @return
     */
	public List<Book_V3> findBindByCondition(BookCondition condition) {
		int start = condition.getStartRow();
		int limit = condition.getPageSize();
		//String phb_name = condition.getPhb_name();
		String ids = condition.getIds();
		int id= condition.getId();
		StringBuffer sql = new StringBuffer("select * from book_v3 where 1 = 1 ");
		if(!DataUtil.isEmpty(ids)){
			sql.append(" and zc_id in ("+ ids + ") ");
		}
		List<Object> args = new ArrayList<Object>();
		if(id!=0){
			sql.append(" and zc_id = ? ");
			args.add(id);
		}
		
		
		sql.append(" order by id desc limit ?,?");
		args.add(start);
		args.add(limit);
		
		List<Book_V3> list = this.book_V3Dao.find(sql.toString(), args.toArray());
		return list;
	}
	
	/**
	 * 根据条件查询书籍数量
	 * @param condition
	 * @return
	 */
	public int findCountByCondition(BookCondition condition){
		String ids = condition.getIds();
		StringBuffer sql = new StringBuffer("select count(*) from book_v3 where 1 = 1 ");
		int id= condition.getId();
		if(!DataUtil.isEmpty(ids)){
			sql.append(" and zc_id in ("+ ids + ") ");
		}else{
			return 0;
		}
		List<Object> args = new ArrayList<Object>();
		if(id!=0){
			sql.append(" and zc_id = ? ");
			args.add(id);
		}
		
		return this.book_V3Dao.findForInt(sql.toString(), args.toArray());
	}
}
