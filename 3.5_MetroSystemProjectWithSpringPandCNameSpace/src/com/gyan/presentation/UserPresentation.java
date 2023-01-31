package com.gyan.presentation;

import java.sql.SQLException;

public interface UserPresentation {
	public void showUserMenu(int cardId, boolean isJourneyOngoing) throws ClassNotFoundException, SQLException;
	public void userLogin() throws ClassNotFoundException, SQLException;
	public void performUserMenu(int choice, int cardId, boolean isJourneyOngoing) throws ClassNotFoundException, SQLException;
}
