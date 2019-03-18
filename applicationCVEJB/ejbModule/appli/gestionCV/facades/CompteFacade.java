package appli.gestionCV.facades;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import appli.gestionCV.dao.CompteDAO;
import appli.gestionCV.entities.Compte;
import appli.gestionCV.exceptions.InvalidCredentialException;
import appli.gestionCV.factories.CompteFactory;

@Stateless
public class CompteFacade implements Serializable{
	
	private static final long serialVersionUID = 8320620266557785685L;
	@Inject
	private CompteDAO compteDAO;
	
	public Compte newInstance() {
		return CompteFactory.newInstance();
	}	

	public Compte searchCompte(Compte compte) throws InvalidCredentialException {
		if(compte == null) {
			//TODO gestion erreur, pas runtime mais une exception créer
			throw new RuntimeException();
		}
		
		compte.setPassword(getSHA512(compte.getPassword()));
		
		Compte compteReturn = compteDAO.searchCompte(compte);
		
		if(compteReturn == null) {
			throw new InvalidCredentialException("Utilisateur ou mot de passe invalide.");
		}
		
		return compteReturn;
	}

	public void createIfNotExist(Compte compte) {
		if(compte == null) {
			//TODO gestion erreur, pas runtime mais une exception créer
			throw new RuntimeException();
		}
		
		compteDAO.createIfNotExist(compte);
	}
	
	public String getSHA512(String password) {
		String generatedPassword = password;
		for (int i = 0; i < 3000; i++) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-512");
				byte[] bytes = md.digest(generatedPassword.getBytes(StandardCharsets.UTF_8));
				StringBuilder sb = new StringBuilder();
				for (byte b : bytes) {
					sb.append(String.format("%02x", b));
				}
				generatedPassword = sb.toString();
			}catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return generatedPassword;
	}

	public void create(Compte compte) {
		compteDAO.create(compte);
	}
}
