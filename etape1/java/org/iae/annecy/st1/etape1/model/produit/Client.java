package org.iae.annecy.st1.etape1.model.produit;

import java.io.Serializable;

public class Client implements Serializable {
	private String id, nom, prenom;
	private int codePromotionnel;
	private Panier monPanier;

	public String getID() {
		return id;
	}

	public void setID(String identification) {
		id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getCodePromotionnel() {
		return codePromotionnel;
	}

	public void setCodePromotionnel(int codePromotionnel) {
		this.codePromotionnel = codePromotionnel;
	}

	public Panier getMonPanier() {
		return monPanier;
	}

	public void setMonPanier(Panier monPanier) {
		this.monPanier = monPanier;
		monPanier.setClient(this);
	}

	public Client(String idClient, String nom, String prenom, int codePromotionnel) {
		this.id = idClient;
		this.nom = nom;
		this.prenom = prenom;
		this.codePromotionnel = codePromotionnel;
	}

	public String afficherClient() {
		return "Le nouveau client a l'ID " + id + ", son nom est " + nom + ", son prénom est " + prenom
				+ ", et son code promotionnel " + codePromotionnel;

	}

}
