package co.edu.unbosque.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.unbosque.model.Manager;
import co.edu.unbosque.model.Pet;

/**
 * Clase Controller que controla el funcionamiento del programa
 * @author Luis Martinez, Andres Carbonell
 *@serial 2021
 *@version 5.0
 */
public class Controller {
	Manager m; // Atributo que renombra la clase Manager

	/**
	 * Constructor de la clase controller
	 */
	public Controller() {
		try {

			m = new Manager();
			m.uploadData();
			System.out.println("Assigning Ids...");
			m.assignID();

			// showPetsInfo();
			menu();

		} catch (FileNotFoundException e) {
			System.out.println("No encontr� el archivo");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo de tipo void que muestra el menu y las diferentes opciones
	 */
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
				System.out.println("Please type the microchip number you�d like to search: ");
				String aux = sc.next();
				try {
					Long mc = Long.parseLong(aux);
					Pet p = m.findByMicrochip(mc);
					if (p == null) {
						System.out.println("Pet not found");
					} else {
						System.out.println("Id: " + p.getId());
						System.out.println("Microchip: " + p.getMicrochip());
						System.out.println("Species: " + p.getSpecies());
						System.out.println("Sex: " + p.getSex());
						System.out.println("Size: " + p.getSize());
						System.out.println("Dangerous: " + p.getPotentDangerous());
						System.out.println("neighborhood: " + p.getNeighborhood());
					}
				} catch (Exception e) {
					System.out.println("INVALID Microchip");
				}
				break;

			case "2":
				System.out.println("Please type the species number you�d like to search: \n Type 1.Canino 2.Felino");
				int noSpecies = sc.nextInt();
				String species = "";
				if (noSpecies == 1) {
					species = "CANINO";
				} else if (noSpecies == 2) {
					species = "FELINO";
				} else {
					System.out.println("Invalid Option");
				}
				System.out.println(species + ": " + m.countBySpecies(species));
				break;

			case "3":
				System.out.println("Please type the pet's neighborhood you�d like to search: ");
				String neib = sc.next();
				int contN = m.countByNeighborhood(neib);
				if (contN == 0) {
					System.out.println("There's no pets found in: " + neib);
				} else {
					System.out.println("There's " + contN + " pets found in: " + neib);
				}
				break;
			case "4":
				System.out.println("Please type search parameters: ");
				System.out.println("If you donn't want to use a certain parameter please type 'N' \n");
				System.out.println("Number of showed pets:");
				try {
					String n = sc.next();
					System.out.println("Pet's Position (Top/Last):");
					String position = sc.next();
					System.out.println("Pet's Species (Canino/Felino):");
					String speciesF = sc.next();
					System.out.println("Pet's Sex (Macho/Hembra)");
					String sex = sc.next();
					System.out.println("Pet's Size:(Miniatura/Peque�o/Mediano/Grande/Gigante)");
					String size = sc.next();
					System.out.println("Pet's Potential Dangerous (T/F):");
					String pDangerous = sc.next();
					System.out.println("Pet's Neighborhood:");
					String neibF = sc.next();

					ArrayList<Pet> petsF = new ArrayList<Pet>();

					petsF = m.findByMultipleFields(n, position, speciesF, sex, size, pDangerous, neibF);

					for (int i = 0; i < petsF.size(); i++) {
						System.out.println("\n" + petsF.get(i).getPet());
					}
					if (!n.equalsIgnoreCase("n")) {
						System.out.println("Show next " + n + " pets?\nType Y/N");
						String next = sc.next();
						int numb = Integer.parseInt(n);
						int num = Integer.parseInt(n);
						while (next.equalsIgnoreCase("y")) {

							petsF = m.findByMultipleFields(num + numb + "", position, speciesF, sex, size, pDangerous,
									neibF);
							if (petsF.size() - num >= 0) {
								for (int i = num; i < petsF.size(); i++) {
									System.out.println("\n" + petsF.get(i).getPet());
								}
								num = num + numb;
							} else {
								System.out.println("There�s no registers left");
							}
							System.out.println("Show next " + n + " pets?\nType Y/N");
							next = sc.next();
						}
					}

					break;

				} catch (Exception e) {
					System.out.println("Invalid Field");
					e.printStackTrace();
				}
			case "5":
				continueMenu = "n";
				break;

			default:
				System.out.println("Please tipe a valid menu number");
				System.out.println("Would you like to perform another action? \nType Y/N");
				continueMenu = sc.next();
			}

		}
		System.out.println("-------------------------------------");
		System.out.println("      Have a nice day =)");

	}

}