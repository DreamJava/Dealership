package com.revature.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import com.revature.org.User;
import com.revature.util.DealerSystem;
import com.revature.util.UserType;

public class Employee implements User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3827309952112415402L;
	private UserType user;
	
	public Employee(){
		super();
		user = UserType.EMPLOYEE;
	}

	public void regCustAcct(String input, Scanner in) {
		System.out.println("Action not available for employees.");
		System.out.println();
	}

//	@Override
//	public static User login(Scanner in) {
//		return null;
//	}

	@Override
	public UserType getType() {
		return user;
	}
	
	public void addCar(Scanner in) {
		boolean go = true;
		
		while(go) {
			ArrayList<String> name = new ArrayList<String>();
			System.out.print("Input make: ");
			name.add(in.nextLine());
			System.out.print("Model: ");
			name.add(in.nextLine());
			System.out.println();
			for(Entry<Car, Price> car : DealerSystem.lot.entrySet()) {
				if(car.getKey().getMake().equals(name.get(0)) || car.getKey().getModel().equals(name.get(1))); {
					DealerSystem.lot.remove(car.getKey());
					go = false;
				}
			}
		}

	}

	public static User login(Scanner in) {
		User user = null;
		HashMap<String, User> usernames = DealerSystem.usernames;
		System.out.print("Please login.\nInput your username: ");
		String input = in.nextLine();
		if(input.isEmpty()) System.out.println("Invalid input");
		else if(usernames.containsKey(input)) {
			String username = input;
			boolean loggedIn = false;
			while(!loggedIn) {
				System.out.print("Input your password, " + username + ": ");
				input = in.nextLine();
				//					DealerSystem.active.put(DealerSystem.usernames.get(username), true);
				user = DealerSystem.usernames.get(username);
				loggedIn = true;
			}
		}
		return user;
	}
	
	public static void acceptOffer(Offer offer) {
		System.out.println("Offer accepted.");
		DealerSystem.processOffer(offer);
	}
}
