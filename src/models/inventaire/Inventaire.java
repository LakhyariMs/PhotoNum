package models.inventaire;

public class Inventaire {

	private String reference; // j'enleve l'enumeration temporairement pour tester 
	private int qte;
	private float prixDeVente;
	
	// Constructeur --------------------------------------------------------------

	public Inventaire(String reference , int qte , float prixVente ) {
		this.reference = reference;
		this.qte = qte ;
		this.prixDeVente = prixVente ;
	}

	// Setters & Getters --------------------------------------------------------------
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}


	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public float getPrixDeVente() {
		return prixDeVente;
	}

	public void setPrixDeVente(float prixDeVente) {
		this.prixDeVente = prixDeVente;
	}
	
	@Override
	public String toString() {
		return "Inventaire [reference=" + reference + ", qte=" + qte + ", prixDeVente=" + prixDeVente + "]";
	}

	
	
}
