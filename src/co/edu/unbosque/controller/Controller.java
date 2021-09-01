package co.edu.unbosque.controller;

import java.io.FileNotFoundException;

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
		
	}
}
