/**
 * 
 */

package org.iae.annecy.st1.etape1;


import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.StringView;
import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.produit.AnnuaireClient;
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
		
		Produit produit1 =new Produit("PR01", "Skovna","Chaise", "Dossier en cuir matelassé", 34.00);
		Produit produit2 = new Produit ("PR02", "Brotsk","Table","Table en carbone", 65.00);
		Catalogue catalogueProduit = new Catalogue();
		AnnuaireClient annuairedesClients= new AnnuaireClient();
		
		
		
     	try{
			FileInputStream fout = new FileInputStream("catalogue.txt");
			//FileInputStream fout2 = new FileInputStream("annuaire.txt");
			ObjectInputStream object = new ObjectInputStream(fout);
			//ObjectInputStream o2 = new ObjectInputStream(fout2);
			catalogueProduit = (Catalogue) object.readObject();
			//a1 = (Annuaire) o2.readObject();
			//o2.close();
			object.close();
			fout.close();
			//sfout2.close();
		}catch(FileNotFoundException e){
			
			//System.out.println("le fichier est vide !!");
			
			
			catalogueProduit.ajouterProduit(produit1);
			catalogueProduit.ajouterProduit(produit2);
			
		}catch(IOException e){
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
     	
		
		Menu m = new Menu(catalogueProduit, annuairedesClients);
		System.out.println(catalogueProduit.afficherProduits());
		m.afficherMenu();
	
		/*
		CatalogueController catController = new CatalogueController(catalogue);
		System.out.println(catController.get());
		
	private static void initUserModel() {
		final UserModel userModel = new UserModel();
		userModel.register(mainController);*/
		
	
	}
	
}
