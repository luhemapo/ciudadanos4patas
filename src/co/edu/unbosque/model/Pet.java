package co.edu.unbosque.model;

public class Pet {
	private String id;
	private long microchip;
	private String species;
	private String sex;
	private String size;
	private boolean potentDangerous;
	private String neighborhood;

	public Pet(String id, long microchip, String species, String sex, String size, boolean potentDangerous, String neighborhood) {
		this.id = id;
		this.microchip = microchip;
		this.species = species;
		this.sex = sex;
		this.size = size;
		this.potentDangerous = potentDangerous;
		this.neighborhood = neighborhood;

	}

	public String getPet() {
		String auxPet = "Id: "+getId() + "\nMicrochip: "+getMicrochip() + "\nSpecies: " + getSpecies() + "\nSex:"+ getSex() + "\nSize: " + getSize() + 
				"\nIs Potentially Dangerous?: "+getPotentDangerous()+"\nNeighborhood: "+ getNeighborhood()+"\n" ;
		return auxPet;
	}

