package appli.gestionCV.factories;

import appli.gestionCV.entities.Certification;

public final class CompetenceFactory {

	public static Certification newInstance(){
		return new Certification();
	}
}