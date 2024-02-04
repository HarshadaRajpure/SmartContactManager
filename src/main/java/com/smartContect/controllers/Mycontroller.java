package com.smartContect.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smartContect.dao.ContactRepository;
import com.smartContect.dao.UserRepo;
import com.smartContect.entities.Contact;
import com.smartContect.entities.User;

@Controller
public class Mycontroller {
			
//		@Autowired
//		private UserRepo userRepo;
//		
//		@GetMapping("/test")
//		@ResponseBody
//		public String test() {
//			
//			User userClass = new User();
//			userClass.setEmail("qweqweqwe");
//			userClass.setName("gautam");
//			
//			userRepo.save(userClass);
//			return "working";
//		}

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping(value = "/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query ,Principal principal){
		System.out.println(query);
		String name = principal.getName();
		User user = userRepo.getUserByUserName(name);
		List<Contact> contacts = this.contactRepository.findByNameContainingAndUser(query, user);
		return ResponseEntity.ok(contacts);
	}
}
