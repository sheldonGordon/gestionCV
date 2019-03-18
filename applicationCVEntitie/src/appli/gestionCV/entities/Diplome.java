package appli.gestionCV.entities;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Diplome extends AbstractEntity{
	
	private static final long serialVersionUID = -2330396325158666527L;

	private String etablissement;
	
	private String libelle;
	
	private LocalDate dateDebut;
	
	private LocalDate dateFin;
}
