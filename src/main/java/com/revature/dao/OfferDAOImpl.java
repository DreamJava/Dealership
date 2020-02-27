package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.org.Car;
import com.revature.org.Offer;
import com.revature.org.Price;
import com.revature.util.ConnectionFactory;
import com.revature.util.Lot;

public class OfferDAOImpl implements OfferDAO {

	@Override
	public void insertOffer(Offer o) {
		
		try (Connection conn = ConnectionFactory.getConnection()) {

			// putting in a native sql query utilizing a prepared statement
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Offers VALUES(?,?,?)");

			// we are setting the first question mark to be the make that belongs
			// to our Offer object
			ps.setString(1, o.getCar().getMake());

			// we are setting the second question mark to be the model that belongs
			// to our Offer object
			ps.setString(2, o.getCar().getMake());

			// we are setting the third question mark to be the model that belongs
			// to our Offer object
			ps.setInt(3, o.getPrice().getAmount());

			// allows us to execute a query without a result
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Offer> selectAllOffers() {
		List<Offer> offers = new ArrayList<Offer>();
		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Offers");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				offers.add(new Offer(rs.getString(1), rs.getString(2), new Price(rs.getInt(3))));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public void updateOffer(Offer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOffer(Offer o) {
		// TODO Auto-generated method stub

	}

}
