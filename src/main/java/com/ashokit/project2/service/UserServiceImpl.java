package com.ashokit.project2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ashokit.project2.Repository.CityRepo;
import com.ashokit.project2.Repository.CountryRepo;
import com.ashokit.project2.Repository.StateRepo;
import com.ashokit.project2.Repository.UserRepo;
import com.ashokit.project2.dto.LoginFormDTO;
import com.ashokit.project2.dto.RegisterFormDTO;
import com.ashokit.project2.dto.ResetPwdDTO;
import com.ashokit.project2.dto.UserDTO;
import com.ashokit.project2.entity.City;
import com.ashokit.project2.entity.Country;
import com.ashokit.project2.entity.State;
import com.ashokit.project2.entity.User;
import com.ashokit.project2.mailsender.MailSenderJava;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	public MailSenderJava mailSenderJava;
	
	@Override
	public Map<Integer, String> getCountries() {
		
	List<Country>countries=	(List<Country>) countryRepo.findAll();
	
	Map<Integer, String> countriesMap=new HashMap<Integer,String>();
	countries.forEach(c->{
		countriesMap.put(c.getId_country(),c.getCountry_name());
	});
	
	
		return countriesMap;
	
	}
	@Override
	public Map<Integer, String> getStates(Integer countryId) {
	List<State> states=stateRepo.findById_Country(countryId);

	
	Map<Integer, String> statesMap=new HashMap<Integer,String>();

	states.forEach(s->{
		statesMap.put(s.getId_state(),s.getState_name());
	});
		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<City> cities=cityRepo.findById_City(stateId);
		Map<Integer, String> cityMap=new HashMap<Integer,String>();

		cities.forEach(c->{
			cityMap.put(c.getId_city(),c.getCity_name());
		});
			return cityMap;
	}

	@Override
	public boolean duplicateEmailCheck(String email)  {
	
	User user=	userRepo.findByEmail(email);
			if(user!=null) {
				return true;
			}else {
				return false;
			}
	}
	@Override
	public boolean saveUser(RegisterFormDTO regFormDTO) {

	String gpwd=generateRandomPwd();
		User user=new User();
		BeanUtils.copyProperties(regFormDTO, user);
		
	Country country=	countryRepo.findById(regFormDTO.getId_country()).orElse(null);
	user.setCountry(country);
	
	State state=	stateRepo.findById(regFormDTO.getId_state()).orElse(null);
	user.setState(state);

		City city=	cityRepo.findById(regFormDTO.getId_city()).orElse(null);
		user.setCity(city);	
		user.setPwd(gpwd);
		user.setUpdate_pwd(false);
		
	User savedUser=	userRepo.save(user);
	if(savedUser.getId()!=null) {
		
		mailSenderJava.sendEmail(regFormDTO.getEmail(), " Random Password of User please rest it", "Your password forlogin is  "+gpwd);
		return true;
	}else {
		return false;
	}
		
		
		
	}
	@Override
	public UserDTO login(LoginFormDTO loginFormDTO) {
	User user=	userRepo.findByEmailAndPwd(loginFormDTO.getEmail(), loginFormDTO.getPwd());
	if(user!=null) {
		UserDTO userDto=new UserDTO();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}else {
		return null;
	}
		
	}
	@Override
	public boolean resetPwd(ResetPwdDTO resetPwdDTO) {

	String email=	resetPwdDTO.getEmail();
User user=	userRepo.findByEmail(email);
user.setPwd(resetPwdDTO.getNewPwd());
user.setUpdate_pwd(true);

userRepo.save(user);

		return true;
	}

	
	
	private String generateRandomPwd() {
		Random random =new Random();
		String v1="abcdefghijklmnopqrstuvwxz";
		String v2="ABCDEFGHIJKLMNPQRSTUVWXYZ";
		String alpha=v1+v2;
		StringBuffer generatePwd=new StringBuffer();
		
		for(int i=0;i<5;i++) {
		int nextInt=	random.nextInt(alpha.length());
		generatePwd.append(alpha.charAt(nextInt));
		
		}
		
		
		return generatePwd.toString();
		
	}
	

    
}
