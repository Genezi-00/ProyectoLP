package com.auditoriainterna.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.auditoriainterna.dto.UserDto;
import com.auditoriainterna.serviceInterfaces.IUserService;

@Controller
public class UserController {
	
	@Autowired
	UserDetailsService userdetailsService;
	
	 @Autowired
	 private IUserService userService;
	
	@GetMapping("/registration")
	public String getRegistroPage(@ModelAttribute("user")UserDto userDto) {
		return "registro";
	}
	
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user")UserDto userDto, Model model,Principal principal) {
		userService.save(userDto);
		model.addAttribute("message", "Registro Correcto");
		return "registro";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/inicio-page")
	public String user(Model model, Principal principal) {
		UserDetails userDetails = userdetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "inicio";
	}
	
	@GetMapping("/marketing")
	public String marketing() {
		return "marketing";
	}
}
