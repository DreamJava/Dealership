package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.CarDAOImpl;
import com.revature.dao.OfferDAOImpl;
import com.revature.dao.SerializationDAO;
import com.revature.dao.UserDaoPostgres;
import com.revature.driver.DealerDriver;
import com.revature.org.Car;
import com.revature.org.Customer;
import com.revature.org.Employee;
import com.revature.org.Offer;
import com.revature.org.Price;
//import com.revature.util.*;
//import com.revature.pojo.Book;
import com.revature.org.User;

import de.kyrychenko.utils.vin.VinGeneratorUtils;

public class DealerSystem implements Serializable {
	
	/**
	 * 
	 */
	final int CUSTOMER = 1;
	final int EMPLOYEE = 2;
	final int EXIT = 0;

	CarDAOImpl cdi;
	private static final long serialVersionUID = -6973336632711654326L;
	public static Logger log;
	public static Scanner in;
	static boolean go;
	public static Lot lot;
	static String input;
	public static HashMap<String, User> usernames;
	public static HashMap<User, String> passwords;
	//public static HashMap<User, Boolean> active;
	public static ArrayList<Offer> offers;
	public static SerializationDAO s;
	public User employee;
	private OfferDAOImpl odi;
	private UserDaoPostgres udi;
	
	public DealerSystem() {
		cdi = new CarDAOImpl();
		odi = new OfferDAOImpl();
		udi = new UserDaoPostgres();
		
		s = new SerializationDAO();
		log = Logger.getLogger(DealerSystem.class);
		in = new Scanner(System.in);
		go = true;
		input = null;
		lot = new Lot();
		if(usernames == null) usernames = new HashMap<String, User>();
		else {
			s.serialUnames();
			addUser(new Employee(), "TheBest", "password"); // User type, username, password
		}
		if(passwords == null) passwords = new HashMap<User, String>();
		else s.serialPsswds();
//		if(active == null) active = new HashMap<User, Boolean>();
//		else s.serialActive();
		if(offers == null) offers = new ArrayList<Offer>();
//		else s.serialOffers();
//		log.info("Employee created with username \"TheBest\" and password \"password\"!");
		go();
	}
	
	private void addUser(User user, String username, String password) {
		if(usernames.containsKey(username)) {
			usernames.put(username, user);
			passwords.put(user, password);
//			active.put(user, false);
		}
	}

	public void go() {
		while(go) {
			String greeting = "Input 1 for Customer menu, 2 for employee menu, "
					+ "\nor, at any time, 0 for the main menu: ";
			System.out.print(greeting);
//			log.info("Msg: " + greeting);
			input = in.nextLine();
			System.out.println();
//			log.info("Input" + input);
			int menuSel = Integer.parseInt(input);
			switch(menuSel) {
			case EXIT:
				go = false;
				break;
			case CUSTOMER:
				menuSel = Integer.parseInt(customerMenu());
				break;
			case EMPLOYEE:
				menuSel = Integer.parseInt(employeeMenu());
			}
		}
		System.out.println("Goodbye!!!");
	}

	private String employeeMenu() {
		String emGreeting = "Employee menu";
		System.out.println(emGreeting);
//		log.info(emGreeting + " entered.");
		boolean go = true;
		boolean loggedIn = false;
		//User emp = null;
		while(go) {
			if(!loggedIn) {
				employee = Employee.login(in);
//				active = s.deSerialActive();
				log.info("Employee logged in.");
			}
			boolean validInput = false;
			while(!validInput) {
				System.out.println("\nYou have the following options:");
				System.out.print("1 to manage lot inventory, 2 to manage offers, 3 to"
						+ " \nview payments, \nand as always, 0 to logout and exit the system: ");
				input = in.nextLine();
				if(Integer.parseInt(input) == 0 | Integer.parseInt(input) == 1 
						| Integer.parseInt(input) == 2) validInput = true;
			}
			if(Integer.parseInt(input) == 0) {
				//active.put(employee, false);
				go = false;
			}
			if(Integer.parseInt(input) == 1) manageInventory();
			if(Integer.parseInt(input) == 2) manageOffers();
			if(Integer.parseInt(input) == 3) viewPayments();
		}
		System.out.println("Back");
		return input;
	}

	private void viewPayments() {
		// TODO Auto-generated method stub
		
	}
	
