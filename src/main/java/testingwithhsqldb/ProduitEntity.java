/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingwithhsqldb;

/**
 *
 * @author pedago
 */

public class ProduitEntity {
	private int id = 0;        //code d'un produit
	private String name = "unNom"; //nom d'un produit
	private float price = 0.0f;     //prix d'un produit

	public ProduitEntity(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public ProduitEntity() {};
        
        // pour récupérer l'id ou le nom ou le prix
        
	public int getId() {
		return id;
	}
        
	public String getName() {
		return name;
	}
        
	public float getPrice() {
		return price;
	}
        
        // pour modifier l'id ou le nom ou le prix
        
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
}