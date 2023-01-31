package com.gyan.service;

import java.sql.SQLException;

public interface JourneyService {

boolean swipeIn(int cId, int sId) throws SQLException;
	
	boolean swipeOut(int cId, int dId) throws SQLException;
	
	int getSourceStation(int cId) throws SQLException;
	
	double getFare(int cId) throws SQLException;
	
	boolean isJourneyOngoing(int cId) throws SQLException;
	
	boolean getDuration(int cId) throws SQLException;

}
