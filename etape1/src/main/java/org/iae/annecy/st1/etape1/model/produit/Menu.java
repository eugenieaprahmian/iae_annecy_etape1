package org.iae.annecy.st1.etape1.model.produit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Menu {
	
	Catalogue cat;

	public Menu(Catalogue cat) {
		super();
		this.cat = cat;
	}
	
	public Catalogue getCat() {
		return cat;
	}

	public void setCat(Catalogue cat) {
		this.cat = cat;
	}

	public void modifierAttribut(){
		Scanner scannerProduit = new Scanner (System.in);
		System.out.println("Quel produit modifier ?");
		String produit;
		produit = scannerProduit.nextLine();
		Produit monProduit = cat.chercheProduit(produit); //monProduit -> produit tampon pour le récupérer
		System.out.println("Quel attribut modifier ? \n \n 1- Nom \n 2- Description \n 3- Description longue \n 4- Prix ");
		int attribut;
		attribut = scannerProduit.nextInt();
	
		if (attribut == 2){
			String description; 
			System.out.println("Nouvelle description : ");
			scannerProduit.nextLine();
			description = scannerProduit.nextLine();
			monProduit.setDescription(description);
		System.out.println("Le produit référencé par " +monProduit.getReference()+ " a la nouvelle description " + monProduit.getDescription());
		}
		else if (attribut == 3){
			String descriptionLongue;
			System.out.println("Nouvelle description longue : ");
			scannerProduit.nextLine();
			descriptionLongue = scannerProduit.nextLine();
			monProduit.setDescriptionLongue(descriptionLongue);
			System.out.println("Le produit référené par " +monProduit.getReference()+ " a la nouvelle description longue" + monProduit.getDescriptionLongue());
		}
		else if (attribut == 1){
			String nom;
			System.out.println("Nouveau nom : ");
			scannerProduit.nextLine();
			nom = scannerProduit.nextLine();
			monProduit.setNom(nom);
			System.out.println("Le produit référencé par " + monProduit.getReference()+ "a le nouveau nom "+ monProduit.getNom());
		
		}
		else if (attribut == 4){
			double prix;
			System.out.println("Nouveau prix : ");
			//scannerProduit.nextDouble();
			prix = scannerProduit.nextDouble();
			monProduit.setPrix(prix);
		System.out.println("Le produit référencé par " +monProduit.getReference()+ " a le nouveau prix " + monProduit.getPrix());
		}
		
	}

	
	public void ajouterProduit(){
		Scanner creerAttribut = new Scanner (System.in);
		String reference, nom, description, descriptionLongue;
		double prix;
		
			
		do{	
		System.out.println("Référence du nouveau produit : ");
		//creerAttribut.nextLine();
		reference = creerAttribut.nextLine();
		if((cat.referenceExiste(reference))== true)
			System.out.println("Référence déjà rattachée à un produit");
		}while((cat.referenceExiste(reference))== true);
		
		System.out.println("Nom du nouveau produit : ");
		nom = creerAttribut.nextLine();
		//}while(cat.referenceExiste(reference)==true); 
		
		System.out.println("Description du nouveau produit : ");
		//creerAttribut.nextLine();
		description = creerAttribut.nextLine();
		
		System.out.println("Description longue du nouveau produit : ");
		descriptionLongue = creerAttribut.nextLine();
		do {
			
		System.out.println("Prix du nouveau produit : ");
		prix = creerAttribut.nextInt();
		if (prix < 0){
			System.out.println("Le prix indiqué est invalide, veuillez ressaisir un prix valide");
		}
		}
		while (prix<0);
		
		Produit monProduit = new Produit(reference, nom, description, descriptionLongue, prix);
		System.out.println("Le nouveau produit a la référence "+ monProduit.getReference() + ", sa description est " + monProduit.getDescription() + ", sa description longue est "+monProduit.getDescriptionLongue()+ "et son prix " + monProduit.getPrix());
		this.getCat().ajouterProduit(monProduit);
		//ajouter sérialisation ici
		try{
			FileOutputStream fos = new FileOutputStream("serializedcatalogue");
			ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(this.getCat());
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	           System.out.println(ioe.getMessage()); 
	    	   ioe.printStackTrace();
		}
	}
	
	public void afficherMenu(){
		int choix = 0;
		do{
			Scanner scannerMenu = new Scanner (System.in);
			System.out.println("========== MENU ========== \n"
						+ "\n"
						+ "\n"
						+ "1- Afficher catalogue \n"
						+ "2- Ajouter produit \n"
						+ "3- Modifier produit\n"
						+ "4- Quitter");
			choix = scannerMenu.nextInt();
			
			if (choix == 1)
				System.out.println(cat.afficherProduits());
			else if (choix == 3)
				this.modifierAttribut();
			else if (choix ==2)
				this.ajouterProduit();
		}
		while(choix !=4);
	}

}
