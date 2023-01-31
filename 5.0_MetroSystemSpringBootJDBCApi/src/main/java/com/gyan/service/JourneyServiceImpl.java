package com.gyan.service;

import java.sql.SQLException;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyan.persistence.JourneyDao;

@Service
public class JourneyServiceImpl implements JourneyService {
	@Autowired
	private JourneyDao journeyDao;
	@Autowired
	private CardService cardService;

	@Override
	public boolean swipeIn(int cId, int sId) throws SQLException {
		return journeyDao.swipeIn(cId, sId);
	}

	@Override
	public boolean swipeOut(int cId, int dId) throws SQLException {
		int currSourceStation = getSourceStation(cId);
		double fare = (Math.abs(currSourceStation - dId)) * 5;
		cardService.addCardBalance(cId, -fare);
		return journeyDao.swipeOut(cId, dId, fare);
	}

	@Override
	public int getSourceStation(int cId) throws SQLException {
		return journeyDao.getSourceStation(cId);
	}

	@Override
	public double getFare(int cId) throws SQLException {
		return journeyDao.getFare(cId);
	}

	@Override
	public boolean isJourneyOngoing(int cId) throws SQLException {
		return journeyDao.isJourneyOngoing(cId);
	}

	@Override
	public boolean getDuration(int cId) throws SQLException {
		int lateTime = 20;
		int durationTime = journeyDao.getDuration(cId);
		return durationTime > lateTime;
	}

}
