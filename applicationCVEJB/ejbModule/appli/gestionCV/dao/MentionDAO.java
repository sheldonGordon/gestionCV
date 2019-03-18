package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Mention;

@Stateless
public class MentionDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Mention m) {
		DaoTools.create(em, m);
	}

	public void update(Mention m) {
		DaoTools.update(em, m);
	}
	
	public void delete(Mention m) {
		DaoTools.delete(em, Mention.class, m);
	}
	
	public List<Mention> getAll() {
		return DaoTools.readAll(em, Mention.class);
	}
}
