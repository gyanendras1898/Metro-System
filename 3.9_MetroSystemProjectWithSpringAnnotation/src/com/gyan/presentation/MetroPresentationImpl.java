package com.gyan.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gyan.beans.Station;
import com.gyan.service.CardService;
import com.gyan.service.CardServiceImpl;
import com.gyan.service.JourneyService;
import com.gyan.service.JourneyServiceImpl;
import com.gyan.service.StationService;
import com.gyan.service.StationServiceImpl;

import lombok.Setter;

@Component
public class MetroPresentationImpl implements MetroPresentation {
	@Autowired
	private CardService cardService;
	
	Scanner scanner = new Scanner(System.in);

	@Override
	public void showMenu() {
		System.out.println("1. Register new User");
		System.out.println("2. Log-in");
		System.out.println("3. Exit");

	}

	@Override
	public void registerNewUser() throws SQLException {
		int userId;
		int chance = 3;
		double balance;
		do {
			System.out.println("Enter Initail Balance");
			balance = scanner.nextDouble();

			if (balance < 100)
				System.out.println("Deposit Initial Balance of minimum 100 : " + (chance) + " remaining chance");

		} while (chance-- > 0 && balance < 100);

		if (balance < 100)
			System.out.println("Thanks for using our System, kindly visit us again!");
		else {
			userId = cardService.registerUser(balance);
			System.out.println("User Registered Successfully. Card id : " + userId);
		}
	}

	@Override
	public void performMenu(int choice) throws SQLException {
		switch (choice) {
		// register new user
		case 1:
			this.registerNewUser();
			break;
		// login
		case 2:
			/*
			 * handle in UserPresentationImpl class
			 */
			break;

		// exit
		case 3:
			System.out.println("Thanks for using our System, kindly visit us again!");
			break;
		default:
			System.out.println("Enter Valid Choice!");

		}

	}

}
