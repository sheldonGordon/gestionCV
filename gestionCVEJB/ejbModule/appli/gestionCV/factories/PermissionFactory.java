package appli.gestionCV.factories;

import appli.gestionCV.entities.Permission;

public final class PermissionFactory {

	public static Permission newInstance(){
		return new Permission();
	}
}