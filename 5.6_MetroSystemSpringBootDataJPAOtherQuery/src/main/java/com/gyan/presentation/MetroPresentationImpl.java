package com.gyan.presentation;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gyan.beans.Card;
import com.gyan.service.CardService;
import com.gyan.service.StationService;


@Component
public class MetroPresentationImpl implements MetroPresentation {
	@Autowired
	private CardService cardService;
	@Autowired
	private StationService stationService;

	Scanner scanner = new Scanner(System.in);

	@Override
	public void showMenu() {
		System.out.println("1. Register new User");
		System.out.println("2. Log-in");
		System.out.println("3. Show all users");
		System.out.println("4. Add Stations");
		System.out.println("5. Exit");

	}

	@Override
	public void registerNewUser()  {
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
	public void showAllUserDetails()  {
		List<Card> cards = cardService.viewCardId();

		for (Card card : cards)
			System.out.println(card);
	}
	
	private void addStation() {
		System.out.println("Enter Station Name");
		String name = scanner.next();
		int stationNumber = stationService.addStation(name);
		System.out.println("Station added succesfully. Id : "+stationNumber);
	}

	@Override
	public void performMenu(int choice)  {
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
			this.addStation();
			break;
		case 5:
			System.out.println("Thanks for using our System, kindly visit us again!");
			break;
		default:
			System.out.println("Enter Valid Choice!");

		}

	}

}
