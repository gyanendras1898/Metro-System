package com.gyan.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gyan.beans.Station;

public class JourneyDaoImpl implements JourneyDao {

	@Override
	public boolean swipeIn(int cId, int sId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "INSERT INTO journey (card_id,boarding_station,swipe_in_time,destination_station)"
				+ " values(?,?,now(),?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setInt(1, cId);
		preparedStatement.setInt(2, sId);
		preparedStatement.setInt(3, -1);
		
		int affectedRows=preparedStatement.executeUpdate();
		
		connection.close();
		
		if(affectedRows>0) 
			return true;
		return false;
	}

	@Override
	public boolean swipeOut(int cId, int dId, int fare) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "Update journey set destination_station = ?, swipe_out_time = now(), fare =?"
				+ " where card_id=? order by swipe_in_time desc limit 1;";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, dId);
		preparedStatement.setInt(2, fare);
		preparedStatement.setInt(3, cId);
		
		int affectedRows=preparedStatement.executeUpdate();
		
		connection.close();
		
		if(affectedRows>0) 
			return true;
		return false;
	}

	@Override
	public int getSourceStation(int cId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "select boarding_station from journey where card_id = ? order by swipe_in_time desc limit 1";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, cId);
		
		ResultSet rs = preparedStatement.executeQuery();

		if(rs.next())
			return rs.getInt(1);
		return 0;
	}

	@Override
	public int getFare(int cId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "select fare from journey where card_id = ? order by swipe_out_time desc limit 1";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, cId);
		
		ResultSet rs = preparedStatement.executeQuery();

		if(rs.next())
			return rs.getInt(1);
		return 0;
	}

	@Override
	public boolean isJourneyOngoing(int cId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "select destination_station from journey where card_id = ? order by swipe_in_time desc limit 1";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, cId);
		
		ResultSet rs = preparedStatement.executeQuery();

		if(rs.next()) {
			if(rs.getInt(1) == -1)	return true;
		}
			
		return false;
	}

	@Override
	public boolean getDuration(int cId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
		String query = "SELECT TIMESTAMPDIFF(second, swipe_in_time, swipe_out_time) from journey where card_id = ? order by swipe_out_time desc limit 1";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, cId);
		
		ResultSet rs = preparedStatement.executeQuery();

		if(rs.next())
			if(rs.getInt(1) > 20)	return true;
		return false;
	}

}
