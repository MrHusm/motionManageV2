package com.manage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.manage.dao.BookReviewDao;
import com.manage.model.Channel_Book_Review;


@Service("bookReviewService")
public class BookReviewService{
	
    @Resource
    private BookReviewDao bookReviewDao;
    
	public void saveBookReview(Channel_Book_Review bookReview) {
		this.bookReviewDao.insert(bookReview);
	}
}
