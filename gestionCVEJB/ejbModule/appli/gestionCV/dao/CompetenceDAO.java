package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Competence;

@Stateless
public class CompetenceDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Competence c) {
		DaoTools.create(em, c);
	}

	public void update(Competence c) {
		DaoTools.update(em, c);
	}
	
	public void delete(Competence c) {
		DaoTools.delete(em, Competence.class, c);
	}
	
	public List<Competence> getAll() {
		return DaoTools.readAll(em, Competence.class);
	}
}
