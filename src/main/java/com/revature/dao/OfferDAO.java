package com.revature.dao;

import java.util.List;

import com.revature.org.Car;
import com.revature.org.Offer;

public interface OfferDAO {
	
	/*
	 * The purpose of the DAO interface
	 * is to provide us with the guideline
	 * for our CRUD operations to be performed
	 * on objects of type Model (Pet)
	 */
	
	//CREATE
	public void insertOffer(Offer o);
	//READ
	public List<Offer> selectAllOffers();
	//UPDATE
	public void updateOffer(Offer o);
	//DELETE
	public void deleteOffer(Offer o);

}
