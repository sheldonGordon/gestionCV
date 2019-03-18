package appli.gestionCV.facades;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import appli.gestionCV.dao.PersonneDAO;
import appli.gestionCV.entities.Personne;
import appli.gestionCV.factories.PersonneFactory;

@Stateless
public class PersonneFacade implements Serializable{
	
	private static final long serialVersionUID = 8320620266557785685L;
	@Inject
	private PersonneDAO personneDAO;
	
	public Personne newInstance() {
		return PersonneFactory.newInstance();
	}	

	public void createIfNotExist(Personne personne) {
		if(personne == null) {
			//TODO gestion erreur, pas runtime mais une exception créer
			throw new RuntimeException();
		}
		
		personneDAO.createIfNotExist(personne);
	}

	public void create(Personne personne) {
		personneDAO.create(personne);
	}
}
