package models.typeImpression;

public class TypeImpressionBis {

	private int id ;
	private String ref;
	private String email;
	
	@Override
	public String toString() {
		return "TypeImpression [id=" + id + ", ref=" + ref + ", email=" + email + "]";
	}

	public TypeImpressionBis(int id,String ref , String email) {
		this.id = id;
		this.ref = ref ;
		this.email = email ;
	}
}
