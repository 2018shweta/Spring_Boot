package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.UserBean;
import com.dao.UserDao;

@RestController
public class SignUpController {
	
	@Autowired
	UserDao userDao;
    @PostMapping("/signup")
	public ResponseEntity<?> saveUser(UserBean userBean)
	{
    	System.out.println(userBean.getFirstName());
    	
    	userDao.addUser(userBean);
    	return ResponseEntity.ok(userBean);
	}
    
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers()
	{
		
		List<UserBean> users =userDao.getAllUsers();
		return ResponseEntity.ok(users);
		
	}
	
	
	
	
	
}
