package com.manage.model;
import java.util.Date;




public class UserV2 implements java.io.Serializable {
	
	private static final long serialVersionUID = -4427270760065791110L;
	private Long id;
	private String name;
	private String nickName;
	private String password;
	private String tel;
	private String email;
	private String logo;
	private Short sex;
	private Date birthDay;
	private Integer source;
	private Date createDate;
	private Date upadteDate;
	
	private String imsi;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpadteDate() {
		return upadteDate;
	}

	public void setUpadteDate(Date upadteDate) {
		this.upadteDate = upadteDate;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickName=" + nickName
				+ ", password=" + password + ", tel=" + tel + ", email="
				+ email + ", logo=" + logo + ", sex=" + sex + ", birthDay="
				+ birthDay + ", source=" + source + ", createDate="
				+ createDate + ", upadteDate=" + upadteDate + "]";
	}
}