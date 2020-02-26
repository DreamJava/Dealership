package com.revature.util;

import java.util.HashMap;

import com.revature.org.Car;
import com.revature.org.Price;

public class Lot extends HashMap<Car, Price>{

	public void viewInv() {
		for(Entry<Car, Price> car : DealerSystem.lot.entrySet()) System.out.println(car.getKey().getMake() + " " 
				+ car.getKey().getModel() + " $" + car.getValue().getAmount());
	}

}
