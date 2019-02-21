package appli.gestionCV.entities;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Role extends AbstractEntity{

	private static final long serialVersionUID = -4589978508449414170L;

	private String libelle;
	
	private String description;
	
	private List<Permission> listePermission;
}
