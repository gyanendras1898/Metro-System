package com.gyan.presentation;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gyan.beans.Station;
import com.gyan.service.CardService;
import com.gyan.service.JourneyService;
import com.gyan.service.StationService;

@Component
public class UserPresentationImpl implements UserPresentation {
	
	@Autowired
	private CardService cardService;
	@Autowired
	private JourneyService journeyService;
	@Autowired
	private StationService stationService;
	
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void showUserMenu(int cardId, boolean isJourneyOngoing) throws SQLException {
		System.out.println("1. View Balance");
		System.out.println("2. Add Balance");
		if (isJourneyOngoing)
			System.out.println("3. Swipe Out");
		else
			System.out.println("3. Swipe In");
		System.out.println("4. Exit");
	}

	@Override
	public void userLogin() throws SQLException {
		System.out.println("Enter Card Id");
		int cardId = scanner.nextInt();

		boolean cardPresent = cardService.isCardPresent(cardId);

		if (!cardPresent) {
			System.out.println("Card not registered or invalid card ID");
			return;
		}
		int choice;
		do{ 
			boolean isJourneyOngoing = journeyService.isJourneyOngoing(cardId);
			this.showUserMenu(cardId, isJourneyOngoing);
			System.out.println("Enter choice ");
			choice = scanner.nextInt();
			this.performUserMenu(choice, cardId, isJourneyOngoing);
			System.out.println();
		}while(choice != 4);
		
	}

	@Override
	public void performUserMenu(int choice, int cardId, boolean isJourneyOngoing)
			throws SQLException {
		switch (choice) {
		// view balance
		case 1:
			double currBalance = cardService.viewBalance(cardId);
			System.out.println("Your current balance is " + currBalance);
			break;

		// add balance
		case 2:
			System.out.println("Enter the amount");
			int amount = scanner.nextInt();

			if (amount <= 0)
				System.out.println("Enter valid amount");

			else {
				boolean amountUpdated = cardService.addCardBalance(cardId, amount);

				if (amountUpdated) {
					System.out.println("Amount added successfully");
					currBalance = cardService.viewBalance(cardId);
					System.out.println("Your current balance is " + currBalance);
				} else
					System.out.println("Amount not added: Please contact with admin");
			}
			break;
		// swipe in and out
		case 3:
			List<Station> stations = stationService.getStations();

			// swipe in feature
			if (!isJourneyOngoing) {
				currBalance = cardService.viewBalance(cardId);
				if (currBalance < 25) {
					System.out.println("Your current balance is " + currBalance);
					System.out.println("Please maintain min balance of 25 and try again");
				} else {
					for (Station station : stations)
						System.out.println(station);
					System.out.println("");

					System.out.println("Enter Source Station Number From Above Stations");
					int stationId = scanner.nextInt();
					int chance = 3;
					while (chance-- > 0 && (stationId <= 0 || stationId > stations.size())) {
						System.out.println("Enter valid station number");
						stationId = scanner.nextInt();
					}
					if (chance == 0) {
						System.out.println("Maximum limit reached, Please login again");
					}
					// logged in
					else {
						boolean swipedIn = journeyService.swipeIn(cardId, stationId);
						if (swipedIn)
							System.out.println("Boarded successfully");
						else
							System.out.println("Not Boarded, Please contact admin");
					}
				}
			}
			// swipe out feature
			else {
				for (Station station : stations)
					System.out.println(station);
				System.out.println("");

				System.out.println("Enter Destination Station Number From Above Stations");
				int stationId = scanner.nextInt();

				int chance = 3;
				while (chance-- > 0 && (stationId <= 0 || stationId > stations.size())) {
					System.out.println("Enter valid station number");
					stationId = scanner.nextInt();
				}
				if (chance == 0)
					System.out.println("Maximum limit reached, Please login again");

				// logged in
				else {
					boolean swipedOut = journeyService.swipeOut(cardId, stationId);

					if (swipedOut) {
						double fareValue = journeyService.getFare(cardId);
						currBalance = cardService.viewBalance(cardId);
						System.out.println("Your fare is " + fareValue + " and remaining balance is " + currBalance);

						boolean late = journeyService.getDuration(cardId);
						
						//fine
						if (late) {

							System.out.println("You are late and will be fined 100");

							if (currBalance < 100) {
								System.out.println("Your current balance is " + currBalance + ". Please add atleast "
										+ (100 - currBalance) + " rupees");
								System.out.println();

								amount = scanner.nextInt();
								choice = 3;
								while (choice-- > 0 && amount < 100 - currBalance) {
									System.out.println("Enter valid amount");
									amount = scanner.nextInt();
								}
								if (choice == 0)
									System.out.println("Amount not added, Please contact with admin");
								else
									cardService.addCardBalance(cardId, amount);
							}
							currBalance = cardService.viewBalance(cardId);
							if (currBalance >= 100) {
								boolean fine = cardService.addCardBalance(cardId, -100);

								if (fine) {
									double currtBalance = cardService.viewBalance(cardId);
									System.out.println("You are fined 100");
									System.out.println("Your current balance is " + currtBalance);
								} else
									System.out.println("Amount not deducted, Please contact with admin");
							}

							else {
								System.out.println("Balance low : Failed to pay fine");
								System.out.println("Card Blocked, Please contact with admin");
							}
						}
					}else System.out.println("Swipe Out failed, Please contact with admin");
				}
			}
			break;
		case 4:
			System.out.println("Thanks for using Metro Service");
			break;

		default:
			System.out.println("Enter Valid Choice!");

		}
	}
}
