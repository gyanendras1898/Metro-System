package com.gyan.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gyan.beans.Station;

public class StationDaoImpl implements StationDao {

	@Override
	public List<Station> getStations() throws ClassNotFoundException, SQLException {
		List<Station> stations = new ArrayList<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/metro_system_db","root","admin");
		
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
