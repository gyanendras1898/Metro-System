package com.gyan.service;

import java.sql.SQLException;

public interface JourneyService {

boolean swipeIn(int cId, int sId) throws ClassNotFoundException, SQLException;
	
	boolean swipeOut(int cId, int dId) throws ClassNotFoundException, SQLException;
	
	int getSourceStation(int cId) throws ClassNotFoundException, SQLException;
	
	int getFare(int cId) throws ClassNotFoundException, SQLException;
	
	boolean isJourneyOngoing(int cId) throws ClassNotFoundException, SQLException;
	
	boolean getDuration(int cId) throws ClassNotFoundException, SQLException;

}
