package com.gyan.persistence;

import java.sql.SQLException;

public interface JourneyDao {
	boolean swipeIn(int cId, int sId) throws ClassNotFoundException, SQLException;
	
	boolean swipeOut(int cId, int dId, double fare) throws ClassNotFoundException, SQLException;
	
	int getSourceStation(int cId) throws ClassNotFoundException, SQLException;
	
	double getFare(int cId) throws ClassNotFoundException, SQLException;
	
	boolean isJourneyOngoing(int cId) throws ClassNotFoundException, SQLException;
	
	boolean getDuration(int cId) throws ClassNotFoundException, SQLException;

}
