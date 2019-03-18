package appli.gestionCV.factories;

import appli.gestionCV.entities.Competence;

public final class CertificationFactory {

	public static Competence newInstance(){
		return new Competence();
	}
}