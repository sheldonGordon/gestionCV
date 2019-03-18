package appli.gestionCV.factories;

import appli.gestionCV.entities.Personne;

public final class PersonneFactory {

	public static Personne newInstance(){
		return new Personne();
	}
}