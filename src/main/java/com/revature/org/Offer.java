package com.revature.org;

public class Offer extends Price{
	
	public enum Status{
		PENDING, ACCEPTED, REJECTED
	}
	
	Customer cust;
	Car car;
	Status status;

	Offer(Customer cust, Car car, Price price){
		super(price.getAmount());
		this.cust = cust;
		this.car = car;
		status = Status.PENDING;
	}
	
	public void updateStatus(Status status){
		this.status = status;
	}
}
