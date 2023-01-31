package com.gyan.client;

import java.sql.SQLException;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gyan.exceptions.InvalidInputException;
import com.gyan.presentation.MetroPresentation;
import com.gyan.presentation.UserPresentation;

public class MetroProjectClient {

	private static ApplicationContext springContainer;

	public static void main(String[] args) {

		springContainer = new ClassPathXmlApplicationContext("metroSystem.xml");

		MetroPresentation metroPresentation = (MetroPresentation) springContainer.getBean("metroPresentationImpl");
		UserPresentation userPresentation = (UserPresentation) springContainer.getBean("userPresentationImpl");

		int choiceInt = 0;

		try(Scanner scanner = new Scanner(System.in)) {
			do {
				metroPresentation.showMenu();
				System.out.println("Enter Choice : ");
				String choice = scanner.next();
				if ((int) choice.charAt(0) - 48 >= 0 && (int) choice.charAt(0) - 48 <= 9) {
					choiceInt = Integer.parseInt(choice);
					if (choiceInt == 2)
						userPresentation.userLogin();
					else
						metroPresentation.performMenu(choiceInt);
					System.out.println();
				} else
					throw new InvalidInputException("Warning!!! Please input integer only");
			} while (choiceInt != 3);
		} catch (InvalidInputException exception) {
			System.out.println(exception.getMessage());
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}

	}

}
