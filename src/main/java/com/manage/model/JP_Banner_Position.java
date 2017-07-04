package com.manage.model;

import java.util.Date;
import java.util.List;


public class JP_Banner_Position implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME="jp_banner_position";
	
	private Integer id;
	
	private String name;//广告位名称
	
	private Integer type;//广告类型 1 大图 2 小图 3 文字
	
	private Integer source;//0 中文书城 1 创新版
    
    private Date createDate;//创建时间
    
    private Date updateDate;//修改时间
    
    private List<JP_Banner> banners;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public List<JP_Banner> getBanners() {
		return banners;
	}

	public void setBanners(List<JP_Banner> banners) {
		this.banners = banners;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
}
