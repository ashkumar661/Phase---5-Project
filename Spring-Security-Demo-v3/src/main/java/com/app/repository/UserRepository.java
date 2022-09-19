package com.app.repository;

import com.app.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select user from User user where user.email=?1")
	public User findUserByEmailId(String emailId);
	
}
