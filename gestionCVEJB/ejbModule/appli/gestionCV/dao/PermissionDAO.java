package appli.gestionCV.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Permission;

@Stateless
public class PermissionDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Permission p) {
		DaoTools.create(em, p);
	}

	public void update(Permission p) {
		DaoTools.update(em, p);
	}
	
	public void delete(Permission p) {
		DaoTools.delete(em, Permission.class, p);
	}
	
	public List<Permission> getAll() {
		return DaoTools.readAll(em, Permission.class);
	}
}
