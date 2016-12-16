package org.iae.annecy.st1.etape1.model.produit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class Catalogue implements Serializable {
	private ArrayList<Produit> produits = new ArrayList<Produit>();

	public ArrayList<Produit> getProduits() {
		return this.produits;
	}

	public void ajouterProduit(Produit produit) {
		this.getProduits().add(produit);
	}

	public String afficherProduits() {
		String texte = "";
		int count = 1;
		for (Produit produit : this.produits) {
			texte += count++ + "[Reférence : " + produit.getReference() + " - Nom : " + produit.getNom()
					+ " - Description : " + produit.getDescription() + " - Description longue : "
					+ produit.getDescriptionLongue() + " - Prix : " + produit.getPrix() + "] \n";
		}
		return texte;
	}

	public Produit chercheProduit(String ref) {
		ConsoleHelper display = new ConsoleHelper();
		Iterator<Produit> it = this.getProduits().iterator();
		while (it.hasNext()) {
			Produit current = it.next();
			if (ref.equals(current.getReference())) {
				display.display("Produit trouvé ! ");
				return current;
			}
		}
		return null;
	}

	public boolean referenceExiste(String reference) {
		boolean bool = false;
		for (Produit p : produits) {
			if (reference.equals(p.getReference()))
				bool = true;
		}
		return bool;
	}

}
