package org.iae.annecy.st1.etape1.model.produit;

import java.io.Serializable;

public class Produit implements Serializable {
	private String reference, description, descriptionLongue, nom;
	private double prix;
	private double quantite = 0;

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

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String afficherProduit() {
		String texte = "";
		int count = 1;

		texte += count++ + "[Reférence : " + this.getReference() + " - Nom : " + this.getNom() + " - Description : "
				+ this.getDescription() + " - Description longue : " + this.getDescriptionLongue() + " - Prix : "
				+ this.getPrix() + "]\n";

		return texte;
	}

	public Produit(String reference, String nom, String description, String descriptionLongue, double prix) {
		this.reference = reference;
		this.nom = nom;
		this.description = description;
		this.descriptionLongue = descriptionLongue;
		this.prix = prix;
	}

}
