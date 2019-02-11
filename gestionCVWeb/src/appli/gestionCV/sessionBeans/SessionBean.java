package appli.gestionCV.sessionBeans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Getter;

@SessionScoped
@Named
public class SessionBean implements Serializable{
	
	private static final long serialVersionUID = -5895035546239998943L;
	
	@Getter
	private boolean connecter;	
	
	public SessionBean() {
		deconnecter();
	}
	
	public void deconnecter() {
		connecter = false;
	}
	
	public void connecter() {
		connecter = true;
	}
}
