package com.gyan.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyan.beans.Station;
import com.gyan.persistence.StationDao;

@Service
public class StationServiceImpl implements StationService {
	@Autowired
	private StationDao stationDao;

	@Override
	public List<Station> getStations() throws SQLException {
		
		return stationDao.findAll();
	}

}
