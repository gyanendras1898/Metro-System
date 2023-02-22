package com.gyan.model.service;

public interface JourneyService {

boolean swipeIn(int cId, int sId) ;
	
	boolean swipeOut(int cId, int dId) ;
	
	int getSourceStation(int cId) ;
	
	double getFareForMostRecentJourney(int cId) ;
	
	boolean isJourneyOngoing(int cId) ;
	
	boolean getDurationForMostRecentJourney(int cId) ;

}
