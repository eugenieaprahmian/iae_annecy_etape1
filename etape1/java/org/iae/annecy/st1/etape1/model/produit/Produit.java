package org.iae.annecy.st1.etape1.model.produit;

import java.io.Serializable;

public class Produit implements Serializable {
	private String reference, description, descriptionLongue,nom;
	private double prix;
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionLongue() {
		return descriptionLongue;
	}
	public void setDescriptionLongue(String descriptionLongue) {
		this.descriptionLongue = descriptionLongue;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Produit (String reference, String nom, String description, String descriptionLongue, double prix){
		this.reference= reference;
		this.nom = nom;
		this.description=description;
		this.descriptionLongue=descriptionLongue;
		this.prix=prix;
		
	}
	

}
