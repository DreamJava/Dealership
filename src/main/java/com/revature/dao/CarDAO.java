package com.revature.dao;

import java.util.List;

import com.revature.org.Car;

//import com.example.model.Car;

public interface CarDAO {
	
	/*
	 * The purpose of the DAO interface
	 * is to provide us with the guideline
	 * for our CRUD operations to be performed
	 * on objects of type Model (Pet)
	 */
	
	//CREATE
	public void insertCar(Car c);
	//READ
	public Car selectCarByVIN(String name);
	public List<Car> selectAllCars();
	//UPDATE
	public void updateCar(Car c);
	//DELETE
	public void deleteCar(Car c);

}
