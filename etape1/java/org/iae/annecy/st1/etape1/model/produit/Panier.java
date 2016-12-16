package org.iae.annecy.st1.etape1.model.produit;

import java.util.ArrayList;
import java.util.Iterator;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class Panier {
	// ConsoleHelper display = new ConsoleHelper();
	private Client client;
	private ArrayList<Produit> achats = new ArrayList<Produit>();

	public ArrayList<Produit> getProduits() {
		return this.achats;
	}

	private boolean commandeValide = false;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isCommandeValide() {
		return commandeValide;
	}

	public void setCommandeValide(boolean commandeValide) {
		this.commandeValide = commandeValide;
	}

	public void ajouterProduitPanier(Produit produit, int qte) {
		this.getProduits().add(produit);
		produit.setQuantite(qte);
	}

	public String afficherPanier() {
		String texte = "";
		int count = 1;
		for (Produit produit : this.achats) {
			texte += count++ + "[Reférence : " + produit.getReference() + " - Nom : " + produit.getNom()
					+ " - Description : " + produit.getDescription() + " - Description longue : "
					+ produit.getDescriptionLongue() + " - Quantité : " + produit.getQuantite() + " - Prix : "
					+ produit.getPrix() * produit.getQuantite() + "]\n";
		}
		return texte;

	}

	public Panier() {

	}

}
