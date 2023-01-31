package com.gyan.persistence;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gyan.beans.Station;
import com.gyan.rowmapper.StationRowMapper;

@Repository
public class StationDaoImpl implements StationDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Station> getStations() throws SQLException {
		String query = "select * from stations";
		List<Station> stations = jdbcTemplate.query(query, new StationRowMapper());
		return stations;
	}

}
