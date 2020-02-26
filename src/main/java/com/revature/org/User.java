package com.revature.org;

import java.io.Serializable;
import java.util.Scanner;

import com.revature.util.DealerSystem;
import com.revature.util.UserType;

public interface User extends Serializable{

	public static User login(Scanner in) {
		User user = null;
		boolean loggedIn = false;
		while(!loggedIn) {
			System.out.print("Please login.\nInput your username: ");
			String input = in.nextLine();
			if(input.isEmpty()) System.out.println("Invalid input");
			else if(DealerSystem.usernames.containsKey(input)) {
				String username = input;
				while(!loggedIn) {
					System.out.print("Input your password, " + username + ": ");
					input = in.nextLine();
					if(input.equals(DealerSystem.passwords.get(DealerSystem.usernames.get(username)))) {
//						DealerSystem.active.put(DealerSystem.usernames.get(username), true);
						loggedIn = true;
					}
				}
			}
			System.out.println();
		}
		return user;
	}

	public static User regCustAcct(Scanner in) {
		System.out.print("Please create a username: ");
		String username = in.nextLine();
		System.out.print("Please create a password: ");
		String password = in.nextLine();
		Customer newCustomer = new Customer();
		DealerSystem.usernames.put(username, newCustomer);
//		DealerSystem.s.serialUnames();
		DealerSystem.passwords.put(newCustomer, password);
//		DealerSystem.s.serialPsswds();
		System.out.println();
//		System.out.print("Do you currently own any cars? (1 for yes, 0 for no) ");
//		String input = in.nextLine();
//		if(Integer.parseInt(input) == 1) addOwned(in);
//		DealerSystem.active.put(newCustomer, true);
		return newCustomer;
	}
	
	public UserType getType();
}
