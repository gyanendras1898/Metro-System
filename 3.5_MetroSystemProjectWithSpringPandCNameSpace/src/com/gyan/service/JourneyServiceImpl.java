package com.gyan.service;

import java.sql.SQLException;

import com.gyan.persistence.JourneyDao;
import com.gyan.persistence.JourneyDaoImpl;

import lombok.Setter;

public class JourneyServiceImpl implements JourneyService {
	@Setter
	private JourneyDao journeyDao;
	@Setter
	private CardService cardService;

	@Override
	public boolean swipeIn(int cId, int sId) throws ClassNotFoundException, SQLException {
		return journeyDao.swipeIn(cId, sId);
	}

	@Override
	public boolean swipeOut(int cId, int dId) throws ClassNotFoundException, SQLException {
		int currSourceStation = getSourceStation(cId);
		double fare = (Math.abs(currSourceStation - dId)) * 5;
		cardService.addCardBalance(cId, -fare);
		return journeyDao.swipeOut(cId, dId, fare);
	}

	@Override
	public int getSourceStation(int cId) throws ClassNotFoundException, SQLException {
		return journeyDao.getSourceStation(cId);
	}

	@Override
	public double getFare(int cId) throws ClassNotFoundException, SQLException {
		return journeyDao.getFare(cId);
	}

	@Override
	public boolean isJourneyOngoing(int cId) throws ClassNotFoundException, SQLException {
		return journeyDao.isJourneyOngoing(cId);
	}

	@Override
	public boolean getDuration(int cId) throws ClassNotFoundException, SQLException {
		return journeyDao.getDuration(cId);
	}

}
