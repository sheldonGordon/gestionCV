package appli.gestionCV.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import appli.gestionCV.entities.Compte;
import appli.gestionCV.sessionBeans.SessionBean;
import appli.gestionCV.exceptions.GestionCVException;
import appli.gestionCV.facades.CompteFacade;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = -1310591877235822848L;

	@Getter
	@Setter
	private Compte compte;
	
	@Inject
	private CompteFacade compteFacade;
	
	@Inject
	private SessionBean session;
	
	@PostConstruct
	public void init() {
		if(session.isConnecter()) {			
			redirectionConsultation();
		}else {
			compte = compteFacade.newInstance();
		}		
	}
	
	/**
	 * Méthode appelé apres validation du bouton connecter
	 */
	public void logOn(){		
		try {
			compte = compteFacade.searchCompte(compte);	
			
			session.connecter();
			
			redirectionConsultation();			
		} catch (GestionCVException e) {
			String message = e.getMessage();
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}	
	}
	
	private void redirectionConsultation() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

		try {
			response.sendRedirect("../consultation/consulter.xhtml");
		} catch (IOException e) {
			String message = "Une erreur est survenue pendant la redirection.";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
	}
}
