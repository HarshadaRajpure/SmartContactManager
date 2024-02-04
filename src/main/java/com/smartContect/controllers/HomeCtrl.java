package com.smartContect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartContect.dao.UserRepo;
import com.smartContect.entities.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeCtrl {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepo;
	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("title", "Home - Smart contect manager");
		return "home";
	}

	@GetMapping("/about")
	public String about(Model m) {
		m.addAttribute("title", "About - Smart contect manager");
		return "about";
	}

	@GetMapping("/Signup")
	public String Signup(Model m) {
		m.addAttribute("title", "Signup - Smart contect manager");
		m.addAttribute("user", new User());
		return "signup";
	}

//	this heandler for get user resisteration form data

	@PostMapping("/do_register")
	public String Register(@Valid @ModelAttribute("user") User user,BindingResult result,Model m) {
	    try {
	    	
	    	if(result.hasErrors()) {
				m.addAttribute("user", user);
				System.out.println(result);
				return "signup"; 
			}else {
				 	user.setRole("Role_User");
		            user.setEnabled(true);
		            user.setImgUrl("gautam.jpeg");
		            
		            
		            user.setPassword(passwordEncoder.encode(user.getPassword()));
		            User savedUser = this.userRepo.save(user);
		            
		            
		     System.out.println(user);
		            
		            
//		            User savedUser = this.userRepo.save(user);
		            System.out.println(" 2 : "+user);
		            return "RegistrationDone";
					}
	           
	    } catch (Exception e) {
	        e.printStackTrace();
	        m.addAttribute("user", user);
	        if(!(userRepo.findByEmail(user.getEmail())==null)) {
	        	m.addAttribute("error","Email exist plese use a diffrent email address");
	        }
	        return "signup";
	    }
	    
		
		
		
	}

	//handler for custom Login
		@GetMapping("/signin")
		public String customLogin(Model model) {
			model.addAttribute("title", "Login -Smart Contact Manager");
			return "login";
		}

}
