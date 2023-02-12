package com.gyan.service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyan.beans.Card;
import com.gyan.persistence.CardDao;

@Service
public class CardServiceImpl implements CardService {
	@Autowired
	private CardDao cardDao;

	@Override
	public int registerUser(double balance) throws SQLException {
		Card card = new Card();
		card.setBalance(balance);
		Card newCard = cardDao.save(card);
		return newCard.getId();
	}

	@Override
	public boolean isCardPresent(int cardID) throws SQLException {
		return cardDao.findById(cardID).isPresent();
	}
	
	@Override
	public double viewBalance(int cardId)throws SQLException {
		Card card = cardDao.findById(cardId).get();
		return card.getBalance();
	}

	@Override
	public boolean addCardBalance(int cardId, double balance)throws SQLException {
//		return cardDao.addBalance(cardId,this.viewBalance(cardId)+balance);
		return true;
	}

	

	@Override
	public List<Card> viewCardId()throws SQLException {
		return cardDao.findAll();
	}

	
}
