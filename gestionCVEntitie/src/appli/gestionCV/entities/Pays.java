package appli.gestionCV.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pays extends AbstractEntity{
	
	private static final long serialVersionUID = 7109605528646170868L;

	private String nom;
}
