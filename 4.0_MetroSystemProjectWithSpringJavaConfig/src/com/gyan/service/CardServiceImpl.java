package com.gyan.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyan.beans.Card;
import com.gyan.persistence.CardDao;
import com.gyan.persistence.CardDaoImpl;

import lombok.Setter;
@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardDao cardDao;
	
	

	@Override
	public int registerUser(double balance) throws SQLException {
		return cardDao.addCard(balance);
	}

	@Override
	public boolean isCardPresent(int cId) throws SQLException {
		return cardDao.isCardPresent(cId);
	}
	
	@Override
	public double viewBalance(int cardId)throws SQLException {
		return cardDao.viewBalance(cardId);
	}

	@Override
	public boolean addCardBalance(int cardId, double balance)throws SQLException {
		return cardDao.addBalance(cardId,this.viewBalance(cardId)+balance);
	}

	

	@Override
	public List<Card> viewCardId()throws SQLException {
		return cardDao.allCard();
	}

	
}
