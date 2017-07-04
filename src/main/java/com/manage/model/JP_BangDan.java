package com.manage.model;

import java.util.Date;
import java.util.List;


public class JP_BangDan implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="jp_bangdan";
	
	private Integer id;
	
	private String name;//榜单名称
	
	private Integer bookNum;//显示书籍数量
	
	private Integer showNameFlag;//榜单名称是否显示 0 不显示 1显示
	
	private Integer freshFlag;//刷新可变 0 不可变 1可变
	
	private Integer downFlag;//是否一键下载 0 不可下载 1可下载
	
	private Integer downNum;//下载章节数
	
	private Integer moreFlag;//是否有更多 0 无 1加载当前 2跳转 3换一批
	
	private String moreUrl;//更多链接
	
	private String moreContent;//更多文案
	
	private Integer bookFrom;//书籍来源 1 人工 2 驱动
	
	private Integer driveId;//驱动ID
	
	private String driveName;//驱动名称
	
	private Integer bangStyle;//榜单样式 1竖版 2横版 3 图带文字 4 纯文字
	
	private String remark;//备注
	
	private Integer source;//0 中文书城  1 创新版
	
	private String otherIds;//排重榜单ID
	
    private Date createDate;//创建时间
    
    private Date updateDate;//修改时间
    
    private List<JP_BangDan_Book> books;
    
    private List<JP_BangDan_Label> labels;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBookNum() {
		return bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	public Integer getShowNameFlag() {
		return showNameFlag;
	}

	public void setShowNameFlag(Integer showNameFlag) {
		this.showNameFlag = showNameFlag;
	}

	public Integer getFreshFlag() {
		return freshFlag;
	}

	public void setFreshFlag(Integer freshFlag) {
		this.freshFlag = freshFlag;
	}

	public Integer getDownFlag() {
		return downFlag;
	}

	public void setDownFlag(Integer downFlag) {
		this.downFlag = downFlag;
	}

	public Integer getDownNum() {
		return downNum;
	}

	public void setDownNum(Integer downNum) {
		this.downNum = downNum;
	}

	public Integer getMoreFlag() {
		return moreFlag;
	}

	public void setMoreFlag(Integer moreFlag) {
		this.moreFlag = moreFlag;
	}

	public String getMoreUrl() {
		return moreUrl;
	}

	public void setMoreUrl(String moreUrl) {
		this.moreUrl = moreUrl;
	}

	public String getMoreContent() {
		return moreContent;
	}

	public void setMoreContent(String moreContent) {
		this.moreContent = moreContent;
	}

	public Integer getBookFrom() {
		return bookFrom;
	}

	public void setBookFrom(Integer bookFrom) {
		this.bookFrom = bookFrom;
	}

	public Integer getDriveId() {
		return driveId;
	}

	public void setDriveId(Integer driveId) {
		this.driveId = driveId;
	}

	public Integer getBangStyle() {
		return bangStyle;
	}

	public void setBangStyle(Integer bangStyle) {
		this.bangStyle = bangStyle;
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

	public List<JP_BangDan_Book> getBooks() {
		return books;
	}

	public void setBooks(List<JP_BangDan_Book> books) {
		this.books = books;
	}

	public List<JP_BangDan_Label> getLabels() {
		return labels;
	}

	public void setLabels(List<JP_BangDan_Label> labels) {
		this.labels = labels;
	}

	public String getDriveName() {
		return driveName;
	}

	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getOtherIds() {
		return otherIds;
	}

	public void setOtherIds(String otherIds) {
		this.otherIds = otherIds;
	}
}
