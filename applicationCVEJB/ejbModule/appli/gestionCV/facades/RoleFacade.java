package appli.gestionCV.facades;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import appli.gestionCV.dao.RoleDAO;
import appli.gestionCV.entities.Role;
import appli.gestionCV.factories.RoleFactory;

@Stateless
public class RoleFacade implements Serializable{
	
	private static final long serialVersionUID = 8320620266557785685L;
	@Inject
	private RoleDAO roleDAO;
	
	public Role newInstance() {
		return RoleFactory.newInstance();
	}	

	public void createIfNotExist(Role role) {
		if(role == null) {
			//TODO gestion erreur, pas runtime mais une exception créer
			throw new RuntimeException();
		}
		
		roleDAO.createIfNotExist(role);
	}
	
	public Role getRoleByLibelle(String libelle) {
		return roleDAO.getRoleByLibelle(libelle);
	}
	
}
