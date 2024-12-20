package com.ashokit.project2.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_city;
	private String city_name;
	
	
	private Integer id_state;

	public Integer getId_city() {
		return id_city;
	}

	public void setId_city(Integer id_city) {
		this.id_city = id_city;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public Integer getId_state() {
		return id_state;
	}

	public void setId_state(Integer id_state) {
		this.id_state = id_state;
	}






	
	
}
