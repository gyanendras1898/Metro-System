package com.gyan.persistence;

import java.sql.SQLException;

public interface JourneyDao {
	boolean swipeIn(int cId, int sId) throws SQLException;
	
	boolean swipeOut(int cId, int dId, double fare) throws SQLException;
	
	int getSourceStation(int cId) throws SQLException;
	
	double getFare(int cId) throws SQLException;
	
	boolean isJourneyOngoing(int cId) throws SQLException;
	
	int getDuration(int cId) throws SQLException;

}
