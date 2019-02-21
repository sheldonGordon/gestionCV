package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Fonction;

@Stateless
public class FonctionDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Fonction f) {
		DaoTools.create(em, f);
	}

	public void update(Fonction f) {
		DaoTools.update(em, f);
	}
	
	public void delete(Fonction f) {
		DaoTools.delete(em, Fonction.class, f);
	}
	
	public List<Fonction> getAll() {
		return DaoTools.readAll(em, Fonction.class);
	}
}
