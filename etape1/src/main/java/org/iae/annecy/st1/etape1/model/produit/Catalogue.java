package org.iae.annecy.st1.etape1.model.produit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Catalogue {
		private ArrayList <Produit> produits = new ArrayList <Produit>();
		public ArrayList <Produit> getProduits(){
			return this.produits;
		}
		public void ajouterProduit (Produit produit){
			this.getProduits().add(produit);
			}
		public String afficherProduits(){//ajouter désérialisation
			try
		        {
		            FileInputStream fis = new FileInputStream("serializedcatalogue");
		            ObjectInputStream ois = new ObjectInputStream(fis);
		            ois.close();
		            fis.close();
		         }catch(IOException ioe){
		             System.out.println("Le fichier n'existe pas");             
		          }
		        for(Produit tmp: produits){
		            System.out.println(tmp);
		        }
		
			String texte="";
			int count = 1;
				for (Produit produit : this.produits){
				texte += count++ +" [ "+"Reférence : "+produit.getReference() + " - Nom : "+produit.getNom()
				+" - Description : "+produit.getDescription() + " - Description longue : "+produit.getDescriptionLongue()
				+" - Prix : "+produit.getPrix()+"] \n";
				}
				return texte;
				 
		}
					
		public Produit chercheProduit(String ref){
			Iterator <Produit> it = this.getProduits().iterator();
			while (it.hasNext()){
				Produit current = it.next();
				if(ref.equals (current.getReference())){
					System.out.println("Produit trouvé : ");
					return current;
				}
			}
			return null;
		}
		
		public boolean referenceExiste (String reference){
			boolean bool = false;
			for (Produit p : produits)
			{
				if(reference.equals(p.getReference()))
				bool = true;
			}
		return bool;
		}
		
}



