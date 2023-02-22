package com.gyan.model.service;

import java.util.List;

import com.gyan.beans.Station;

public interface StationService {
	List<Station> getStations() ;
	Station addStation(String name);
}
