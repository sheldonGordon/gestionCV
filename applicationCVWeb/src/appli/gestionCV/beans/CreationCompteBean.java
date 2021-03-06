package appli.gestionCV.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import appli.gestionCV.entities.Compte;
import appli.gestionCV.entities.Personne;
import appli.gestionCV.entities.Role;
import appli.gestionCV.facades.CompteFacade;
import appli.gestionCV.facades.PersonneFacade;
import appli.gestionCV.facades.RoleFacade;
import appli.gestionCV.sessionBeans.SessionBean;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CreationCompteBean implements Serializable{

	private static final long serialVersionUID = -1310591877235822848L;

	@Getter
	@Setter
	private Compte compte;
	
	@Getter
	@Setter
	private Personne personne;

	@Getter
	private String confirmationMdp;
	
	@Getter
	private String mdp;

	@Inject
	private CompteFacade compteFacade;
	
	@Inject
	private PersonneFacade personneFacade;
	
	@Inject
	private RoleFacade roleFacade;
	
	@Inject
	private SessionBean session;
	
	@PostConstruct
	public void init() {
		if(session.isConnecter()) {			
			redirectionModification();
		}else {
			compte = compteFacade.newInstance();
			personne = personneFacade.newInstance();
		}		
	}
	
	public void creation(){		
		List<Role> roles = new ArrayList<>(0);

		roles.add(roleFacade.getRoleByLibelle("UTILISATEUR"));
		roles.add(roleFacade.getRoleByLibelle("VISITEUR"));
		
		compte.setListeRole(roles);
		
		if(confirmationMdp.equals(mdp)) {
			compte.setPassword(mdp);
		}else {
			String message = "Les mots de passe ne sont pas identiques.";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return;
		}
		
		compteFacade.create(compte);
		
		personne.setCompte(compte);
		
		personneFacade.create(personne);
		
		redirectionLogin();				
	}

	private void redirectionModification() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

		try {
			response.sendRedirect("./modifier.xhtml");
		} catch (IOException e) {
			String message = "Une erreur est survenue pendant la redirection.";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
	}
	private void redirectionLogin() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

		try {
			response.sendRedirect("../login/login.xhtml");
		} catch (IOException e) {
			String message = "Une erreur est survenue pendant la redirection.";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
	}

	public void setConfirmationMdp(String pass) {
		this.confirmationMdp = compteFacade.getSHA512(pass);
	}
	
	public void setMdp(String pass) {
		this.mdp = compteFacade.getSHA512(pass);
	}
}
