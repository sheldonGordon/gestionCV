package appli.gestionCV.factories;

import appli.gestionCV.entities.Curriculum;

public final class CurriculumFactory {

	public static Curriculum newInstance(){
		return new Curriculum();
	}
}