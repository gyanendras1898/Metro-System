package com.gyan.model.service;

import java.util.List;

import com.gyan.beans.Card;

public interface CardService {
	int registerUser(String userName,double balance) ;
	Card userLogin(int cardId) ;
	boolean addCardBalance(int cardId, double balance) ;
	double viewBalance(int cardId) ;
	List<Card> viewCardId() ;
	
}
