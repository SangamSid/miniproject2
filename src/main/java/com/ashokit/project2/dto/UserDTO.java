package com.ashokit.project2.dto;


import com.ashokit.project2.entity.City;
import com.ashokit.project2.entity.Country;
import com.ashokit.project2.entity.State;

public class UserDTO {
	
	private Integer id;
	private String name;
	private String email;
	private Long phno;
	
	private Integer id_country;
	
	private Integer id_state;

	private Integer id_city;
	
	private String pwd;
	private boolean update_pwd;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhno() {
		return phno;
	}
	public void setPhno(Long phno) {
		this.phno = phno;
	}
	
	public Integer getId_country() {
		return id_country;
	}
	public void setId_country(Integer id_country) {
		this.id_country = id_country;
	}
	public Integer getId_state() {
		return id_state;
	}
	public void setId_state(Integer id_state) {
		this.id_state = id_state;
	}
	public Integer getId_city() {
		return id_city;
	}
	public void setId_city(Integer id_city) {
		this.id_city = id_city;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isUpdate_pwd() {
		return update_pwd;
	}
	public void setUpdate_pwd(boolean update_pwd) {
		this.update_pwd = update_pwd;
	}

	


}
