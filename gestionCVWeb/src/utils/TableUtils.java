package utils;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TableUtils implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//permet de faire afficher ou non un tableau
	public static boolean tableRender( List<?> liste ) {
		return ( liste != null && liste.size() != 0);
	}
}
