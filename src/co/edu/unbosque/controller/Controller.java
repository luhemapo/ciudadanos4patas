package co.edu.unbosque.controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

import co.edu.unbosque.model.Manager;
import co.edu.unbosque.model.Pet;

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

		while (continueMenu.equalsIgnoreCase("y")) {
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
			String option = sc.next();
			
			switch (option) {
			case "1":
				System.out.println("Please type the microchip number you´d like to search: ");
				String aux = sc.next();
				try {
					Long mc = Long.parseLong(aux);
					Pet p = m.findByMicrochip(mc);
					if(p == null) {
						System.out.println("Pet not found");
					}else {
						System.out.println("Id: "+p.getId());
						System.out.println("Microchip: "+p.getMicrochip());
						System.out.println("Species: "+p.getSpecies());
						System.out.println("Sex: "+p.getSex());
						System.out.println("Size: "+p.getSize());
						System.out.println("Dangerous: "+p.getPotentDangerous());
						System.out.println("neighborhood: "+p.getNeighborhood());
					}
				}catch(Exception e) {
					System.out.println("INVALID Microchip");
				}
				break;
		}
	}
}
}