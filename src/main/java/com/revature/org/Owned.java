package com.revature.org;

import java.io.Serializable;
import java.util.HashMap;

import com.revature.util.DealerSystem;

public class Owned extends HashMap<Car, Price> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String location;
	
	public Owned(String location) {
		this.location = location;
	}

	public void viewInv() {
		//DealerSystem.lot = DealerSystem.s.deSerialCars();
		for(Entry<Car, Price>  car: this.entrySet()) 
			System.out.println(car.getKey().getMake() + " " + 
				car.getKey().getModel() + " $" + this.get(car.getKey()).getAmount());
	}

	public String getLocation() {
		return location;
	}

}
