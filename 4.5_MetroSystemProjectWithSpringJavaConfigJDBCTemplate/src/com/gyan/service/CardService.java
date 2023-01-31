package com.gyan.service;

import java.sql.SQLException;

import java.util.List;

import com.gyan.beans.Card;

public interface CardService {
	int registerUser(double balance) throws SQLException;
	boolean isCardPresent(int cardId) throws SQLException;
	boolean addCardBalance(int cardId, double balance)throws SQLException;
	double viewBalance(int cardId)throws SQLException;
	List<Card> viewCardId()throws SQLException;
	
}
