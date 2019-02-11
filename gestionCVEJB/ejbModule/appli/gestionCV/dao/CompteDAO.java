package appli.gestionCV.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import appli.gestionCV.entities.Compte;
import appli.gestionCV.exceptions.InvalidCredentialException;

@Stateless
public class CompteDAO{

	@PersistenceContext
	private EntityManager em;

	public Compte searchCompte(Compte compte) throws InvalidCredentialException {
		Map<String, Object> args = new HashMap<String, Object>();
		
		args.put("login", compte.getLogin());
		args.put("password", compte.getPassword());
		
		List<Compte> listeCompte = DaoTools.findAllByNamedQuery(em, Compte.class, "COMPTE.logOn", args);

		if (listeCompte.isEmpty()) {
			throw new InvalidCredentialException("Utilisateur ou mot de passe invalide.");
		}
		return listeCompte.get(0);
	}

	public void createIfNotExist(Compte compte) {
		DaoTools.createIfNotExist(em, Compte.class, compte, "login");
	}
}
