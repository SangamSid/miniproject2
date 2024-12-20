package com.ashokit.project2.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.project2.entity.Country;
import com.ashokit.project2.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	public User findByEmailAndPwd(String email,String pwd);
}
