package com.gyan.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gyan.beans.Card;

@Repository
public class CardDaoImpl implements CardDao {
	@Autowired
	private DataSource dataSource;

	@Override
	public int addCard(double balance) throws SQLException{
		Connection connection=dataSource.getConnection();
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
	public double viewBalance(int cardId) throws SQLException {
		Connection connection=dataSource.getConnection();
		String query = "select balance from cards where id=?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		preparedStatement.setDouble(1, cardId);
		
		ResultSet rs=preparedStatement.executeQuery();
		
		if(rs.next()) 
			return rs.getDouble(1);	
		return 0.0;
	}
	
	@Override
	public boolean addBalance(int cardId, double balance) throws SQLException {
		Connection connection=dataSource.getConnection();
		String query = "update cards set balance = ? where id=?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		preparedStatement.setDouble(1, balance);
		preparedStatement.setDouble(2, cardId);
		
		int rs=preparedStatement.executeUpdate();
		
		return rs>0;
	}


	@Override
	public boolean isCardPresent(int cardId) throws SQLException{
		Connection connection=dataSource.getConnection();
		String query = "SELECT EXISTS (SELECT * FROM CARDS WHERE ID=?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		preparedStatement.setInt(1, cardId);
		
		ResultSet rs=preparedStatement.executeQuery();
		
		if(rs.next())
			return rs.getInt(1)==1;
		return false;
	}

	@Override
	public List<Card> allCard()throws SQLException {
		List<Card> cards = new ArrayList<>();
		Connection connection=dataSource.getConnection();
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
	public int lastCardAdded() throws SQLException {
		Connection connection=dataSource.getConnection();
		Statement statement = connection.createStatement();
		
		String query = "select cId from card order by number desc limit 1;";
		
		
		ResultSet rs = statement.executeQuery(query);
		
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	

}
