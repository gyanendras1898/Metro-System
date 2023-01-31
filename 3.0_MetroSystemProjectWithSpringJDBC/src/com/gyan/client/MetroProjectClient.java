package com.gyan.client;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gyan.exceptions.InvalidInputException;
import com.gyan.presentation.MetroPresentation;
import com.gyan.presentation.MetroPresentationImpl;
import com.gyan.presentation.UserPresentation;


public class MetroProjectClient {
	
	private static MetroPresentation metroPresentation;
	private static UserPresentation userPresentation;

	public static void main(String[] args) {
		
		ApplicationContext springContainer = new ClassPathXmlApplicationContext("metroSystem.xml");
		
		metroPresentation = (MetroPresentation) springContainer.getBean("metroPresentation");
		userPresentation = (UserPresentation) springContainer.getBean("userPresentation");

		Scanner sc=new Scanner(System.in);
		while(true) {
			try {
			metroPresentation.showMenu();
			System.out.println("Enter Choice : ");
			String choice=sc.next();
			if((int)choice.charAt(0)-48>=0 && (int)choice.charAt(0)-48<=9) {
				int choiceInt = Integer.parseInt(choice);
				if(choiceInt == 2)	userPresentation.userLogin();
				else metroPresentation.performMenu(choiceInt);
				System.out.println();
			}		
			else
				throw new InvalidInputException("Warning!!! Please input integer only");
			}
			catch(InvalidInputException exception) {
				System.out.println(exception.getMessage());
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
