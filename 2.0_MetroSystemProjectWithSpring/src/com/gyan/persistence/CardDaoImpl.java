package com.gyan.persistence;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gyan.beans.Card;
import com.gyan.beans.Station;


public class CardDaoImpl implements CardDao {

	@Override
	public int addCard(double balance) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "insert into cards (balance) values (?);";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS); 
		preparedStatement.setDouble(1, balance);
		int affectedRows=preparedStatement.executeUpdate();
		
		if(affectedRows>0) {
			ResultSet rs=preparedStatement.getGeneratedKeys();
			if(rs.next())
				return rs.getInt(1);
		}
		return -1;
	}

	@Override
	public double viewBalance(int cardId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "select balance from cards where id=?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		preparedStatement.setDouble(1, cardId);
		
		ResultSet rs=preparedStatement.executeQuery();
		
		if(rs.next()) 
			return rs.getDouble(1);	
		return 0.0;
	}
	
	@Override
	public boolean addBalance(int cardId, double balance) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "update cards set balance = ? where id=?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		preparedStatement.setDouble(1, balance);
		preparedStatement.setDouble(2, cardId);
		
		int rs=preparedStatement.executeUpdate();
		
		connection.close();
		
		return rs>0;
	}


	@Override
	public boolean isCardPresent(int cardId) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "SELECT EXISTS (SELECT * FROM CARDS WHERE ID=?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		preparedStatement.setDouble(1, cardId);
		
		ResultSet rs=preparedStatement.executeQuery();
		
		if(rs.next())
			return true;
		return false;
	}

	@Override
	public List<Card> allCard()throws SQLException, ClassNotFoundException {
		List<Card> cards = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "select * from cards";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		
		ResultSet rs=preparedStatement.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("number");
			double balance = rs.getDouble("balance");
			cards.add(new Card(id,balance));
		}
		return cards;

	}

	@Override
	public int lastCardAdded() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		Statement statement = connection.createStatement();
		
		String query = "select cId from card order by number desc limit 1;";
		
		
		ResultSet rs = statement.executeQuery(query);
		
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	

}
