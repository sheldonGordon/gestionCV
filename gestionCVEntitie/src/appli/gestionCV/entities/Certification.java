package appli.gestionCV.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Certification extends Diplome{

	private static final long serialVersionUID = 5019363539106315704L;
	
	private String reference;
}
