package com.gyan.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyan.beans.Journey;
import com.gyan.persistence.JourneyDao;

@Service
public class JourneyServiceImpl implements JourneyService {
	@Autowired
	private JourneyDao journeyDao;
	@Autowired
	private CardService cardService;

	@Override
	public boolean swipeIn(int cardId, int stationId) throws SQLException {
		Journey journey = new Journey();
		journey.setCardId(cardId);
		journey.setBoardingStationNo(stationId);
		return journeyDao.save(journey) != null;
	}

	@Override
	public boolean swipeOut(int cardId, int stationId) throws SQLException {
		int currSourceStation = getSourceStation(cardId);
		double fare = (Math.abs(currSourceStation - stationId)) * 5;
		cardService.addCardBalance(cardId, -fare);
//		return journeyDao.swipeOut(cardId, stationId, fare);
		return true;
	}

	@Override
	public int getSourceStation(int cardId) throws SQLException {
//		return journeyDao.getSourceStation(cardId);
		return 1;
	}

	@Override
	public double getFare(int cardId) throws SQLException {
//		return journeyDao.getFare(cardId);
		return 10.0;
	}

	@Override
	public boolean isJourneyOngoing(int cardId) throws SQLException {
//		return journeyDao.isJourneyOngoing(cardId);
		return true;
	}

	@Override
	public boolean getDuration(int cardId) throws SQLException {
		int lateTime = 20;
		int durationTime = 10;
//				journeyDao.getDuration(cardId);
		return durationTime > lateTime;
	}

}
