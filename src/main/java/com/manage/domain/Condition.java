package com.manage.domain;

public class Condition {
	private int startRow;
	
	private int pageSize;
	
	private int upPageNum;
	
	private int downPageNum;
	
	private int totalRow;
	
	private int totalPage;
	
	private int dqPage;

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getUpPageNum() {
		return upPageNum;
	}

	public void setUpPageNum(int upPageNum) {
		this.upPageNum = upPageNum;
	}

	public int getDownPageNum() {
		return downPageNum;
	}

	public void setDownPageNum(int downPageNum) {
		this.downPageNum = downPageNum;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getDqPage() {
		return dqPage;
	}

	public void setDqPage(int dqPage) {
		this.dqPage = dqPage;
	}
	
}
