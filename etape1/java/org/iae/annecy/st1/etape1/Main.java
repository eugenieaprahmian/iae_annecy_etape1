/**
 * 
 */

package org.iae.annecy.st1.etape1;

import java.io.ObjectInputStream;
import java.io.Serializable;
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
import org.iae.annecy.st1.etape1.model.produit.Client;
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
public class Main implements Serializable {

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

		produitE();

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

	private static void produitE() {

		Catalogue catalogueProduit = new Catalogue();
		AnnuaireClient annuaireClients = new AnnuaireClient();
		Produit p1 = new Produit("PR01", "Skovna", "Armoire", "Armoire blanche", 98);
		Produit p2 = new Produit("PR02", "Brotsk", "Miroir", "Cadre bois ", 34);
		Client c1 = new Client("CL01", "Vanotti", "Marion", 15);
		Client c2 = new Client("CL02", "Dupont", "Jean", 25);

		// deserialisation catalogue
		try {
			FileInputStream fis = new FileInputStream("catalogue.txt");
			ObjectInputStream object = new ObjectInputStream(fis);
			catalogueProduit = (Catalogue) object.readObject();
			object.close();
			fis.close();

		} catch (FileNotFoundException e) {

			catalogueProduit.ajouterProduit(p1);
			catalogueProduit.ajouterProduit(p2);

		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// deserialisation annuaire
		try {
			FileInputStream fis2 = new FileInputStream("annuaireClient.txt");
			ObjectInputStream object2 = new ObjectInputStream(fis2);
			annuaireClients = (AnnuaireClient) object2.readObject();
			object2.close();
			fis2.close();

		} catch (FileNotFoundException e) {
			annuaireClients.ajouterClient(c1);
			annuaireClients.ajouterClient(c2);

		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Menu menu = new Menu(catalogueProduit, annuaireClients);
		ConsoleHelper display = new ConsoleHelper();
		display.display(catalogueProduit.afficherProduits());
		menu.afficherMenu();

		// CatalogueController catController = new
		// CatalogueController(catalogue);
		// System.out.println(catController.get());
	}

	private static void produitJDE() {
		initUserModel();
		initCustomerModel();

		final Scanner scan = new Scanner(System.in, "UTF-8");

		/*
		 * final DataView userData = mainController.get("user:display"); final
		 * StringView userView = new UserTextFrenchView();
		 * 
		 * ConsoleHelper.display(userView.build(userData));
		 */
		// get a Person
		DataParam searchPersonParam = new BasicDataParam();
		searchPersonParam.add("id", "10"); // 0-5 inconu, 5-10 TEST, >10
											// DERUETTE
		final DataView customerData = mainController.get("person:get", searchPersonParam);
		final StringView customerGetView = new PersonGetFrenchView();

		ConsoleHelper.display(customerGetView.build(customerData));

		// demande l'ajout d'une personne attribut/attribut
		DataParam newCustomer = new BasicDataParam();
		String personId = ConsoleHelper.read(scan, "Quel est l'ID du client ?");
		newCustomer.add("id", personId); // <100 = OK, sinon KO
		String personNom = ConsoleHelper.read(scan, "Quel est le nom du client ?");
		newCustomer.add("nom", personNom);
		String personPrenom = ConsoleHelper.read(scan, "Quel est le prÃ©nom du client ?");
		newCustomer.add("prenom", personPrenom);

		final DataView customerAddData = mainController.get("person:add", newCustomer);
		final StringView customerAddView = new PersonAddFrenchView();

		ConsoleHelper.display(customerAddView.build(customerAddData));

		// Demande l'ajout d'une personne en une seul fois
		final ConsoleInputView customerCreateView = new PersonCreateFrenchView();
		customerCreateView.ask(scan);

		final DataView customerAddDataBulk = mainController.get("person:add", newCustomer);
		final StringView customerAddViewBulk = new PersonAddFrenchView();

		ConsoleHelper.display(customerAddViewBulk.build(customerAddDataBulk));

	}

}
