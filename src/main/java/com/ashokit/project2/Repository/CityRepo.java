package com.ashokit.project2.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ashokit.project2.entity.City;
import com.ashokit.project2.entity.State;

public interface CityRepo extends JpaRepository<City, Integer> {
	
	@Query(value="select id_state, id_city,city_name from City c where c.id_state=:stateId",nativeQuery=true)
	public List<City> findById_City(Integer stateId);

}
