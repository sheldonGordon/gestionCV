package appli.gestionCV.factories;

import appli.gestionCV.entities.Experience;

public final class ExperienceFactory {

	public static Experience newInstance(){
		return new Experience();
	}
}