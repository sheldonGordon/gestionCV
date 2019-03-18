package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Experience;

@Stateless
public class ExperienceDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Experience e) {
		DaoTools.create(em, e);
	}

	public void update(Experience e) {
		DaoTools.update(em, e);
	}
	
	public void delete(Experience e) {
		DaoTools.delete(em, Experience.class, e);
	}
	
	public List<Experience> getAll() {
		return DaoTools.readAll(em, Experience.class);
	}
}
