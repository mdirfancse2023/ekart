package com.ekart.dao;

import java.sql.Statement;

import com.ekart.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	public User userLogin(String email,String password) {
		User user = null;
		try {
			query = "select * from ekartusers where email=? and password=?";
			ps = this.con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
