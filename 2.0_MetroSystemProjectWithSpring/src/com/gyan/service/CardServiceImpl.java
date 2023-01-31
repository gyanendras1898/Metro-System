package com.gyan.service;

import java.sql.SQLException;
import java.util.List;

import com.gyan.beans.Card;
import com.gyan.persistence.CardDao;
import com.gyan.persistence.CardDaoImpl;

import lombok.Setter;

public class CardServiceImpl implements CardService {
	
	@Setter
	private CardDao cardDao;
	
	

	@Override
	public boolean registerUser(double balance) throws ClassNotFoundException, SQLException {
		int newId=cardDao.addCard(balance);
		if(newId!=-1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCardPresent(int cId) throws ClassNotFoundException, SQLException {
		return cardDao.isCardPresent(cId);
	}
	
	@Override
	public double viewBalance(int cardId)throws ClassNotFoundException, SQLException {
		return cardDao.viewBalance(cardId);
	}

	@Override
	public boolean addCardBalance(int cardId, double balance)throws ClassNotFoundException, SQLException {
		return cardDao.addBalance(cardId,this.viewBalance(cardId)+balance);
	}

	

	@Override
	public List<Card> viewCardId()throws ClassNotFoundException, SQLException {
		return cardDao.allCard();
	}

	
}
