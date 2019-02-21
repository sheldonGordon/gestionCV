package appli.gestionCV.entities;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Curriculum extends AbstractEntity{
	
	private static final long serialVersionUID = 5865817561849285520L;
	
	private String libelle;
	
	private String photo;
	
	private String titre;
	
	private Personne personne;
	
	private List<Formation> listeFormation;
	
	private List<Experience> listeExperience;
	
	private List<Certification> listeCertification;
	
	private List<Competence> listeCompetence;
	
	private List<Sport> listeSport;
	
	private List<CentreInteret> listeCentreInteret;
}
