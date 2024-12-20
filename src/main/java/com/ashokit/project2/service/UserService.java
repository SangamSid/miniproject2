package com.ashokit.project2.service;

import java.util.Map;

import com.ashokit.project2.dto.LoginFormDTO;
import com.ashokit.project2.dto.RegisterFormDTO;
import com.ashokit.project2.dto.ResetPwdDTO;
import com.ashokit.project2.dto.UserDTO;
import com.ashokit.project2.entity.User;

public interface UserService {
	

	public Map<Integer,String> getCountries();

	public Map<Integer,String> getStates(Integer countryId);

	public Map<Integer,String> getCities(Integer stateId);

	public boolean duplicateEmailCheck(String email);

	public boolean saveUser(RegisterFormDTO regFormDTO);

	public UserDTO login(LoginFormDTO loginFormDTO);

	public boolean resetPwd(ResetPwdDTO resetPwdDTO);

	
}
