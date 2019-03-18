package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.CentreInteret;

@Stateless
public class CentreInteretDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(CentreInteret ci) {
		DaoTools.create(em, ci);
	}

	public void update(CentreInteret ci) {
		DaoTools.update(em, ci);
	}
	
	public void delete(CentreInteret ci) {
		DaoTools.delete(em, CentreInteret.class, ci);
	}
	
	public List<CentreInteret> getAll() {
		return DaoTools.readAll(em, CentreInteret.class);
	}
}
