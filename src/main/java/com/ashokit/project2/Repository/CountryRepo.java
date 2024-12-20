package com.ashokit.project2.Repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ashokit.project2.entity.Country;

public interface CountryRepo extends JpaRepository<Country, Integer> {
	
	
	
	
	
}
