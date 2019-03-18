package appli.gestionCV.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Role;

@Stateless
public class RoleDAO{

	@PersistenceContext
	private EntityManager em;

	public void create(Role r) {
		DaoTools.create(em, r);
	}

	public void update(Role r) {
		DaoTools.update(em, r);
	}
	
	public void delete(Role r) {
		DaoTools.delete(em, Role.class, r);
	}
	
	public List<Role> getAll() {
		return DaoTools.readAll(em, Role.class);
	}

	public void createIfNotExist(Role role) {
		DaoTools.createIfNotExist(em, Role.class, role, "libelle");
	}

	public Role getRoleByLibelle(String libelle) {
		Map<String, String> args = new HashMap<>();
		
		args.put("libelle", libelle);
	
		List<Role> roles = DaoTools.findAllByNamedQuery(em, Role.class, "ROLE.getRoleByLibelle");
		
		return roles.get(0);
	}
}
