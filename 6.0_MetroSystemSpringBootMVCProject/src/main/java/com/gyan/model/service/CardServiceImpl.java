package com.gyan.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyan.beans.Card;
import com.gyan.model.persistence.CardDao;

@Service
public class CardServiceImpl implements CardService {
	@Autowired
	private CardDao cardDao;

	@Override
	public int registerUser(String userName, double balance) {
		if (balance < 100)
			return 0;
		Card card = new Card();
		card.setBalance(balance);
		card.setUserName(userName);
		return cardDao.save(card).getId();
	}

	@Override
	public double viewBalance(int cardId) {
		Optional<Card> options = cardDao.findById(cardId);
		if (options.isPresent())
			return options.get().getBalance();
		return -1;
	}

	@Override
	public boolean addCardBalance(int cardId, double balance) {
		return cardDao.addBalance(cardId,this.viewBalance(cardId)+balance) >0;
	}

	@Override
	public List<Card> viewCardId() {
		return cardDao.findAll();
	}

	@Override
	public Card userLogin(int cardId) {
		Optional<Card> options = cardDao.findById(cardId);
		if (options.isPresent())
			return options.get();
		return null;
	}

}
