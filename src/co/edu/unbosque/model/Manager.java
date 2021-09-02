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
	/**
	 * Metodo de tipo void utilizado por la libreria "opencsv" realiza la lectura del archivo csv 
	 */
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
	/**
	 * Metodo de tipo boolean que realiza la validacion del formato del microchip
	 * @return ans
	 */
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
	/**
	 * Metodo de tipo String que genera el ID
	 * @return generated
	 */

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
	/**
	 * Metodo de tipo String que obtiene los n ultimos digitos del microchip
	 * @return mc
	 */

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
	/**
	 * Metodo de tipo void que asigna el ID
	 */
	public void assignID() {
		int n=2;
		for(int i=0;i<pets.size();i++) {
			String generated = idGenerator(i,n);
			pets.get(i).setId(generated);
		}
		idTest();
		System.out.println("Ids Assigned");
	}
	/**
	 * Metodo de tipo void que verifica que el ID no sea repetido
	 */
	
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
	/**
	 * Metodo de tipo Pet que busca una mascota utilizando el numero de microchip
	 *@param microchip
	 * @return null
	 */
	public Pet findByMicrochip(Long microchip) {
		for(int i=0; i<pets.size();i++) {
			if(pets.get(i).getMicrochip() == microchip) {
				System.out.println("Pet fund!");
				return pets.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Metodo de tipo int que cuenta las mascotas por especie
	 *@param species
	 * @return cont
	 */
	public int countBySpecies(String species) {
		int cont = 0;
		for(int i=0; i<pets.size();i++) {
			if(pets.get(i).getSpecies().equalsIgnoreCase(species)) {
				cont++;
			}
		}
		
		return cont;
	}
	/**
	 * Metodo de tipo int que cuenta las mascotas por barrio de residencia
	 *@param neib
	 * @return cont
	 */

	public int countByNeighborhood(String neib) {
		int cont = 0;
		for(int i=0; i<pets.size();i++) {
			if(pets.get(i).getNeighborhood().equalsIgnoreCase(neib)) {
				cont++;
			}
		}
		
		return cont;
	}
	/**
	 * Metodo de ArrayList<Pet> que se encarga de buscar una mascota con diferentes parametros de busqueda
	 *@param sn, position, species, sex, size, pd, neib
	 * @return ans
	 */

	public ArrayList<Pet> findByMultipleFields(String sn, String position, String species, String sex,
			String size, String pd, String neib) {
		
		ArrayList <Pet> ans= new ArrayList<Pet>();
		if (!species.equalsIgnoreCase("n")) {
			ans=findBySpecies(species);
		}
		
		if (!sex.equalsIgnoreCase("n")) {
			if(!ans.isEmpty()) {
				ans=findBySex(sex, ans) ;
			}else {
				ans=findBySex(sex, pets) ;
			}
		}
		
		if (!size.equalsIgnoreCase("n")) {
			if(!ans.isEmpty()) {
				ans=findBySize(size, ans) ;
			}else {
				ans=findBySize(size, pets) ;
			}
		}
		
		if (!pd.equalsIgnoreCase("n")) {
			if(!ans.isEmpty()) {
				ans=findByPD(pd, ans) ;
			}else {
				ans=findByPD(pd, pets) ;
			}
		}
		
		if (!neib.equalsIgnoreCase("n")) {
			if(!ans.isEmpty()) {
				ans=findByNB(neib, ans) ;
			}else {
				ans=findByNB(neib, pets) ;
			}
		}
		
		
		
		if (!position.equalsIgnoreCase("n")) {
			if(!ans.isEmpty()) {
				ans=findByPosition(position, ans) ;
			}else {
				ans=findByPosition(position, pets) ;
			}
		}
		
		if (!sn.equalsIgnoreCase("n")) {
			int n = Integer.parseInt(sn);
			if(!ans.isEmpty()) {
				ans=findByN(n, ans) ;
			}else {
				ans=findByN(n, pets) ;
			}
		}
		
		
		return ans;
	}
	/**
	 * Metodo de ArrayList<Pet> que se encarga de buscar mascotas por especie
	 *@param species
	 * @return ans
	 */

	public ArrayList<Pet> findBySpecies(String species ) {
		ArrayList <Pet> ans= new ArrayList<Pet>();
		for (int i = 0; i < pets.size(); i++) {
			if (pets.get(i).getSpecies().equalsIgnoreCase(species)) {
				ans.add(pets.get(i));
			}
		}
		return ans;
	}
	/**
	 * Metodo de ArrayList<Pet> que se encarga de buscar mascotas por sexo
	 *@param sex, ans
	 * @return ansS
	 */
	public ArrayList<Pet> findBySex(String sex, ArrayList <Pet> ans) {
		ArrayList <Pet> ansS= new ArrayList<Pet>();
		for (int i = 0; i < ans.size(); i++) {
			if (ans.get(i).getSex().equalsIgnoreCase(sex)) {
				ansS.add(ans.get(i));
			}
		}
		return ansS;
	}
	
	/**
	 * Metodo de ArrayList<Pet> que se encarga de buscar mascotas por tamaño
	 *@param size, ans
	 * @return ansS
	 */
	public ArrayList<Pet> findBySize(String size, ArrayList <Pet> ans){
		ArrayList <Pet> ansS= new ArrayList<Pet>();
		if(size.equalsIgnoreCase("gigante")) {
			size="Muy Grande";
		}
		for (int i = 0; i < ans.size(); i++) {
			if (ans.get(i).getSize().equalsIgnoreCase(size)) {
				ansS.add(ans.get(i));
			}
		}
		return ansS;
	}

	/**
	 * Metodo de ArrayList<Pet> que se encarga de buscar mascotas por el atributo potentially dangerous
	 *@param pd, ans
	 * @return ansS
	 */
	public ArrayList<Pet> findByPD(String pd, ArrayList <Pet> ans){
		ArrayList <Pet> ansS= new ArrayList<Pet>();
		boolean pdbool=false;
		if(pd.equalsIgnoreCase("t")) {
			pdbool=true;
		}
		for (int i = 0; i < ans.size(); i++) {
			if (ans.get(i).getPotentDangerous() == pdbool) {
				ansS.add(ans.get(i));
			}
		}
		return ansS;
	}
	
	/**
	 * Metodo de ArrayList<Pet> que se encarga de buscar mascotas por barrio de residencia
	 *@param neib, ans
	 * @return ansS
	 */
	public ArrayList<Pet> findByNB(String neib, ArrayList <Pet> ans ){
		ArrayList <Pet> ansS= new ArrayList<Pet>();
		for (int i = 0; i < ans.size(); i++) {
			if(ans.get(i).getNeighborhood().equalsIgnoreCase(neib)) {
				ansS.add(ans.get(i));
			}
		}
		return ansS;
	}
	
	/**
	 * Metodo de ArrayList<Pet> que se encarga de mostrar un numero especifico de mascotas
	 *@param n, ans
	 * @return ansS
	 */
public ArrayList<Pet> findByN(int n, ArrayList <Pet> ans ){
		
		ArrayList <Pet> ansS= new ArrayList<Pet>();
		if(n>ans.size()) {
			for (int i = 0; i < ans.size(); i++) {
				ansS.add(ans.get(i));
			}
		}else {
			for (int i = 0; i < n; i++) {
				ansS.add(ans.get(i));
			}
		}

		return ansS;
	}
/**
 * Metodo de ArrayList<Pet> que se encarga de ordenar mascotas de manera ascendente o descendente
 *@param position, ans
 * @return ansS
 */
	public ArrayList<Pet> findByPosition(String position, ArrayList <Pet> ans ){
		ArrayList <Pet> ansS= new ArrayList<Pet>();
		if(position.equalsIgnoreCase("last")) {
			for (int i = ans.size()-1; i>=0 ; i--) {
				ansS.add(ans.get(i));
			}
		}else {
			for (int i = 0; i < ans.size(); i++) {
				ansS.add(ans.get(i));
			}
		}

		return ansS;	
	}
	

}
