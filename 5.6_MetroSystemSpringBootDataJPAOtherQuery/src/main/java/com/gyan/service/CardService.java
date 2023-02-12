package com.gyan.service;

import java.util.List;

import com.gyan.beans.Card;

public interface CardService {
	int registerUser(double balance) ;
	boolean isCardPresent(int cardId) ;
	boolean addCardBalance(int cardId, double balance) ;
	double viewBalance(int cardId) ;
	List<Card> viewCardId() ;
	
}
