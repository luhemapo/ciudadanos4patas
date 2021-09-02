package co.edu.unbosque.model;

/**
 * Clase Pet que contiene los atributos de las mascotas 
 * @author Luis Martinez, Andres Carbonell
 *
 */   class Pet {
	 
	private String id;
	private long microchip;
	private String species;
	private String sex;
	private String size;
	private boolean potentDangerous;
	private String neighborhood;

	/**
	 * Constructor de la clase Pet   
	 */
	public Pet(String id, long microchip, String species, String sex, String size, boolean potentDangerous, String neighborhood) {
		this.id = id;
		this.microchip = microchip;
		this.species = species;
		this.sex = sex;
		this.size = size;
		this.potentDangerous = potentDangerous;
		this.neighborhood = neighborhood;

	}
	/**
	 * Metodo que obtiene el atributo Pet
	 * @return auxPet
	 */
	public String getPet() {
		String auxPet = "Id: "+getId() + "\nMicrochip: "+getMicrochip() + "\nSpecies: " + getSpecies() + "\nSex:"+ getSex() + "\nSize: " + getSize() + 
				"\nIs Potentially Dangerous?: "+getPotentDangerous()+"\nNeighborhood: "+ getNeighborhood()+"\n" ;
		return auxPet;
	}

	/**
	 * Metodo que obtiene el atributo Id
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Metodo que configura el atributo id
	 * @return id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Metodo que obtiene el atributo Microchip
	 * @return microchip
	 */
	public long getMicrochip() {
		return microchip;
	}
	/**
	 * Metodo que configura el atributo Microchip
	 * @return microchip
	 */
	public void setMicrochip(long microchip) {
		this.microchip = microchip;
	}
	/**
	 * Metodo que obtiene el atributo Species
	 * @return species
	 */
	public String getSpecies() {
		return species;
	}
	/**
	 * Metodo que configura el atributo Species
	 * @return species
	 */
	public void setSpecies(String species) {
		this.species = species;
	}
	/**
	 * Metodo que obtiene el atributo Pet
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * Metodo que configura el atributo Sex
	 * @return sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * Metodo que obtiene el atributo Size
	 * @return size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * Metodo que configura el atributo Size
	 * @return size
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * Metodo que obtiene el atributo PotentDangerous
	 * @return potentDangerous
	 */
	public boolean getPotentDangerous() {
		return potentDangerous;
	}
	/**
	 * Metodo que configura el atributo PotentDangerous
	 * @return potentDangerous
	 */
	public void setPotentDangerous(boolean potentDangerous) {
		this.potentDangerous = potentDangerous;
	}
	/**
	 * Metodo que obtiene el atributo Neighborhood
	 * @return neighborhood
	 */
	public String getNeighborhood() {
		return neighborhood;
	}
	/**
	 * Metodo que configura el atributo Neighborhood
	 * @return neighborhood
	 */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

}
