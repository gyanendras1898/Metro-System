package com.gyan.service;

import java.util.List;

import com.gyan.beans.Station;

public interface StationService {
	List<Station> getStations() ;
	int addStation(String name);
}
