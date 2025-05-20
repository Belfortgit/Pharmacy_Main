package com.user.service;

import java.util.List;

import com.user.entities.User;
import com.user.exception.InvalidRoleException;
import com.user.exception.InvalidUserIdException;

public interface UserService {
	User add(User user) throws InvalidRoleException;
	List<User> view();
	User viewById(int userid) throws InvalidUserIdException;
	List<User> viewByRole(String role) throws InvalidRoleException;
	String delete(int userid) throws InvalidUserIdException;
	User findByUserName(String username);
	
	public String verify(User user);
}

