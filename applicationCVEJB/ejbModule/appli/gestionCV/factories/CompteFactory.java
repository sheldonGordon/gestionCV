package appli.gestionCV.factories;

import appli.gestionCV.entities.Compte;

public final class CompteFactory {

	public static Compte newInstance(){
		return new Compte();
	}
}