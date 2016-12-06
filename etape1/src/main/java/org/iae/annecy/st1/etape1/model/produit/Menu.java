package org.iae.annecy.st1.etape1.model.produit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Menu {
	
	Catalogue cat;
	AnnuaireClient annuaire;

	public Menu(Catalogue cat, AnnuaireClient annuaire) {
		super();
		this.cat = cat;
		this.annuaire = annuaire;
	}
	
	public Catalogue getCat() {
		return cat;
	}

	public void setCat(Catalogue cat) {
		this.cat = cat;
	}
	public AnnuaireClient getAnnuaireClient() {
		return annuaire; 
	}

	public void setAnnuaireClient(AnnuaireClient annuaire) {
		this.annuaire = annuaire;
	}

	public void modifierAttribut(){
		Scanner scannerProduit = new Scanner (System.in);
		System.out.println("Quel produit modifier (indiquer référence du produit)?");
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
			System.out.println("Le produit référencé par " + monProduit.getReference()+ " a le nouveau nom "+ monProduit.getNom());
		
		}
		else if (attribut == 4){
			double prix;
			System.out.println("Nouveau prix : ");
			prix = scannerProduit.nextDouble();
			monProduit.setPrix(prix);
		System.out.println("Le produit référencé par " +monProduit.getReference()+ " a le nouveau prix " + monProduit.getPrix());
		}
		try{
			FileOutputStream fos = new FileOutputStream("catalogue.txt");
			ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(this.cat);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	           //System.out.println(ioe.getMessage()); 
	    	   System.out.println(ioe);
		}
	}

	
	public void ajouterProduit(){
		Scanner creerAttribut = new Scanner (System.in);
		String reference, nom, description, descriptionLongue;
		double prix;
		
			
		do{	
		System.out.println("Référence du nouveau produit : ");
		reference = creerAttribut.nextLine();
		if((cat.referenceExiste(reference))== true)
			System.out.println("Référence déjà rattachée à un produit");
		}while((cat.referenceExiste(reference))== true);
		
		System.out.println("Nom du nouveau produit : ");
		nom = creerAttribut.nextLine();
		
		System.out.println("Description du nouveau produit : ");
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
		
		try{
			FileOutputStream fos = new FileOutputStream("catalogue.txt");
			ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(this.cat);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	    	   System.out.println(ioe);
		}
	}
	public void ajouterClientAnnuaire(){
		Scanner creerAttribut = new Scanner (System.in);
		String idClient, nom, prenom, codePromotionnel;
		
		//do{	
		System.out.println("ID du nouveau client : ");
		idClient = creerAttribut.nextLine();
		/*if((annuaire.IDexiste(ID))== true)
			System.out.println("Numéro déjà rattachée à un client");
		}while((annuaire.IDExiste(ID))== true);*/
		
		System.out.println("Nom du nouveau client : ");
		nom = creerAttribut.nextLine();
		
		System.out.println("Prénom du nouveau client : ");
		prenom = creerAttribut.nextLine();
		
		System.out.println("Code promotionnel du nouveau client : ");
		codePromotionnel = creerAttribut.nextLine();
				
		Client monClient = new Client(idClient, nom, prenom, codePromotionnel);
		System.out.println("Le nouveau client a le numéro "+ monClient.getID() + ", son nom est " + monClient.getNom() + ", son prénom est "+monClient.getPrenom()+ "et il dispose des codes promotionnels " + monClient.getCodePromotionnel());
		this.getAnnuaireClient().ajouterClient(monClient);
		
		try{
			FileOutputStream fos2 = new FileOutputStream("annuaireClient.txt");
			ObjectOutputStream oos2= new ObjectOutputStream(fos2);
	         oos2.writeObject(this.cat);
	         oos2.close();
	         fos2.close();
	       }catch(IOException ioe){
	    	   System.out.println(ioe);
		}
	}
	
	public void modifierAttributClient(){
		Scanner scannerClient = new Scanner (System.in);
		System.out.println("Quel client modifier (indiquer numéro du client)?");
		String client;
		client = scannerClient.nextLine();
		Client monClient = annuaire.chercheClient(client); 
		System.out.println("Quel attribut modifier ? \n \n 1- Nom \n 2- Prénom \n 3- Code promotionnel ");
		int attribut;
		attribut = scannerClient.nextInt();
	
		if (attribut == 1){
			String nom; 
			System.out.println("Nouveau nom : ");
			scannerClient.nextLine();
			nom = scannerClient.nextLine();
			monClient.setNom(nom);
		System.out.println("Le client référencé par " +monClient.getID()+ " a le nouveau nom " + monClient.getNom());
		}
		else if (attribut == 2){
			String prenom;
			System.out.println("Nouveau prénom : ");
			scannerClient.nextLine();
			prenom = scannerClient.nextLine();
			monClient.setPrenom(prenom);
			System.out.println("Le client référencé par " +monClient.getID()+ " a le nouveau prénom" + monClient.getPrenom());
		}
		else if (attribut == 3){
			String codePromotionnel;
			System.out.println("Nouveau code promotionnel : ");
			scannerClient.nextLine();
			codePromotionnel = scannerClient.nextLine();
			monClient.setCodePromotionnel(codePromotionnel);
			System.out.println("Le client référencé par " + monClient.getID()+ " a le nouveau code promotionnel "+ monClient.getCodePromotionnel());
			
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
						+ "4- Annuaire client \n"
						+ "5- Ajouter client \n"
						+ "6- Modifier client \n"
						+ "7- Quitter");
			choix = scannerMenu.nextInt();
			
			if (choix == 1)
				System.out.println(cat.afficherProduits());
			else if (choix == 3)
				this.modifierAttribut();
			else if (choix ==2)
				this.ajouterProduit();
			else if (choix == 4)
				System.out.println(annuaire.afficherClients());
			else if (choix == 5)
				this.ajouterClientAnnuaire();
			else if (choix ==6)
				this.modifierAttributClient();
		}
		
		while(choix !=7);
	}

}
