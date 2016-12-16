/**
 * 
 */

package org.iae.annecy.st1.etape1;


import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.iae.annecy.st1.common.mvc.BasicDataParam;
import org.iae.annecy.st1.common.mvc.ConsoleInputView;
import org.iae.annecy.st1.common.mvc.DataParam;
import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.StringView;
import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.produit.AnnuaireClient;
import org.iae.annecy.st1.etape1.model.produit.Catalogue;
import org.iae.annecy.st1.etape1.model.produit.Menu;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.etape1.model.person.PersonAddModel;
import org.iae.annecy.st1.etape1.model.person.PersonGetModel;
import org.iae.annecy.st1.etape1.view.UserTextFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonAddFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonCreateFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonGetFrenchView;
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
		initCustomerModel();
		
		final Scanner scan = new Scanner(System.in, "UTF-8");

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
		}*/
		
		
		// get a Person
		DataParam searchPersonParam = new BasicDataParam();
		searchPersonParam.add("id", "10"); //0-5 inconu, 5-10 TEST, >10 DERUETTE
		final DataView customerData = mainController.get("person:get", searchPersonParam);
		final StringView customerGetView = new PersonGetFrenchView();
		
		ConsoleHelper.display(customerGetView.build(customerData));
		
		//demande l'ajout d'une personne attribut/attribut
		DataParam newCustomer = new BasicDataParam();
		String personId = ConsoleHelper.read(scan, "Quel est l'ID du client ?");
		newCustomer.add("id", personId); // <100 = OK, sinon KO
		String personNom = ConsoleHelper.read(scan, "Quel est le nom du client ?");
		newCustomer.add("nom", personNom);
		String personPrenom = ConsoleHelper.read(scan, "Quel est le prénom du client ?");
		newCustomer.add("prenom", personPrenom);
		
		final DataView customerAddData = mainController.get("person:add", newCustomer);
		final StringView customerAddView = new PersonAddFrenchView();
		
		ConsoleHelper.display(customerAddView.build(customerAddData));
		
		//Demande l'ajout d'une personne en une seul fois
		final ConsoleInputView customerCreateView = new PersonCreateFrenchView();
		customerCreateView.ask(scan);
		
		final DataView customerAddDataBulk = mainController.get("person:add", newCustomer);
		final StringView customerAddViewBulk = new PersonAddFrenchView();
		
		ConsoleHelper.display(customerAddViewBulk.build(customerAddDataBulk));
		
     	
		
		Menu menu = new Menu(catalogueProduit, annuaireClients);
		System.out.println(catalogueProduit.afficherProduits());
		menu.afficherMenu();
	
		
		//CatalogueController catController = new CatalogueController(catalogue);
		//System.out.println(catController.get());
	}
	private static void initUserModel() {
		final UserModel userModel = new UserModel();
		userModel.register(mainController);
		
	
	}
	
	private static void initCustomerModel() {
		final PersonGetModel customerGetModel = new PersonGetModel();
		customerGetModel.register(mainController);
		
		final PersonAddModel customerAddModel = new PersonAddModel();
		customerAddModel.register(mainController);
	}
	
	
}
