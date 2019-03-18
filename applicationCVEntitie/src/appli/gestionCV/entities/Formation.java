package appli.gestionCV.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Formation extends Diplome{

	private static final long serialVersionUID = -2330396325158666527L;
	
	private String ville;
	
	private Pays pays;
	
	private boolean obtention;
	
	private Mention mention;
}
