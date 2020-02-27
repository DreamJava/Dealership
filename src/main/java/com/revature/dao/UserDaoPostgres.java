package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.model.User;
import com.revature.model.UserType;
import com.revature.util.ConnectionFactory;

public class UserDaoPostgres implements UserDao {

	@Override
	public void createUser(User u) {
		
		try (Connection conn = ConnectionFactory.getConnection()) {

			// putting in a native sql query utilizing a prepared statement
			PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES(?,?,?)");

			// we are setting the first question mark to be the make that belongs
			// to our car object
			ps.setString(1, u.getUsername());

			// we are setting the second question mark to be the model that belongs
			// to our car object
			ps.setString(2, u.getPassword());

			// we are setting the third question mark to be the model that belongs
			// to our car object
			ps.setString(3, u.getType().toString());

			// allows us to execute a query without a result
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User u) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "update user_table set password = '" + u.getPassword() + "' where username = '" + u.getUsername() + "'";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {

		User ret = null;
		
		String sql = "Select * from user_table where username ='" + username + "'";
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				ret = new User(UserType.valueOf(rs.getString(1)), rs.getString(2), rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
