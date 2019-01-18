package PhotoNum.fichiers;

public class Photo {
	
	private int id ;
	private String parametre ;
	private String informations;
	
	// Constructeur--------------------------------------------------------------

	public Photo(int id , String param , String infos ) {
		this.id = id ;
		this.parametre = param ;
		this.informations = infos ;
	}
	
	// Setters & getters --------------------------------------------------------------

	public int getId() {
		return id;
	}

	public String getParametre() {
		return parametre;
	}

	public void setParametre(String parametre) {
		this.parametre = parametre;
	}

	public String getInformations() {
		return informations;
	}

	public void setInformations(String informations) {
		this.informations = informations;
	}
	

	
}
