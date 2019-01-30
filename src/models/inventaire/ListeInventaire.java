package models.inventaire;

import java.util.ArrayList;
import java.util.List;

public class ListeInventaire {
	private List<Inventaire> inventaire = new ArrayList<Inventaire>();
	
	// Constructeur --------------------------------------------------------------
	
	public ListeInventaire(List<Inventaire> inventaire) {
		this.inventaire = inventaire;
	}
	
	// Setters & Getters --------------------------------------------------------------

	public List<Inventaire> getInventaire() {
		return inventaire;
	}

	public void setInventaire(List<Inventaire> inventaire) {
		this.inventaire = inventaire;
	}

	// Inventaire --------------------------------------------------------------
	
	public void addToInventaire(Inventaire invetaire ) {
		this.inventaire.add(invetaire);
	}
	
	public boolean removeFromInventaire(Inventaire inventaire) {
		if(this.inventaire.remove(inventaire))
			return true ;
		return false;
	}
	
	/**
	 *  Modifier la qte d'un element dans l'inventaire
	 * @param inventaire
	 * @param qte
	 * @return
	 */
	public boolean setInventaireQte (Inventaire inventaire , int qte ) {
		for (Inventaire in : this.inventaire) {
			if(in.equals(inventaire)) {
				in.setQte(qte);
				return true ;
			}
		}
		return false ;
	}
	
	/**
	 * Modifier le prix d'un element dans l'inventaire
	 * @param inventaire
	 * @param prix
	 * @return
	 */
	public boolean setInventairePrix (Inventaire inventaire , float prix ) {
		for (Inventaire in : this.inventaire) {
			if(in.equals(inventaire)) {
				in.setPrixDeVente(prix);
				return true ;
			}
		}
		return false ;
	}
	
	/**
	 * Modifier la qte et le prix d'un element dans l'inventaire
	 * @param inventaire
	 * @param qte
	 * @param prix
	 * @return
	 */
	public boolean setInventairePrixAndQte (Inventaire inventaire ,int qte , float prix ) {
		for (Inventaire in : this.inventaire) {
			if(in.equals(inventaire)) {
				in.setQte(qte);
				in.setPrixDeVente(prix);
				return true ;
			}
		}
		return false ;
	}
	
	
}
