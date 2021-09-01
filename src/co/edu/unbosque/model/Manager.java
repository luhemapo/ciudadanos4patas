package co.edu.unbosque.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Manager {

	private FileReader archCSV = null;
	private CSVReader csvReader = null;
	private String[] header = new String [7];
	private ArrayList <Pet> pets;
	
	public  Manager() throws FileNotFoundException {
		archCSV = new FileReader("data/pets-citizens.csv");
		CSVParser separator = new CSVParserBuilder().withSeparator(';').build();
		csvReader = new CSVReaderBuilder(archCSV).withCSVParser(separator).build();
		
		pets = new ArrayList <Pet>();
	}
	public void uploadData() {
		try {

			String[] row = null;
			row = csvReader.readNext();
			header[1]=row[0];//capture microchip
			header[2]=row[1];//capture specie
			header[3]=row[2];//capture sex
			header[4]=row[3];//capture size
			header[5]=row[4];//capture dangerous
			header[6]=row[5];//capture neib

			while((row = csvReader.readNext()) != null) {        	  
				if(formatValidation(row) == true) {
					boolean dangTemp=true;
					if(row[4].equalsIgnoreCase("NO")) {
						dangTemp=false;
					}
					Pet petTemp = new Pet("",Long.parseLong(row[0]),row[1],row[2],row[3],dangTemp,row[5]);
					pets.add(petTemp);
				}

			}

			System.out.println(pets.size()+" registers were charged");
			System.out.println("Loading process ended");
			System.out.println("**************************");
		}
		catch(Exception e) {
			System.out.println("An error ocurred");
			System.out.println(e);
		}
	}
	
	public boolean formatValidation (String[]pRow) {
		boolean ans=true;
		for(int i=0;i<6;i++) {
			if(pRow[i].isEmpty()) {
				System.err.println("EmptyAttributeException");
				ans=false;
			}
		}

		try {
			long pMicrochip = Long.parseLong(pRow[0]);
		}catch (NumberFormatException e) {
			System.err.println(e);
			ans=false;
		}

		return ans;
	}

	public String idGenerator(int pos, int n) {
		String psize="";
		String pdang="";
		String generated="";
		if(pets.get(pos).getSize().equalsIgnoreCase("miniatura")) {
			psize="MI";
		}else
		{
			psize = pets.get(pos).getSize().substring(0,1);
		}

		if(pets.get(pos).getPotentDangerous()== true) {
			pdang="T";
		}else
		{
			pdang="F";
		}

		generated=generated + getNoMicrochip(pos,n) 
		+"-"+pets.get(pos).getSpecies().substring(0,1) +
		pets.get(pos).getSex().substring(0,1) + psize + pdang;

		return generated;
	}

	public String getNoMicrochip(int actual, int n) {
		String mc=""+pets.get(actual).getMicrochip();
		mc = mc.substring(mc.length() - n); //obtener los n ultimos digitos del microchip
		return mc;
	}
	public void showPetsInfo() {
		for(int i=0;i<pets.size();i++) {
			System.out.println("Id: "+ pets.get(i).getId());
		}
	}

	public void assignID() {
		int n=2;
		for(int i=0;i<pets.size();i++) {
			String generated = idGenerator(i,n);
			pets.get(i).setId(generated);
		}
		idTest();
		System.out.println("Ids Assigned");
	}
	
	public void idTest() {
		for(int i =0;i<pets.size();i++) {
			for(int j=i+1;j<pets.size();j++) {
				if(pets.get(i).getId().equals(pets.get(j).getId())) {
					int n = pets.get(j).getId().split("-")[0].length();
					String generated = idGenerator(j,n+1);
					pets.get(j).setId(generated);
				}
			}
		}
	}

	public Pet findByMicrochip(Long microchip) {
		for(int i=0; i<pets.size();i++) {
			if(pets.get(i).getMicrochip() == microchip) {
				System.out.println("Pet fund!");
				return pets.get(i);
			}
		}
		return null;
	}

}
