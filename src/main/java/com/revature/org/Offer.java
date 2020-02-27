package com.revature.org;

public class Offer extends Price{
	
	public enum Status{
		PENDING, ACCEPTED, REJECTED
	}
	
	Customer cust;
	private Car car;
	Status status;

	Offer(Customer cust, Car car, Price price){
		super(price.getAmount());
		this.cust = cust;
		this.setCar(car);
		status = Status.PENDING;
	}
	
	public Offer(String make, String model, Price price) {
		super(price.getAmount());
	}

	public void updateStatus(Status status){
		this.status = status;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Price getPrice() {
		return this;
	}
}
