package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Formation;

@Stateless
public class FormationDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Formation f) {
		DaoTools.create(em, f);
	}

	public void update(Formation f) {
		DaoTools.update(em, f);
	}
	
	public void delete(Formation f) {
		DaoTools.delete(em, Formation.class, f);
	}
	
	public List<Formation> getAll() {
		return DaoTools.readAll(em, Formation.class);
	}
}