	private void manageOffers() {
		while(true) {
			System.out.print("Manage offers: "); System.out.println("Input 1 to accept, 2 to reject, or 3 for next offer: ");
			input = in.nextLine();
			int currentOffer = 0;
			if(Integer.parseInt(input) == 0) {
				System.out.println("Back");
				break;
			}
			if(Integer.parseInt(input) == 1) 
				if(!offers.isEmpty()) Employee.acceptOffer(offers.get(currentOffer));
				else System.out.println("There are no offers yet.");
			if(Integer.parseInt(input) == 2) 
				if(!offers.isEmpty()) rejectOffer(currentOffer);
				else System.out.println("There are no offers yet.");
			if(Integer.parseInt(input) == 3) {
				currentOffer = nextOffer(currentOffer);
				if(!offers.isEmpty()) System.out.println("Current offer: "  + offers.get(currentOffer).getAmount());
				else System.out.println("There are no offers yet.");
			}
		}
	}
	
	private int nextOffer(int currentOffer) {
		if(!(currentOffer < offers.size() - 1))
		return currentOffer ++;
		else return currentOffer = 0;
	}
	
	private void rejectOffer(int currentOffer) {
		// TODO Auto-generated method stub
		
	}
	
//	private void acceptOffer(int currentOffer) {
//		
//	}
	
	private void manageInventory() {
		while(true) {
			System.out.print("Input 1 to view inventory 2 to add a car to the lot, or 3 to remove: ");
			input = in.nextLine();
			if(Integer.parseInt(input) == 0) break;
			if(Integer.parseInt(input) == 1) viewInv();
			if(Integer.parseInt(input) == 2) menuAddCar();
			if(Integer.parseInt(input) == 3) removeCar();
		}
	}
	
	private void viewInv() {
//		lot = s.deSerialCars();
		if(lot != null) {
			System.out.println();
			for(Entry<Car, Price> car : lot.entrySet()) {
				System.out.println(car.getKey().getMake() + " " + car.getKey().getModel() + " $" + car.getValue().getAmount());
			}
			System.out.println();
		} else if(lot == null) lot = new Lot();
		else if(lot.isEmpty()) System.out.println("Lot empty!");
	}
	
	private void removeCar() {
		System.out.print("What is the vin of the car do remove?");
		input = in.nextLine();
		if(Integer.parseInt(input) != 0) {
			lot.remove(cdi.selectCarByVIN(input));
			cdi.updateLot();
		}
	}

	private void menuAddCar() {
		System.out.print("Input make: ");
		String make = in.nextLine();
		System.out.print("Model: ");
		String model = in.nextLine();
		System.out.print("MSRP: $");
		Price msrp = new Price(Integer.parseInt(in.nextLine()));
		String vin = Double.toString(Math.abs((double)999999999 * Math.random()));
		Car car = new Car(make, model, vin, msrp);
		cdi.insertCar(car);
		lot.put(car, msrp);
		s.createCar(car);
		s.serialCars(lot);
		
		
	}

	//	public static User login(UserType type) {
//		boolean loggedIn = false;
//		String username = null;
//		User user = null;
//		while(!loggedIn) {
//			
//		}
//		return usernames.get(username);
//	}

	private String customerMenu() {
		System.out.print("Customer menu\nInput 1 to login, or 2 to create a customer acount: ");
		input = in.nextLine();
		System.out.println();
		boolean loggedIn = false;
		Customer user = null;
		while(!loggedIn) {
			if(Integer.parseInt(input) == 1) {
				user = (Customer) User.login(in);
				loggedIn = true;
			}
			if(Integer.parseInt(input) == 2) {
				user = (Customer) User.regCustAcct(in);
				loggedIn = true;
			}
		}
		while(go) {
			//Customer.login(String input, Scanner in);
			System.out.print("Input 1 to view cars, 2 to make an offer,\n 3 to view owned cars, 4 to add \n"
					+ "owned cars, or 5 to view remaining payments: ");
			input = in.nextLine();
			System.out.println();
			if(Integer.parseInt(input) == 0) go = false;
			if(Integer.parseInt(input) == 1) lot.viewInv();
			if(Integer.parseInt(input) == 2) user.makeOffer(in);
			if(Integer.parseInt(input) == 3) user.viewOwned();
			if(Integer.parseInt(input) == 4) user.addOwned(in);
			if(Integer.parseInt(input) == 5) user.viewPmtsRmng();
			System.out.println();
			System.out.println();
		}
		return input;
		
	}

	public static void processOffer(Offer offer) {
		// TODO Auto-generated method stub
		
	}

}
