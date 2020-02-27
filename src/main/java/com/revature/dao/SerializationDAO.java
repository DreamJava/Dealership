package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.revature.org.Car;
import com.revature.org.Customer;
import com.revature.org.Owned;
import com.revature.org.Offer;
import com.revature.org.User;
import com.revature.util.DealerSystem;
import com.revature.util.Lot;

public class SerializationDAO {

	public void createCar(Car c) {
		String filename = c.getVin() + ".dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void createCustomer(Customer c) {
		String username = null;
		for(Entry<String, User> entry : DealerSystem.usernames.entrySet()) 
			if(entry.getValue() == c) username = entry.getKey();
		String filename = "./Customers/" + username + ".dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Customer getCustomer(String username) {
		
		String filename;
		filename = "./Customers/" + username + ".dat";
		Customer c = null;
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
			c = (Customer) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	
	public void serialCars(Lot l) {
		String filename;
		filename = "CarLot.dat";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(l);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		try {
//			String filename = "CarLot.ser";
//			if(Files.exists(Paths.get(filename))) {
//				FileOutputStream fos = new FileOutputStream("CarLot.ser");
//				ObjectOutputStream oos = new ObjectOutputStream(fos);
//				oos.writeObject(DealerSystem.lot);
//				oos.close();
//				fos.close();
//			}
//		}
//		catch(IOException ioe) {
//			ioe.printStackTrace();
//		}
//		finally {
//			DealerSystem.log.info("Serialization passed... lot has been saved.");
//		}
	}
	

	public Lot deSerialCars() {
		String filename;
		filename = "CarLot.dat";
		Lot b = null;
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
			b = (Lot) ois.readObject();
		} catch (FileNotFoundException e) {
			File file = new File(filename);
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return b;

//		String filename = "CarLot.ser";
//		Lot l = null;
//		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
//			l = (Lot) ois.readObject();
//		} catch (FileNotFoundException e) {
//			//serialCars();
//			//l = deSerialCars();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return l;
	}

	
	
	public void serialUnames() {
		try {
			String filename = "Unames.ser";
			if(Files.exists(Paths.get(filename))) {
				FileOutputStream fos = new FileOutputStream("Unames.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(DealerSystem.usernames);
				oos.close();
				fos.close();
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			DealerSystem.log.info("Serialization passed... usernames have been saved.");
		}
	}
	
	public HashMap<String, User> deSerialUnames() {
		String filename = "Unames.ser";
		HashMap<String, User> un = null;
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
			un = (HashMap<String, User>) ois.readObject();
		} catch (FileNotFoundException e) {
			serialUnames();
			un = deSerialUnames();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return un;
	}

	
	
	public void serialPsswds() {
		try {
			String filename = "Psswrds.ser";
			if(!Files.exists(Paths.get(filename))) {
				FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(DealerSystem.passwords);
				oos.close();
				fos.close();
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			DealerSystem.log.info("Serialization passed... passwords have been saved.");
		}
	}
	
	public HashMap<User, String> deSerialPass() {
		String filename = "Psswds.ser";
		HashMap<User, String> p = null;
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
			p = (HashMap<User, String>) ois.readObject();
		} catch (FileNotFoundException e) {
			serialPsswds();
			p = deSerialPass();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return p;
	}	
	
//	public void serialActive() {
//		try {
//			String filename = "Active.ser";
//			if(Files.exists(Paths.get(filename))) {
//				FileOutputStream fos = new FileOutputStream("Active.ser");
//				ObjectOutputStream oos = new ObjectOutputStream(fos);
//				oos.writeObject(DealerSystem.active);
//				oos.close();
//				fos.close();
//			}
//		}
//		catch(IOException ioe) {
//			ioe.printStackTrace();
//		}
//		finally {
//			DealerSystem.log.info("Serialization passed... active users have been saved.");
//		}
//	}
	


//	public HashMap<User, Boolean> deSerialActive() {
//		String filename = "Active.ser";
//		HashMap<User, Boolean> a = null;
//		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
//			a = (HashMap<User, Boolean>) ois.readObject();
//		} catch (FileNotFoundException e) {
//			serialActive();
//			a = deSerialActive();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return a;
//	}

	
	public void serialOffers() {
		try {
			String filename = "Offers.ser";
			if(Files.exists(Paths.get(filename))) {
				FileOutputStream fos = new FileOutputStream("Active.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(DealerSystem.offers);
				oos.close();
				fos.close();
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			DealerSystem.log.info("Serialization passed... active offers have been saved.");
		}
	}
	


	public ArrayList<Offer> deSerialOffers() {
		String filename = "Offers.ser";
		ArrayList<Offer> a = null;
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
			a = (ArrayList<Offer>) ois.readObject();
		} catch (FileNotFoundException e) {
			serialOffers();
			a = deSerialOffers();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return a;
	}

	
	
	public void serialOwned() {
		try {
			String filename = "Owned.ser";
			if(Files.exists(Paths.get(filename))) {
				FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(DealerSystem.lot);
				oos.close();
				fos.close();
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			DealerSystem.log.info("Serialization passed... owned cars have been saved.");
		}
	}
	

	public Owned deSerialOwned() {
		String filename = "CarLot.ser";
		Owned o = null;
		try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis);) { //try with resources 
			o = (Owned) ois.readObject();
		} catch (FileNotFoundException e) {
			serialOwned();
			o = deSerialOwned();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;

	}

	
}
