package org.iae.annecy.st1.etape1.model.produit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class AnnuaireClient implements Serializable {

	private ArrayList<Client> clients = new ArrayList<Client>();

	public ArrayList<Client> getClients() {
		return this.clients;
	}

	public void ajouterClient(Client client) {
		this.getClients().add(client);
	}

	public String afficherClients() {
		String texte = "";
		int count = 1;
		for (Client client : this.clients) {
			texte += count++ + "[ " + "ID : " + client.getID() + " - Nom : " + client.getNom() + " - Prénom : "
					+ client.getPrenom() + " - Code promotionnel : " + client.getCodePromotionnel() + " ] \n";
		}
		return texte;
	}

	public Client chercheClient(String idClient) {

		Iterator<Client> it = this.getClients().iterator();
		while (it.hasNext()) {
			Client current = it.next();
			if (idClient.equals(current.getID())) {

				ConsoleHelper.display("Client trouvé ! ");
				return current;
			}
		}
		return null;
	}

}