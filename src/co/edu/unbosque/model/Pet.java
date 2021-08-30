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

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getMicrochip() {
		return microchip;
	}

	public void setMicrochip(long microchip) {
		this.microchip = microchip;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean getPotentDangerous() {
		return potentDangerous;
	}

	public void setPotentDangerous(boolean potentDangerous) {
		this.potentDangerous = potentDangerous;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

}
