package com.gyan.service;

import java.sql.SQLException;

import com.gyan.persistence.JourneyDao;
import com.gyan.persistence.JourneyDaoImpl;

public class JourneyServiceImpl implements JourneyService {
	
	private JourneyDao journeyDao = new JourneyDaoImpl();

	@Override
	public boolean swipeIn(int cId, int sId) throws ClassNotFoundException, SQLException {
		return journeyDao.swipeIn(cId, sId);
	}

	@Override
	public boolean swipeOut(int cId, int dId) throws ClassNotFoundException, SQLException {
		return journeyDao.swipeOut(cId, dId, 20);
	}

	@Override
	public int getSourceStation(int cId) throws ClassNotFoundException, SQLException {
		return journeyDao.getSourceStation(cId);
	}

	@Override
	public int getFare(int cId) throws ClassNotFoundException, SQLException {
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
