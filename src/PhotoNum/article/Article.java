package PhotoNum.article;


public class Article {
	
	private int id ;
	private int qte ;
	private float prix ;
	
	// Constructeur--------------------------------------------------------------
	
	public Article(int id , int qte , float prix) {
		this.id = id ;
		this.qte = qte ;
		this.prix = prix ;
	}

	// Setters & Getters--------------------------------------------------------------

	public int getId() {
		return id;
	}

	public int getQte() {
		return qte;
	}

	public float getPrix() {
		return prix;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", qte=" + qte + ", prix=" + prix + "]";
	}
	
	
	

}
