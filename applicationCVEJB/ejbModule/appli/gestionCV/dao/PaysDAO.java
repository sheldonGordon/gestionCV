package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Pays;

@Stateless
public class PaysDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Pays p) {
		DaoTools.create(em, p);
	}

	public void update(Pays p) {
		DaoTools.update(em, p);
	}
	
	public void delete(Pays p) {
		DaoTools.delete(em, Pays.class, p);
	}
	
	public List<Pays> getAll() {
		return DaoTools.readAll(em, Pays.class);
	}
}
