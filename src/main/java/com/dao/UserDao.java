package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {
    @Autowired
	JdbcTemplate stmt;
	
	public void addUser(UserBean userBean)
	{
		stmt.update("insert into users (firstname,lastname,gender,email,password,usertype) values (?,?,?,?,?,?)",userBean.getFirstName(),userBean.getLastName(),userBean.getGender(),userBean.getEmail(),userBean.getPassword(),userBean.getUserType());
	}
	
	public List<UserBean> getAllUsers(){
		return stmt.query("select * from users", new BeanPropertyRowMapper<UserBean>(UserBean.class));
	}

	public void deleteUser(int userId) {
		 stmt.update("delete from users where userId=?",userId);
		
	}

	public void updateUser(UserBean userBean) {
		stmt.update("update users set firstname=?,lastname=? where userid=?",userBean.getFirstName(),userBean.getLastName(),userBean.getUserId());
		
	}

	public UserBean getUserByEmail(String email) {
		List<UserBean> users=null;
		try {
		   users=stmt.query("select * from users where email=?",new BeanPropertyRowMapper<UserBean>(UserBean.class),new Object[] {email});	
		}catch(Exception e)
		{
			System.out.println("user not availabel");
		}
	
		if(users.size()==0)
		{
			return null;
		}
		else {
			return users.get(0);
		}
	}
	
}
