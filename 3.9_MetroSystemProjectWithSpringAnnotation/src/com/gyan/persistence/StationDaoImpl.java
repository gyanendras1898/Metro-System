package com.gyan.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gyan.beans.Station;

@Repository
public class StationDaoImpl implements StationDao {
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Station> getStations() throws SQLException {
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
