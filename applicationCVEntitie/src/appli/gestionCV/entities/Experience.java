package appli.gestionCV.entities;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Experience extends AbstractEntity{

	private static final long serialVersionUID = 5323053308960131085L;

	private String libelle;
	
	private String entreprise;
	
	private String poste;
	
	private LocalDate dateDebut;
	
	private LocalDate dateFin;
	
	private String descriptionPoste;
	
	private List<Fonction> listeFonction;
}
