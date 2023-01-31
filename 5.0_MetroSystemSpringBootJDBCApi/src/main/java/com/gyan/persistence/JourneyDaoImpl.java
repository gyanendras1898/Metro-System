package com.gyan.persistence;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class JourneyDaoImpl implements JourneyDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean swipeIn(int cardID, int stationId) throws SQLException {
		String query = "INSERT INTO journey (card_id,boarding_station,swipe_in_time,destination_station)"
				+ " values(?,?,now(),?)";
		return jdbcTemplate.update(query,cardID,stationId,-1)>0;
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
		List<Integer> destinations = jdbcTemplate.queryForList(query, Integer.class, cardId);
		return destinations!=null && destinations.size()>0;
	}

	@Override
	public int getDuration(int cardId) throws SQLException {
		String query = "SELECT TIMESTAMPDIFF(second, swipe_in_time, swipe_out_time) from journey where card_id = ? order by swipe_out_time desc limit 1";
		int durationInSecond = jdbcTemplate.queryForObject(query, Integer.class, cardId);
		return durationInSecond;
	}

}
