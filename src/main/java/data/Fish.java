package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


// ENTITY ANNOTAATI TARVITAAN TÄHÄN JA VIITTAUS LUOKKAAN PERSISTANCE TIEDOSTOON
@Entity
public class Fish {
	
	// TÄMÄ KERTOO ETTÄ ID ON TIETOKANNAN PÄÄAVAIN
	@Id
	// TÄMÄ KERTOO ETTÄ ID ON AUTOINCREMENTTI
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	
	// KUN KALA LUODAAN, SILLE ANNETAAN ROTU JA PAINO, MUTTA ID TULEE AUTOMAATTISESTI
	private String breed;
	private float weight;
	
	
	// TYHJÄ MUODOSTIN ON OLTAVA, ETTÄ OHJELMA TOIMII!!!
	public Fish() {
		
	}
	public Fish(String breed, float weight) {
		this.breed=breed;
		this.weight=weight;
	}
	public Fish(int id, String breed, float weight) {
		this.id=id;
		this.breed=breed;
		this.weight=weight;
	}
	public Fish(String breed, String weight) {
		this.breed=breed;
		this.setWeight(weight);
	}
	public Fish(String id, String breed, String weight) {
		this.setId(id);
		this.breed=breed;
		this.setWeight(weight);
	}
	
	// GETTERIT JA SETTERIT ON OLTAVA, ETTÄ OHJELMA TOIMII
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	// TÄMÄ VARMISTAA, ETTÄ STRINGINÄ TULLUT ID KÄÄNNETÄÄN INTIKSI
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void setWeight(String weight) {
		try {
			this.weight = Float.parseFloat(weight);
		}
		catch (NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}
	public String toString() {
		return this.id+": "+this.breed+" / "+this.weight;
	}
}
