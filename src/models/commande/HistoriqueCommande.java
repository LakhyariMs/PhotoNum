package models.commande;

import java.sql.Date;

public class HistoriqueCommande extends Commande {

	public HistoriqueCommande(int id, Date date, String modeLiv, float prix, int paye , String mail) {
		// Normalement chaque commande stockee dans la table historique est deja validé (envoyé) un trigger doit s'occuper de ca 
		super(id, date, modeLiv, prix, "En cours", paye,mail);
	}
/*	
	public HistoriqueCommande(Commande cmd ) {
		super(cmd.getId(),cmd.getDate(), cmd.getModeLivraison(), cmd.getPrixTotale(),null, cmd.estPaye());
	}
*/
}
