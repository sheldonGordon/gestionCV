package appli.gestionCV.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Mention extends AbstractEntity{
	
	private static final long serialVersionUID = 7490018451137095089L;

	private String libelle;
}
