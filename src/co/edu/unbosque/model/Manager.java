package co.edu.unbosque.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class Manager {

	private FileReader archCSV = null;
	private CSVReader csvReader = null;
	private String[] header = new String [7];
	private ArrayList <Pet> pets;
	
	public  Manager() throws FileNotFoundException {
		archCSV = new FileReader("data/pets-citizens.csv");
		
	}
}
