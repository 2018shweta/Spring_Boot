package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
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
    	userBean.setUserType("customer");
    	UserBean dbUser=	userDao.getUserByEmail(userBean.getEmail());
    	if(dbUser==null)
    	{
    		userDao.addUser(userBean);
    		return ResponseEntity.ok(userBean);
    		
    	}
    	else
    	{
    		ResponseBean<UserBean> rs=new ResponseBean<>();
    		rs.setData(userBean);
    		rs.setMsg("alrady email avaliable");
    		return new ResponseEntity(rs,HttpStatus.BAD_REQUEST);
    	}
	}
    
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers()
	{
		
		List<UserBean> users =userDao.getAllUsers();
		return ResponseEntity.ok(users);
		
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId)
	
	{
		userDao.deleteUser(userId);
		return ResponseEntity.ok("user delete perfectly");
	}
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(UserBean userBean)
	{
		userDao.updateUser(userBean);
		return ResponseEntity.ok(userBean);
	}
}
