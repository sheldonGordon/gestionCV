package appli.gestionCV.init;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import utils.LogUtils;

@Singleton
@Startup
public class InitSingleton {

	@PostConstruct
	public void init() {
		LogUtils.info("----------------------------------------------");
		LogUtils.info("-------INITIALISATION DE L'APPLICATION--------");
		LogUtils.info("----------------------------------------------");
	}
}
