package com.gyan.persistence;

import java.sql.SQLException;
import java.util.List;

import com.gyan.beans.Card;

public interface CardDao {
	int addCard(double balance) throws ClassNotFoundException, SQLException;
	double viewBalance(int cardId) throws ClassNotFoundException, SQLException;
	boolean addBalance(int cardId, double balance)throws ClassNotFoundException, SQLException;
	boolean isCardPresent(int cardId) throws SQLException, ClassNotFoundException;
	List<Card> allCard()throws ClassNotFoundException, SQLException;
	int lastCardAdded() throws ClassNotFoundException, SQLException;
	
}
