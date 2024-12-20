package com.ashokit.project2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.project2.dto.LoginFormDTO;
import com.ashokit.project2.dto.QuoteAPIResponseDTO;
import com.ashokit.project2.dto.RegisterFormDTO;
import com.ashokit.project2.dto.ResetPwdDTO;
import com.ashokit.project2.dto.UserDTO;
import com.ashokit.project2.entity.User;
import com.ashokit.project2.mailsender.MailSenderJava;
import com.ashokit.project2.service.DashboardService;
import com.ashokit.project2.service.UserService;
import com.ashokit.project2.service.UserServiceImpl;

@Controller
public class UserController {

	Random random=new Random();
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/logout")
	public String logout( Model model) {
		LoginFormDTO loginFormDTO=new LoginFormDTO();
		model.addAttribute("loginFormDTO",loginFormDTO);
		return "login";
		
	}
	
	
	@GetMapping("/")
	public String getLogin( Model model) {
		
		LoginFormDTO loginFormDTO=new LoginFormDTO();
		
		model.addAttribute("loginFormDTO",loginFormDTO);
		
		return "login";
		
	}
	

	@PostMapping("/")
	public String postLogin(Model model,LoginFormDTO loginFormDTO) {
		
			UserDTO userDto=	userService.login(loginFormDTO);
			if(userDto==null) {
				model.addAttribute("emsg","Invalid Credentials  Have  you reset your password");
			}
			
			else{
				boolean str=userDto.isUpdate_pwd();
						if(str) {
							return "redirect:dashboard";
						}
						else {
							return "redirect:resetPwd?email="+userDto.getEmail();
						}
			    }
			return "login";
			}

	@GetMapping("/register")
	public String getRegister( Model model) {
		
		RegisterFormDTO registerFormDTO=new RegisterFormDTO();
		
	Map<Integer,String> countries=	userService.getCountries();
	
		
		model.addAttribute("countries",countries);
		model.addAttribute("registerFormDTO",registerFormDTO);
		return "register";
		
	}
	
	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer,String>  getStates( @PathVariable Integer countryId) {
	
	Map<Integer,String> states=	userService.getStates(countryId);
		System.out.println(states);
		return states;
		
	}
	
	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer,String>  getCities( @PathVariable Integer stateId, Model model) {
	
	Map<Integer,String> cities=	userService.getCities(stateId);
		
		model.addAttribute("cities",cities);
	
		return cities;
		
	}
	
	

	@PostMapping("/register")
	public String postRegister(Model model,RegisterFormDTO registerFormDTO)  {
		
boolean isEmail=		userService.duplicateEmailCheck(registerFormDTO.getEmail());
if(isEmail) {
	model.addAttribute("emsg","Email already present");
}else {
	boolean saveUser=userService.saveUser(registerFormDTO);
	if(saveUser) {
		model.addAttribute("smsg","User Saved Successfully");
	}else {
		model.addAttribute("emsg","Not registered try again");
	}
}
model.addAttribute("countries",userService.getCountries());
		return "register";
		
	}

		
	
	@GetMapping("/dashboard")
	public String dashboard( Model model) {
		QuoteAPIResponseDTO quoteAPIResponseDTO=dashboardService.getQuote();
	
		model.addAttribute("quoteAPIResponseDTO", quoteAPIResponseDTO);
		return "dashboard";
		
	}

	
	@GetMapping("/resetPwd")
	public String resetPwd(@RequestParam String email, Model model) {
		ResetPwdDTO resetPwdDTO=new ResetPwdDTO();
	resetPwdDTO.setEmail(email);
		
		model.addAttribute("resetPwdDTO", resetPwdDTO);
		return "resetPwd";
		
	}
	
	@PostMapping("/resetPwd")
	public String postResetPwd( Model model,ResetPwdDTO resetPwdDTO) {

	boolean resetPwd=userService.resetPwd(resetPwdDTO);
	if(resetPwd) {
		return "redirect:dashboard";
	}else {
		return "resetPwd";
	}
		
		
	}

}
