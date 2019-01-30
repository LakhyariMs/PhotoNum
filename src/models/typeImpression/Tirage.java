package models.typeImpression;

public class Tirage extends TypeImpression {

	public enum FormatQualite {
		A, B, C
	}
	
	private FormatQualite format;
	private int quantite;
	
	public Tirage(int id, FormatQualite format, int quantite) {
		super(id);
		this.format = format;
		this.quantite = quantite;
	}

	public FormatQualite getFormatQualite() {
		return format;
	}
	
	public int getQuantiteTirage() {
		return quantite;
	}
}
