package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.user.entities.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	User findByUserName(String username);
	
	@Query(value="select * from user where role like :role", nativeQuery=true)
	List<User> findByRole(String role);
}
