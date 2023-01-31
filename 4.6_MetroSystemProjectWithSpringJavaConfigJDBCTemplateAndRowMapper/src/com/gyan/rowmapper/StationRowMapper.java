package com.gyan.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gyan.beans.Station;

public class StationRowMapper implements RowMapper<Station> {

	@Override
	public Station mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("number");
		String name = rs.getString("name");
		Station station = new Station(id,name);
		return station;
	}

}
