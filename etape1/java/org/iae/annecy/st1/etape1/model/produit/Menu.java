package org.iae.annecy.st1.etape1.model.produit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class Menu implements Serializable {

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

	public void ecrireAnnuaire() {
		try {
			FileOutputStream fos2 = new FileOutputStream("annuaireClient.txt");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(this.annuaire);
			oos2.close();
			fos2.close();
		} catch (IOException ioe) {
			ConsoleHelper.display("Erreur écriture sur l'annuaire");
		}
	}

	public void ecrireCatalogue() {
		try {
			FileOutputStream fos2 = new FileOutputStream("catalogue.txt");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(this.cat);
			oos2.close();
			fos2.close();
		} catch (IOException ioe) {
			ConsoleHelper.display("Erreur écriture sur le catalogue");
		}
	}

	public void modifierAttribut() {
		Scanner scannerProduit = new Scanner(System.in);
		ConsoleHelper.display("Quel produit modifier (indiquer référence du produit)?");
		String produit;
		produit = scannerProduit.nextLine();
		Produit monProduit = cat.chercheProduit(produit); // monProduit =
															// produit tampon
															// pour le récupérer
		ConsoleHelper.display(monProduit.afficherProduit());
		ConsoleHelper.display(
				"Quel attribut modifier ? \n \n 1- Nom \n 2- Description \n 3- Description longue \n 4- Prix ");
		int attribut;
		attribut = scannerProduit.nextInt();

		if (attribut == 2) {
			String description;
			ConsoleHelper.display("Nouvelle description : ");
			scannerProduit.nextLine();
			description = scannerProduit.nextLine();
			monProduit.setDescription(description);
			this.ecrireCatalogue();
			ConsoleHelper.display("Le produit référence par " + monProduit.getReference()
					+ " a la nouvelle description " + monProduit.getDescription());
		} else if (attribut == 3) {
			String descriptionLongue;
			ConsoleHelper.display("Nouvelle description longue : ");
			scannerProduit.nextLine();
			descriptionLongue = scannerProduit.nextLine();
			monProduit.setDescriptionLongue(descriptionLongue);
			this.ecrireCatalogue();
			ConsoleHelper.display("Le produit référencé par " + monProduit.getReference()
					+ " a la nouvelle description longue " + monProduit.getDescriptionLongue());
		} else if (attribut == 1) {
			String nom;
			ConsoleHelper.display("Nouveau nom : ");
			scannerProduit.nextLine();
			nom = scannerProduit.nextLine();
			monProduit.setNom(nom);
			this.ecrireCatalogue();
			ConsoleHelper.display("Le produit référence par " + monProduit.getReference() + " a le nouveau nom "
					+ monProduit.getNom());

		} else if (attribut == 4) {
			double prix;
			ConsoleHelper.display("Nouveau prix : ");
			prix = scannerProduit.nextDouble();
			monProduit.setPrix(prix);
			this.ecrireCatalogue();
			ConsoleHelper.display("Le produit référence par " + monProduit.getReference() + " a le nouveau prix "
					+ monProduit.getPrix());
		}

	}

	public void ajouterProduit() {
		Scanner creerAttribut = new Scanner(System.in);
		String reference, nom, description, descriptionLongue;
		double prix;

		do {
			ConsoleHelper.display("Référence du nouveau produit : ");
			reference = creerAttribut.nextLine();
			if ((cat.referenceExiste(reference)) == true)
				ConsoleHelper.display("Référence déjà rattachée à  un produit");
		} while ((cat.referenceExiste(reference)) == true);

		ConsoleHelper.display("Nom du nouveau produit : ");
		nom = creerAttribut.nextLine();

		ConsoleHelper.display("Description du nouveau produit : ");
		description = creerAttribut.nextLine();

		ConsoleHelper.display("Description longue du nouveau produit : ");
		descriptionLongue = creerAttribut.nextLine();
		do {

			ConsoleHelper.display("Prix du nouveau produit : ");
			prix = creerAttribut.nextInt();
			if (prix < 0) {
				ConsoleHelper.display("Le prix indiqué est invalide, veuillez ressaisir un prix valide");
			}
		} while (prix < 0);

		Produit monProduit = new Produit(reference, nom, description, descriptionLongue, prix);
		ConsoleHelper.display("Le nouveau produit a la référence" + monProduit.getReference() + ", sa description est "
				+ monProduit.getDescription() + ", sa description longue est " + monProduit.getDescriptionLongue()
				+ " et son prix " + monProduit.getPrix());
		this.getCat().ajouterProduit(monProduit);
		this.ecrireCatalogue();
	}

	public void ajouterClientAnnuaire() {
		Scanner creerAttribut = new Scanner(System.in);
		String idClient, nom, prenom;
		int codePromotionnel;

		// do{
		ConsoleHelper.display("ID du nouveau client : ");
		idClient = creerAttribut.nextLine();

		ConsoleHelper.display("Nom du nouveau client : ");
		nom = creerAttribut.nextLine();

		ConsoleHelper.display("Prénom du nouveau client : ");
		prenom = creerAttribut.nextLine();

		ConsoleHelper.display("Code promotionnel du nouveau client : ");
		codePromotionnel = creerAttribut.nextInt();

		Client monClient = new Client(idClient, nom, prenom, codePromotionnel);
		ConsoleHelper.display("Le client a bien été ajouté !");
		ConsoleHelper.display(monClient.afficherClient());

		this.getAnnuaireClient().ajouterClient(monClient);
		this.ecrireAnnuaire();
	}

	public void modifierAttributClient() {
		Scanner scannerClient = new Scanner(System.in);
		ConsoleHelper.display("Quel client modifier (indiquer numéro du client)?");
		String client;
		client = scannerClient.nextLine();
		Client monClient = annuaire.chercheClient(client);
		ConsoleHelper.display("Quel attribut modifier ? \n \n 1- Nom \n 2- Prénom \n 3- Code promotionnel ");
		int attribut;
		attribut = scannerClient.nextInt();

		if (attribut == 1) {
			String nom;
			ConsoleHelper.display("Nouveau nom : ");
			scannerClient.nextLine();
			nom = scannerClient.nextLine();
			monClient.setNom(nom);
			this.ecrireAnnuaire();
			ConsoleHelper.display(
					"Le client référence par " + monClient.getID() + " a le nouveau nom " + monClient.getNom());
		} else if (attribut == 2) {
			String prenom;
			ConsoleHelper.display("Nouveau prénom : ");
			scannerClient.nextLine();
			prenom = scannerClient.nextLine();
			monClient.setPrenom(prenom);
			this.ecrireAnnuaire();
			ConsoleHelper.display(
					"Le client référence par " + monClient.getID() + " a le nouveau prénom" + monClient.getPrenom());
		} else if (attribut == 3) {
			int codePromotionnel;
			ConsoleHelper.display("Nouveau code promotionnel : ");
			scannerClient.nextLine();
			codePromotionnel = scannerClient.nextInt();
			monClient.setCodePromotionnel(codePromotionnel);
			this.ecrireAnnuaire();
			ConsoleHelper.display("Le client référence par " + monClient.getID() + " a le nouveau code promotionnel "
					+ monClient.getCodePromotionnel());

		}

	}

	public void menuPanierClient(Client client) {
		Scanner scannerClient = new Scanner(System.in);
		String idclient;
		int choix = 0;
		while (choix != 4) {
			if (client.getMonPanier().isCommandeValide() == false) {
				ConsoleHelper.display(" === Gestion du panier du client ===\n " + client.getPrenom() + " "
						+ client.getNom()
						+ "\n1- Afficher panier client et prix\n2- Ajouter un produit\n3- Valider mon panier\n4- Retour");

			} else if (client.getMonPanier().isCommandeValide() == true) {
				ConsoleHelper.display("===Gestion de la commande client === \n" + client.getPrenom() + " "
						+ client.getNom() + "\n1- Afficher commande et  prix\n4- Retour");
				ConsoleHelper.display("La commande du client est validée, impossible d'ajouter de nouveaux produits\n");
			}

			choix = scannerClient.nextInt();
			if (choix == 1) {
				ConsoleHelper.display(client.getMonPanier().afficherPanier());
			} else if (choix == 2) {
				ConsoleHelper.display("Quel produit ajouter au panier (entrer référence) ? \n ");
				String reference;
				reference = scannerClient.nextLine();
				Produit monProduit = cat.chercheProduit(reference);
				ConsoleHelper.display("Quelle quantité voulez-vous ajouter au panier ?\n ");
				int quantite;
				quantite = scannerClient.nextInt();
				ConsoleHelper.display(client.getID() + client.getNom() + client.getPrenom() + "\n [ Produit : "
						+ monProduit.getNom() + " - Prix : " + monProduit.getPrix() + "– Prix total : "
						+ monProduit.getPrix() * quantite + " ] \n ");
			} else if (choix == 3) {
				client.getMonPanier().setCommandeValide(true);
				ConsoleHelper.display("Commande validée");
			}

		}
		if (choix == 4) {
			this.afficherMenu();
		}
	}

	public void creerPanierClient() {
		Scanner scannerClient = new Scanner(System.in);
		ConsoleHelper.display("Quel est le numéro d'autentification du client ?\n");
		String idclient;
		idclient = scannerClient.nextLine();
		Client monClient = annuaire.chercheClient(idclient);
		ConsoleHelper.display("Quel produit ajouter au panier (entrer référence) ? \n ");
		String reference;
		reference = scannerClient.nextLine();
		Produit monProduit = cat.chercheProduit(reference);
		ConsoleHelper.display("Quelle quantité voulez-vous ajouter au panier ?\n ");
		int quantite;
		quantite = scannerClient.nextInt();
		ConsoleHelper.display(monClient.getID() + monClient.getNom() + monClient.getPrenom() + "\n [ Produit : "
				+ monProduit.getNom() + " - Prix : " + monProduit.getPrix() + "- Prix total : "
				+ monProduit.getPrix() * quantite + " ] \n ");

	}

	public void afficherCommandes() {

	}

	public void afficherMenu() {
		int choix = 0;
		do {
			Scanner scannerMenu = new Scanner(System.in);
			ConsoleHelper.display(
					"========== MENU ========== \n" + "\n" + "\n" + "1- Afficher catalogue \n" + "2- Ajouter produit \n"
							+ "3- Modifier produit\n" + "4- Annuaire client \n" + "5- Ajouter client \n"
							+ "6- Modifier client \n" + "7- Gérer mes paniers clients\n" + "8- Quitter");
			choix = scannerMenu.nextInt();

			if (choix == 1)
				ConsoleHelper.display(cat.afficherProduits());
			else if (choix == 3)
				this.modifierAttribut();
			else if (choix == 2)
				this.ajouterProduit();
			else if (choix == 4)
				ConsoleHelper.display(annuaire.afficherClients());
			else if (choix == 5)
				this.ajouterClientAnnuaire();
			else if (choix == 6)
				this.modifierAttributClient();
			else if (choix == 7) {
				// this.menuPanierClient(client);
			}
		}

		while (choix != 8);
	}

}
