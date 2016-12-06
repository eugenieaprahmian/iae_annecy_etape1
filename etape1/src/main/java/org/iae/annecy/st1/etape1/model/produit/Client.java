package org.iae.annecy.st1.etape1.model.produit;

public class Client {
	private String id, nom, prenom, codePromotionnel;

	public String getID() {
		return id;
	}

	public void setID(String id) {
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

	public String getCodePromotionnel() {
		return codePromotionnel;
	}

	public void setCodePromotionnel(String codePromotionnel) {
		this.codePromotionnel = codePromotionnel;
	}
	Client (String idClient, String nom, String prenom, String codePromotionnel){
		this.id = idClient;
		this.nom=nom;
		this.prenom=prenom; 
		this.codePromotionnel=codePromotionnel;
	}
}
