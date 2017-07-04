package com.manage.model;

import java.util.Date;

public class Plug_In {
	public static final String TABLE_NAME="jp_page";
	
	private Integer id;

	private Integer type;//插件类型 1 pdf 2 语音朗读
	
	private String name; //插件名称
    
    private String in_version;//插件内部版本号
    
    private String out_version;//插件外部版本号
    
    private String cpu_type;//cpu类型
    
    private String version;//包含版本
    
    private String url;//插件链接
    
    private String size;//插件大小
    
    private Date createDate;//创建时间
    
    private Date updateDate;//修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIn_version() {
		return in_version;
	}

	public void setIn_version(String in_version) {
		this.in_version = in_version;
	}

	public String getOut_version() {
		return out_version;
	}

	public void setOut_version(String out_version) {
		this.out_version = out_version;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

	public String getCpu_type() {
		return cpu_type;
	}

	public void setCpu_type(String cpu_type) {
		this.cpu_type = cpu_type;
	}
}
