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
@Table(name="state")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_state;
	private String state_name;
	
	
	private Integer id_country;
	
	
	public Integer getId_state() {
		return id_state;
	}
	public void setId_state(Integer id_state) {
		this.id_state = id_state;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public Integer getId_country() {
		return id_country;
	}
	public void setId_country(Integer id_country) {
		this.id_country = id_country;
	}




	
	



	
	
	
	
}
