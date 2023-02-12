package com.gyan.presentation;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gyan.beans.Card;
import com.gyan.service.CardService;


@Component
public class MetroPresentationImpl implements MetroPresentation {
	@Autowired
	private CardService cardService;

	Scanner scanner = new Scanner(System.in);

	@Override
	public void showMenu() {
		System.out.println("1. Register new User");
		System.out.println("2. Log-in");
		System.out.println("3. Show all users");
		System.out.println("4. Exit");

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
	public void showAllUserDetails() throws SQLException {
		List<Card> cards = cardService.viewCardId();

		for (Card card : cards)
			System.out.println(card);
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

		case 3:
			showAllUserDetails();
			break;

		// exit
		case 4:
			System.out.println("Thanks for using our System, kindly visit us again!");
			break;
		default:
			System.out.println("Enter Valid Choice!");

		}

	}

}
