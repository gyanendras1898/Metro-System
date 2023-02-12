package com.gyan.service;

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
	public int registerUser(double balance)  {
		Card card = new Card();
		card.setBalance(balance);
		return cardDao.save(card).getId();
	}

	@Override
	public boolean isCardPresent(int cardID)  {
		return cardDao.findById(cardID).isPresent();
	}
	
	@Override
	public double viewBalance(int cardId)  {
		return cardDao.findById(cardId).get().getBalance();
	}

	@Override
	public boolean addCardBalance(int cardId, double balance)  {
		return cardDao.addBalance(cardId,this.viewBalance(cardId)+balance) >0;
	}

	

	@Override
	public List<Card> viewCardId()  {
		return cardDao.findAll();
	}

	
}
