package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Personne;

@Stateless
public class PersonneDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Personne p) {
		DaoTools.create(em, p);
	}

	public void update(Personne p) {
		DaoTools.update(em, p);
	}
	
	public void delete(Personne p) {
		DaoTools.delete(em, Personne.class, p);
	}
	
	public List<Personne> getAll() {
		return DaoTools.readAll(em, Personne.class);
	}

	public void createIfNotExist(Personne personne) {
		DaoTools.createIfNotExist(em, Personne.class, personne, "nom", "prenome");
	}
}
