/**
 * 
 */

package org.iae.annecy.st1.etape1;


import java.io.ObjectInputStream;
import java.util.Scanner;
import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.StringView;
import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.produit.Catalogue;
import org.iae.annecy.st1.etape1.model.produit.Menu;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.etape1.view.UserTextFrenchView;
import org.iae.annecy.st1.tools.ConsoleHelper;

/**
 * Classe permetant de tester le MVC.
 * 
 * @author Djer1013
 */
public class Main {

	/**
	 * COntroller pemetant le traitement des actions d'exemple.
	 */
	private static MainController mainController;

	static {
		Main.mainController = new MainController();
	}
	
	
	/**
	 * Lance l'application.
	 * 
	 * @param args
	 *            command line parameters
	 */
	public static void main(final String[] args) {
		//initUserModel();

		final DataView userData = mainController.get("user:display");
		final StringView userView = new UserTextFrenchView();

		//ConsoleHelper.display(userView.build(userData));
		
		Produit p1, p2;
		p1 =new Produit("PR01", "Skovna","Chaise", "Dossier en cuir matelassé", 34.00);
		p2 = new Produit ("PR02", "Brotsk","Table","Table en carbone", 65.00);
		Catalogue catalogue = new Catalogue();
		catalogue.ajouterProduit(p1);
		catalogue.ajouterProduit(p2);
		
		Menu m = new Menu(catalogue);
		System.out.println(catalogue.afficherProduits());
		m.afficherMenu();
	
		/*try{
			File fichier = new File("desktop");
			ObjectInputStream ios = new ObjectInputStream (new FileInputStream (fichier));
			catalogue = new Catalogue();
		}*/
		/*
		CatalogueController catController = new CatalogueController(catalogue);
		System.out.println(catController.get());
		
	private static void initUserModel() {
		final UserModel userModel = new UserModel();
		userModel.register(mainController);
		
	*/
	}
	
}
