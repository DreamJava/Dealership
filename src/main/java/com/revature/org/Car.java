package com.revature.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Car implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8237765671428953678L;
	/**
	 * 
	 */
	private String make;
	private String model;
	private String vin;
	private Price msrp;
//	private double id;
	Price chosenOffer;

	HashMap<User, Price> offers;
	
//	public Car(String make, String model) {
//		setName(make, model);
//		offers = new HashMap<User, Price>();
//		id = 100000000 * Math.random();
//		chosenOffer = null;
//	}
	
	public Car(String make, String model, Price msrp) {
		setName(make, model);
		setVIN(vin);
		setMSRP(msrp);
		offers = new HashMap<User, Price>();
		vin = (String) Double.toString(999999999 * Math.random());
		chosenOffer = null;
	}
	
	
	private void setMSRP(Price msrp) {
		this.msrp = msrp;
	}


	public Car(String make, String model, String vin, Price msrp) {
		setName(make, model);
		setVIN(vin);
		setMSRP(msrp);
		offers = new HashMap<User, Price>();
		this.vin = vin;
		chosenOffer = null;
	}
	
	
private void setVIN(String vin) {
		// TODO Auto-generated method stub
		this.vin = vin;
	}

	//	private void setMSRP(Price msrp2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public Price getMSRP() {
//		return msrp;
//	}
	protected void addOffer(User user, Price price) {
		offers.put(user, price);
		//usersMadeOffers.add(user);
	}

	public String getMake() {
		return make;
	}

	public void setName(String make, String model) {
		this.make = make;
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	public String getVin() {
		return vin;
	}


	public Price getMSRP() {
		// TODO Auto-generated method stub
		return msrp;
	}
}
