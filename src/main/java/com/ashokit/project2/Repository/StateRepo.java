package com.ashokit.project2.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ashokit.project2.entity.State;

public interface StateRepo extends JpaRepository<State, Integer>{


	@Query(value="select id_country, id_state, state_name from State s where s.id_country=:countryId",nativeQuery=true)
	public List<State> findById_Country(Integer countryId);

}
