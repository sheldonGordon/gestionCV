package appli.gestionCV.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Permission extends AbstractEntity{

	private static final long serialVersionUID = -1183431460532207337L;

	private String libelle;
	
	private String description;
}
