package com.gyan.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JourneyDaoImpl implements JourneyDao {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean swipeIn(int cId, int sId) throws SQLException {
		Connection connection=dataSource.getConnection();
		
		String query = "INSERT INTO journey (card_id,boarding_station,swipe_in_time,destination_station)"
				+ " values(?,?,now(),?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setInt(1, cId);
		preparedStatement.setInt(2, sId);
		preparedStatement.setInt(3, -1);
		
		int affectedRows=preparedStatement.executeUpdate();
		
		if(affectedRows>0) 
			return true;
		return false;
	}

	@Override
	public boolean swipeOut(int cardId, int stationId, double fare) throws SQLException {

		String query = "Update journey set destination_station = ?, swipe_out_time = now(), fare =?"
				+ " where card_id=? order by swipe_in_time desc limit 1;";
		
		int affectedRows = jdbcTemplate.update(query, stationId, fare, cardId);
		return affectedRows > 0;
	}

	@Override
	public int getSourceStation(int cardId) throws SQLException {
		String query = "select boarding_station from journey where card_id = ? order by swipe_in_time desc limit 1";		
		int sourceStation = jdbcTemplate.queryForObject(query, Integer.class, cardId);			
		return sourceStation;
	}

	@Override
	public double getFare(int cardId) throws SQLException {
		String query = "select fare from journey where card_id = ? order by swipe_out_time desc limit 1";
		double fare = jdbcTemplate.queryForObject(query, Double.class, cardId);
		return fare;
	}

	@Override
	public boolean isJourneyOngoing(int cardId) throws SQLException {
		String query = "select destination_station from journey where card_id = ? order by swipe_in_time desc limit 1";
		return jdbcTemplate.queryForObject(query, Integer.class, cardId) <=0;
	}

	@Override
	public int getDuration(int cardId) throws SQLException {
		String query = "SELECT TIMESTAMPDIFF(second, swipe_in_time, swipe_out_time) from journey where card_id = ? order by swipe_out_time desc limit 1";
		int durationInSecond = jdbcTemplate.queryForObject(query, Integer.class, cardId);
		return durationInSecond;
	}

}
