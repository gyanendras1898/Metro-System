package com.gyan.presentation;


public interface UserPresentation {
	public void showUserMenu(int cardId, boolean isJourneyOngoing) ;
	public void userLogin() ;
	public void performUserMenu(int choice, int cardId, boolean isJourneyOngoing) ;
}
