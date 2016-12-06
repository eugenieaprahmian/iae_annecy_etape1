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
		AnnuaireClient annuaireClients= new AnnuaireClient();
				
     	try{
			FileInputStream fis = new FileInputStream("catalogue.txt");
			ObjectInputStream object = new ObjectInputStream(fis);
			catalogueProduit = (Catalogue) object.readObject();
			object.close();
			fis.close();
			FileInputStream fis2 = new FileInputStream("annuaireClient.txt");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			annuaireClients = (AnnuaireClient) ois2.readObject();
			ois2.close();
			fis2.close();
			
		}catch(FileNotFoundException e){
			catalogueProduit.ajouterProduit(produit1);
			catalogueProduit.ajouterProduit(produit2);
			
		}catch(IOException e){
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
     	/*try{
			FileInputStream fis2 = new FileInputStream("annuaireClient.txt");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			annuaireClients = (AnnuaireClient) ois2.readObject();
			ois2.close();
			fis2.close();
		}catch(FileNotFoundException e){
			catalogueProduit.ajouterProduit(produit1);
			catalogueProduit.ajouterProduit(produit2);
			
		}catch(IOException e){
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*\

     	
		
		Menu m = new Menu(catalogueProduit, annuaireClients);
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
