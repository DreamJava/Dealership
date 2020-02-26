package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.org.Car;
import com.revature.util.ConnectionFactory;
//import com.example.model.Pet;
//import com.revature.util.ConnectionFactory;

public class CarDAOImpl implements CarDAO {


	// There should be system variables once connected to AWS:RDS
	// Note: STS will not have access to new environment variables until
	// you restart it
	// jdbc:postgresql://host:port/database_name
	

	@Override
	public void insertCar(Car c) {
		
		try (Connection conn = ConnectionFactory.getConnection()) {

			// putting in a native sql query utilizing a prepared statement
			PreparedStatement ps = conn.prepareStatement("INSERT INTO proc_table VALUES(?,?)");
			ps.setString(1, c.getMake());
			// we are setting the first question mark to be the make that belongs
			// to our car object
			ps.setString(2, c.getModel());
			// we are setting the second question mark to be the model that belongs
			// to our car object
			ps.setInt(2, c.getVin());
			// we are setting the second question mark to be the model that belongs
			// to our car object
			ps.execute();
			// allows us to execute a query without a result

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Car selectCarByVIN(String vin) {
		Car c = null;
		try (Connection conn = ConnectionFactory.getConnection()) {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pets WHERE name=?");
			ps.setString(1, vin);
			
			ResultSet rs = ps.executeQuery();
			//we are executing the query and storing the result set in 
			//a ResultSet type (object)
			while(rs.next()) {
				c = new Car(rs.getString("make"), rs.getString("model"), rs.getInt("vin"));
				//we are iterating through our result set and populating our
				//pet object with the values that are coming from the
				//table's columns
				//"name" and "type" on line 67 are column names
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Car> selectAllCars() {
		List<Car> pets = new ArrayList<Car>();
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cars");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pets.add(new Car(rs.getString(1), rs.getString(2), rs.getInt(3)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pets;
	}

	@Override
	public void updateCar(Car c) {
		try (Connection conn = ConnectionFactory.getConnection()) {

		PreparedStatement ps = conn.prepareStatement("UPDATE Cars SET type=? WHERE name=?");
		ps.setString(1, c.getMake());
		ps.setString(2, c.getModel());
		ps.setInt(3, c.getVin());
		ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCar(Car c) {
		try (Connection conn = ConnectionFactory.getConnection()) {

			PreparedStatement ps = conn.prepareStatement("DELETE FROM Cars WHERE vin=?");
			ps.setInt(3, c.getVin());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


//	@Override
//	public void insertPet(Car c) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Car selectCarByVIN(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Car> selectAllCars() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateCar(Car c) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteCar(Car c) {
//		// TODO Auto-generated method stub
//
//	}

}
