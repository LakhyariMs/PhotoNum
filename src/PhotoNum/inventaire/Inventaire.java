package PhotoNum.inventaire;

public class Inventaire {

	private ReferenceInventaire reference;
	private int qte;
	private float prixDeVente;
	
	// Constructeur --------------------------------------------------------------

	public Inventaire(ReferenceInventaire reference , int qte , float prixVente ) {
		this.reference = reference;
		this.qte = qte ;
		this.prixDeVente = prixVente ;
	}

	// Setters & Getters --------------------------------------------------------------
	
	public ReferenceInventaire getReference() {
		return reference;
	}

	public void setReference(ReferenceInventaire reference) {
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
	

	
	
}
