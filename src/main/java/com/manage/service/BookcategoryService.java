package com.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.manage.dao.BookcategoryDao;
import com.manage.model.Bookcategory_V3;

@Service("bookcategoryService")
public class BookcategoryService {

	@Resource
	private BookcategoryDao bookcategoryDao;
	
	
	/**
	 * 查询图书分类/作者分类信息 二级 
	 * @param condition
	 * @return
	 */
	public List<Bookcategory_V3> findBookCategorysTwo(int id){
		
		StringBuffer sql = new StringBuffer("SELECT * FROM bookcategory_v3 WHERE id LIKE '%00'  ");
		
		
		if(id==1){
			sql.append(" and id <2000");
		}else if(id==2){
			sql.append(" and id >2000 and id<3000");
		}else if(id==3){
			sql.append(" and id >3000 and id<4000");
		}
		
		
		List<Bookcategory_V3> list = this.bookcategoryDao.find(sql.toString());
		return list;
	}
	
	/**
	 * 查询图书分类/作者分类信息 
	 * @param condition
	 * @return
	 */
	public List<Bookcategory_V3> findBookCategorys1(){
		
		StringBuffer sql = new StringBuffer("SELECT * FROM bookcategory_v3  ");
		
		
		List<Bookcategory_V3> list = this.bookcategoryDao.find(sql.toString());
		return list;
	}
	
}
