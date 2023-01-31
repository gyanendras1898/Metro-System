package com.gyan.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.gyan.beans.Station;

import lombok.Setter;

public class StationDaoImpl implements StationDao {
	@Setter
	private DataSource dataSource;
	
	@Override
	public List<Station> getStations() throws ClassNotFoundException, SQLException {
		List<Station> stations = new ArrayList<>();
		Connection connection=dataSource.getConnection();
		String query = "select * from stations";
		
		PreparedStatement preparedStatement = connection.prepareStatement(query); 
		
		ResultSet rs=preparedStatement.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("number");
			String name = rs.getString("name");
			stations.add(new Station(id,name));
		}
		return stations;
	}

}
