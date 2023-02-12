package com.gyan.presentation;

import java.sql.SQLException;

public interface MetroPresentation {

	void showMenu();

	void performMenu(int choice) throws SQLException;

	void registerNewUser() throws SQLException;

	void showAllUserDetails()throws SQLException;

}
