package com.revature.org;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.util.*;

import de.kyrychenko.utils.vin.VinGeneratorUtils;

public class Customer implements User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6154641176320787116L;

	List<Car> owned;
	
	private UserType type;
	
	protected Customer() {
		super();
		owned = new ArrayList<Car>();
		type = UserType.CUSTOMER;
	}

//	public static boolean login(Scanner in) {
//		DealerSystem.login(UserType.CUSTOMER);
//		return true;
//	}
	public void addOwned(Scanner in) {
		boolean more = true;
		String input = null;
		do {
				System.out.println("-Add owned-");
				System.out.print("Please input make: ");
				String make = in.nextLine();
				System.out.print("Please input model: ");
				String model = in.nextLine();
				owned.add(new Car(make, model));
				System.out.println("Car added.");
				System.out.println();
				System.out.print("More to odd? (1 for yes, press enter for no");
				input = in.nextLine();
				if(input.matches("")) more = false;
		} while(more);
	}

	public void makeOffer(Scanner in) {
		System.out.println("You are now creating your offer. ");
		Car offerCar = null;
		while(offerCar == null) {
			System.out.print("Input the make: ");
			String make = in.nextLine();
			System.out.print("Model: ");
			String model = in.nextLine();
			offerCar = new Car(make, model);
			if(DealerSystem.lot.containsKey(offerCar)){
				System.out.println("How much is your offer for this car, with an msrp of " + DealerSystem.lot.get(offerCar) + "?");
			}
		}
		System.out.print("Input the offer amount($USD): $");
		Price priceOffered = new Price(Integer.parseInt(in.nextLine()));
		Offer offer = new Offer(this, offerCar, priceOffered);
		DealerSystem.offers.add(offer);
	}

	public static User login(Scanner in) {
		User user = null;
		System.out.print("Please login.\nInput your username: ");
		String input = in.nextLine();
		boolean loggedIn = false;
		while(!loggedIn) {
			if(input.isEmpty()) System.out.println("Invalid input");
			else if(DealerSystem.usernames.containsKey(input)) {
				String username = input;
				while(!loggedIn) {
					System.out.print("Input your password, " + username + ": ");
					input = in.nextLine();
					if(input.equals(DealerSystem.passwords.get(DealerSystem.usernames.get(username)))) {
//						DealerSystem.active.put(DealerSystem.usernames.get(username), true);
						user = DealerSystem.usernames.get(username);
						loggedIn = true;
						break;
					}
				}
			} else System.out.println("Username does not exist.");
			break;
			
		}
		return user;
	}

	@Override
	public UserType getType() {
		return type;
	}

	public void viewOwned() {
		if(owned.isEmpty()) System.out.println("You do not own any cars, you must buy one from us first! ;)");
		for(Car car: owned) {
			System.out.println(car.getMake() + " " + car.getModel());
		}
	}

	public void viewPmtsRmng() {
		System.out.println("View remaining payments.");
	}
	
}
