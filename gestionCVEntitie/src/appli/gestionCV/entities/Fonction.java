package appli.gestionCV.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Fonction extends AbstractEntity{

	private static final long serialVersionUID = 8713160833333372777L;

	private String libelle;
}
