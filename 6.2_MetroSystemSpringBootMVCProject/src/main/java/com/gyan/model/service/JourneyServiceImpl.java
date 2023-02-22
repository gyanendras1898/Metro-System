package com.gyan.model.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gyan.beans.Journey;
import com.gyan.model.persistence.JourneyDao;

@Service
public class JourneyServiceImpl implements JourneyService {
	@Autowired
	private JourneyDao journeyDao;
	@Autowired
	private CardService cardService;

	@Override
	public boolean swipeIn(int cardId, int stationId) {
		Journey journey = new Journey();
		journey.setCardId(cardId);
		journey.setBoardingStationNo(stationId);
		Timestamp swipeInTime = new Timestamp(System.currentTimeMillis());
		journey.setSwipeIn(swipeInTime);
		return journeyDao.save(journey) != null;
	}

	@Override
	public boolean swipeOut(int cardId, int stationId) {
		System.out.println("Swipe out ================");
		int currSourceStation = getSourceStation(cardId);
		System.out.println("source station "+currSourceStation);
		double fare = (Math.abs(currSourceStation - stationId)) * 5;
		cardService.addCardBalance(cardId, -fare);
		Timestamp swipeOutTime = new Timestamp(System.currentTimeMillis());
		System.out.println("seipe out "+swipeOutTime);
		int affectedRows = journeyDao.swipeOut(cardId, stationId, swipeOutTime, fare);
		System.out.println("affected row "+affectedRows);
		return affectedRows>0;
	}

	@Override
	public int getSourceStation(int cardId) {
		List<Integer> sourceStation = journeyDao.getSourceStation(cardId, PageRequest.of(0, 1));
		if(sourceStation == null || sourceStation.size()==0)
			return -1;
		else return sourceStation.get(0);
	}

	@Override
	public double getFareForMostRecentJourney(int cardId) {
		List<Double> fare = journeyDao.findFareByCardId(cardId, PageRequest.of(0, 1));
		if(fare == null || fare.size()==0)
			return -1;
		else return fare.get(0);
	}

	@Override
	public boolean isJourneyOngoing(int cardId) {
		return journeyDao.isJourneyOngoing(cardId);
	}

	
	@Override
	public boolean getDurationForMostRecentJourney(int cardId) {
		int lateTime = 20;
	    List<Long> duration = journeyDao.findJourneyDurationByCardId(cardId, PageRequest.of(0, 1));
	    if (duration == null || duration.size() ==0) {
	        return false;
	    }
	    return duration.get(0) > lateTime;
	}

}
