package com.auditoriainterna.serviceInterfaces;


import com.auditoriainterna.dto.UserDto;
import com.auditoriainterna.model.User;


public interface IUserService {
	User save(UserDto userDto);
	
}
