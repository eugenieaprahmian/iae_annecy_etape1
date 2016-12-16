package org.iae.annecy.st1.etape1.controller;

import org.iae.annecy.st1.etape1.model.produit.Catalogue;

public class CatalogueController {
	private Catalogue cat;
	public CatalogueController (Catalogue c){
		this.cat = c;
	}
	public String get (){
		return this.cat.afficherProduits();
		
		
		
	}
}
