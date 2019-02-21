package appli.gestionCV.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Personne extends AbstractEntity{
	
	private static final long serialVersionUID = 8037942927811101158L;

	private String libelle;
	
	private String nom;
	
	private String prenom;

	private String adresse;
	
	private Pays pays;
	
	private String telFixe;
	
	private String telMobile;
	
	private String mail;
	
	private String info;
	
	private Compte compte;
}
