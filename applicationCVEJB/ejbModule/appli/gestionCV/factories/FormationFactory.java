package appli.gestionCV.factories;

import appli.gestionCV.entities.Formation;

public final class FormationFactory {

	public static Formation newInstance(){
		return new Formation();
	}
}