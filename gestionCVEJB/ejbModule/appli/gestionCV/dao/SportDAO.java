package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Sport;

@Stateless
public class SportDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Sport s) {
		DaoTools.create(em, s);
	}

	public void update(Sport s) {
		DaoTools.update(em, s);
	}
	
	public void delete(Sport s) {
		DaoTools.delete(em, Sport.class, s);
	}
	
	public List<Sport> getAll() {
		return DaoTools.readAll(em, Sport.class);
	}
}
