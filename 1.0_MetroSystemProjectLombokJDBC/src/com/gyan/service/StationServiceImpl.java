package com.gyan.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gyan.beans.Station;
import com.gyan.persistence.StationDao;
import com.gyan.persistence.StationDaoImpl;

public class StationServiceImpl implements StationService {
	
	StationDao stationDao = new StationDaoImpl();

	@Override
	public List<Station> getStations() throws ClassNotFoundException, SQLException {
		
		return stationDao.getStations();
	}

}
