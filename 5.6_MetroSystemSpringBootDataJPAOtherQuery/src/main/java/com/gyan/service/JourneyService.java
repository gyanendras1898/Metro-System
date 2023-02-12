package com.gyan.service;

public interface JourneyService {

boolean swipeIn(int cId, int sId) ;
	
	boolean swipeOut(int cId, int dId) ;
	
	int getSourceStation(int cId) ;
	
	double getFare(int cId) ;
	
	boolean isJourneyOngoing(int cId) ;
	
	boolean getDuration(int cId) ;

}
