package com.gyan.client;

import java.sql.SQLException;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gyan.exceptions.InvalidInputException;
import com.gyan.presentation.MetroPresentation;

import com.gyan.presentation.UserPresentation;

@SpringBootApplication(scanBasePackages = "com.gyan")
public class Application implements CommandLineRunner {

	@Autowired
	private MetroPresentation metroPresentation;
	@Autowired
	private UserPresentation userPresentation;
	@Autowired
	private InvalidInputException invalidInputException;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		int choices = 4;
		int userChoice = 0;
		Scanner scanner = new Scanner(System.in);
		do {
			try {

				metroPresentation.showMenu();
				System.out.println("Enter Choice : ");
				String choice = scanner.next();
				if ((int) choice.charAt(0) - 48 >= 0 && (int) choice.charAt(0) - 48 <= 9) {
					userChoice = Integer.parseInt(choice);
					if (userChoice == 2)
						userPresentation.userLogin();
					else
						metroPresentation.performMenu(userChoice);
					System.out.println("***************");
				} else
					throw invalidInputException;

			} catch (InvalidInputException exception) {
				System.out.println(exception.getMessage());
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (userChoice != choices);
		
		scanner.close();

	}

}
