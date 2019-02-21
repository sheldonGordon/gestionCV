package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Certification;

@Stateless
public class CertificationDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Certification c) {
		DaoTools.create(em, c);
	}

	public void update(Certification c) {
		DaoTools.update(em, c);
	}
	
	public void delete(Certification c) {
		DaoTools.delete(em, Certification.class, c);
	}
	
	public List<Certification> getAll() {
		return DaoTools.readAll(em, Certification.class);
	}
}
