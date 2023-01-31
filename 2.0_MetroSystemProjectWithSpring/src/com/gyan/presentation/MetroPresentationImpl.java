package com.gyan.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gyan.beans.Station;
import com.gyan.service.CardService;
import com.gyan.service.CardServiceImpl;
import com.gyan.service.JourneyService;
import com.gyan.service.JourneyServiceImpl;
import com.gyan.service.StationService;
import com.gyan.service.StationServiceImpl;

import lombok.Setter;

public class MetroPresentationImpl implements MetroPresentation {
	Scanner sc = new Scanner(System.in);
	@Setter
	private CardService cardService;
	@Setter
	private JourneyService journeyService;
	@Setter
	private StationService stationService;

	@Override
	public void showMenu() {
		System.out.println("1. Register new User");
		System.out.println("2. Log-in");
		System.out.println("3. Exit");

	}

	@Override
	public void performMenu(int choice) {
		try {
			switch (choice) {
			case 1:
				int chance = 3;
				double balance;
				do {
					System.out.println("Enter Initail Balance");
					balance = sc.nextDouble();

					if (balance < 100)
						System.out
								.println("Deposit Initial Balance of minimum 100 : " + (chance) + " remaining chance");

				} while (chance-- > 0 && balance < 100);

				if (balance < 100) {
					System.out.println("Thanks for using our System, kindly visit us again!");
					System.exit(0);
				}

				if (cardService.registerUser(balance))
					System.out.println("User get registered successfully");
				else
					System.out.println("Server Down");

				break;

			case 2:
				System.out.println("Enter Card Id");
				int cId = sc.nextInt();

				boolean cardPresent = cardService.isCardPresent(cId);

				if (cardPresent == false) {
					System.out.println("Card not registered or invalid card ID");
					break;
				}

				else {
					while (true) {
						System.out.println("1. View Balance");
						System.out.println("2. Add Balance");
						boolean isJourneyOngoing = journeyService.isJourneyOngoing(cId);
						if (isJourneyOngoing == false) {
							System.out.println("3. Swipe In");
						} else if (isJourneyOngoing == true) {
							System.out.println("3. Swipe Out");
						}
						System.out.println("4. Exit");

						System.out.println("Enter choice ");
						int ch = sc.nextInt();

						switch (ch) {

						case 1: // view balance
							try {
								double currBalance = cardService.viewBalance(cId);
								System.out.println("Your current balance is " + currBalance);

							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
							break;

						case 2: // add balance
							try {

								System.out.println("Enter the amount");
								int amount = sc.nextInt();

								if (amount <= 0) {
									System.out.println("Enter valid amount");
									break;
								}

								else {
									boolean amountUpdated = cardService.addCardBalance(cId, amount);
									System.out.println(amountUpdated);

									if (amountUpdated) {
										System.out.println("Amount added successfully");
										double currBalance = cardService.viewBalance(cId);
										System.out.println("Your current balance is " + currBalance);
									} else {
										System.out.println("Amount not added");
									}
								}
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
							break;

						case 3: // swipe in and out
							try {

								if (isJourneyOngoing == false) {

									double currBalance = cardService.viewBalance(cId);
									if (currBalance < 25) {
										System.out.println("Your current balance is " + currBalance);
										System.out.println("Please maintain min balance of 25 and try again");
										break;
									}

									else {

										List<Station> stations = stationService.getStations();
										for (Station s : stations) {
											System.out.print(s + "\n");
										}
										System.out.println("");

										System.out.println("Enter Source Station Number From Above Stations");
										int sId = sc.nextInt();

										if (sId <= 0 || sId > 5) {
											System.out.println("Enter valid station number");
											sId = sc.nextInt();
										}

										else {
											boolean swipedIn = journeyService.swipeIn(cId, sId);

											if (swipedIn) {
												System.out.println("Boarded successfully");

											} else {
												System.out.println("Not Boarded");
											}
										}

									}
								}

								if (isJourneyOngoing == true) {

									List<Station> stations = stationService.getStations();
									for (Station s : stations) {
										System.out.print(s + "\t");
									}
									System.out.println("");

									System.out.println("Enter Destination Station Number From Above Stations");
									int dId = sc.nextInt();

									if (dId <= 0 || dId > 5) {
										System.out.println("Enter valid station number");
										dId = sc.nextInt();
									}

									else {
										boolean swipedOut = journeyService.swipeOut(cId, dId);

										if (swipedOut) {
											int fareValue = journeyService.getFare(cId);
											double currBalance = cardService.viewBalance(cId);
											System.out.println("");
											System.out.println("Your fare is " + fareValue
													+ " and remaining balance is " + currBalance);

											boolean late = journeyService.getDuration(cId);

											// System.out.println("Are you late ==== "+late);

											if (late == true) {

												System.out.println("You are late and will be fined 100");

												if (currBalance < 100) {
													System.out.println("Your current balance is " + currBalance
															+ ". Please add atleast " + (100 - currBalance)
															+ " rupees");
													System.out.println("");

													int amount = sc.nextInt();
													while (amount < 100 - currBalance) {
														System.out.println("Enter valid amount");
														amount = sc.nextInt();
													}
													boolean balanceUpdated = cardService.addCardBalance(cId, amount);
													boolean fine = cardService.addCardBalance(cId, -100);

													if (fine) {
														double currtBalance = cardService.viewBalance(cId);
														System.out.println("You are fined 100");
														System.out.println("Your current balance is " + currtBalance);
													} else {
														System.out.println("Amount not added");
													}

													break;

												}

												else if (currBalance >= 100) {
													boolean fine = cardService.addCardBalance(cId, -100);

													if (fine) {
														double currtBalance = cardService.viewBalance(cId);
														System.out.println("You are fined 100");
														System.out.println("Your current balance is " + currtBalance);
													} else {
														System.out.println("Amount not added");
													}
												}

											} else {
												System.out.println("Now swiped out");
											}
										}
									}
								}
							}

							catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
							break;

						case 4:
							System.out.println("Thanks for using Metro Service");
							System.exit(0);

						default:
							System.out.println("Enter Valid Choice!");
						}
					}
				}

			case 3:
				System.out.println("Thanks for using our System, kindly visit us again!");
				System.exit(0);
			default:
				System.out.println("Enter Valid Choice!");

			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
