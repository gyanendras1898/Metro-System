package com.gyan.persistence;

import java.sql.SQLException;

import java.util.List;
import com.gyan.beans.Card;

public interface CardDao {
	boolean addCard(double balance) throws SQLException;
	double viewBalance(int cardId) throws SQLException;
	boolean addBalance(int cardId, double balance)throws SQLException;
	boolean isCardPresent(int cardId) throws SQLException;
	List<Card> allCard()throws SQLException;
	int lastCardAdded() throws SQLException;
	
}
