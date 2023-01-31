package com.gyan.client;

import java.util.Scanner;

import com.gyan.exceptions.InvalidInputException;
import com.gyan.presentation.MetroPresentation;
import com.gyan.presentation.MetroPresentationImpl;


public class MetroProjectClient {
	
	private static MetroPresentation metroPresentation=new MetroPresentationImpl();

	public static void main(String[] args) {
		

		Scanner sc=new Scanner(System.in);
		while(true) {
			try {
			metroPresentation.showMenu();
			System.out.println("Enter Choice : ");
			String choice=sc.next();
			if((int)choice.charAt(0)-48>=0 && (int)choice.charAt(0)-48<=9) {
				metroPresentation.performMenu(Integer.parseInt(choice));
				System.out.println();
			}		
			else
				throw new InvalidInputException("Warning!!! Please input integer only");
			}
			catch(InvalidInputException exception) {
				System.out.println(exception.getMessage());
			}
		}

	}

}
