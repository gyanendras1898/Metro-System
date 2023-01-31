package com.gyan.client;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gyan.config.MetroConfiguration;
import com.gyan.exceptions.InvalidInputException;
import com.gyan.presentation.MetroPresentation;
import com.gyan.presentation.UserPresentation;

public class MetroProjectClient {

	private static ApplicationContext springContainer;

	public static void main(String[] args) {

		springContainer = new AnnotationConfigApplicationContext(MetroConfiguration.class);
		
		MetroPresentation metroPresentation = (MetroPresentation) springContainer.getBean("metroPresentation");
		UserPresentation userPresentation = (UserPresentation) springContainer.getBean("userPresentation");
		
		int choices = 4;
		int userChoice = 0;

		try(Scanner scanner = new Scanner(System.in)) {
			do {
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
					throw (InvalidInputException)springContainer.getBean("invalidInputException");
			} while (userChoice != choices);
		} catch (InvalidInputException exception) {
			System.out.println(exception.getMessage());
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
