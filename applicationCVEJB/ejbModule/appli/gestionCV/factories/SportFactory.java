package appli.gestionCV.factories;

import appli.gestionCV.entities.Sport;

public final class SportFactory {

	public static Sport newInstance(){
		return new Sport();
	}
}