package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Curriculum;

@Stateless
public class CurriculumDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Curriculum c) {
		DaoTools.create(em, c);
	}

	public void update(Curriculum c) {
		DaoTools.update(em, c);
	}
	
	public void delete(Curriculum c) {
		DaoTools.delete(em, Curriculum.class, c);
	}
	
	public List<Curriculum> getAll() {
		return DaoTools.readAll(em, Curriculum.class);
	}
}
