package co.edu.unbosque.controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

import co.edu.unbosque.model.Manager;

public class Controller {
	Manager m;

	public Controller() {
		try {

			m = new Manager();
			m.uploadData();
			System.out.println("Assigning Ids...");
			m.assignID();

			// showPetsInfo();
			menu();

		} catch (FileNotFoundException e) {
			System.out.println("No encontré el archivo");
			e.printStackTrace();
		}
	}
	public void menu() {
		String continueMenu = "Y";
		System.out.println("-------------------------------------");
		System.out.println("      WELCOME TO PETS MANAGER");
		
		while(continueMenu.equalsIgnoreCase("y")) {
			System.out.println("-------------------------------------");
			System.out.println("              MENU");
			System.out.println("-------------------------------------");
			System.out.println("Please type the option number:");
			System.out.println("1.Find Pet By Microchip.");
			System.out.println("2.Count Pet By Species.");
			System.out.println("3.Count Pet By Neighborhood.");
			System.out.println("4.Find by multiple fields.");
			System.out.println("5.Exit.");
			Scanner sc = new Scanner(System.in);
			String option=sc.next();
	}
}
