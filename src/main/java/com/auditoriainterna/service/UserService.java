package com.auditoriainterna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auditoriainterna.dto.UserDto;
import com.auditoriainterna.model.User;
import com.auditoriainterna.repositories.UserRepository;
import com.auditoriainterna.serviceInterfaces.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),userDto.getRole(),userDto.getFullname());
		return userRepository.save(user);
	}

}
