package com.gyan.service;

import java.sql.SQLException;
import java.util.List;

import com.gyan.beans.Station;

public interface StationService {
	List<Station> getStations() throws SQLException;

}
