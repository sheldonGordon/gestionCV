package appli.gestionCV.init;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import appli.gestionCV.entities.Role;
import appli.gestionCV.facades.RoleFacade;
import utils.LogUtils;

@Singleton
@Startup
public class InitSingleton {
	@Inject
	private RoleFacade roleFacade;

	@PostConstruct
	public void init() {
		LogUtils.info("----------------------------------------------");
		LogUtils.info("-------INITIALISATION DE L'APPLICATION--------");
		LogUtils.info("----------------------------------------------");
		
		Role r1 = roleFacade.newInstance();
		Role r2 = roleFacade.newInstance();
		Role r3 = roleFacade.newInstance();

		r1.setLibelle("UTILISATEUR");
		r2.setLibelle("VISITEUR");
		r3.setLibelle("ADMINISTRATEUR");

		roleFacade.createIfNotExist(r1);
		roleFacade.createIfNotExist(r2);
		roleFacade.createIfNotExist(r3);
	}
}
