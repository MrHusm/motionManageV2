package com.manage.model;

import java.util.Date;

public class Channel_Book_Review {
	public int id;
	
	public int channelId;
	
	public int userId;
	
	public String bookId;
	
	public int reviewId;//评论id
	
	public int reviewUserId;//评论人id
	
	public String content;//内容
	
	public int type;//0 默认 1加精 2置顶
	
	public int audit;//0 默认  1通过 2不通过
	
	public String title;//标题
	
	public int levelReviewId;//评论的层级id
	
	public int reviewStatus;//0 回复评论未读 1回复评论已读
	
	public Date createDate;
	
	public Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getReviewUserId() {
		return reviewUserId;
	}

	public void setReviewUserId(int reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAudit() {
		return audit;
	}

	public void setAudit(int audit) {
		this.audit = audit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLevelReviewId() {
		return levelReviewId;
	}

	public void setLevelReviewId(int levelReviewId) {
		this.levelReviewId = levelReviewId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(int reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
}
