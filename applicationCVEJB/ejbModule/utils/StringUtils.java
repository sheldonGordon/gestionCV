package utils;

import java.util.List;

public class StringUtils {

	private StringUtils(){
		//protection contre l'instanciationS
	}
	
	/**
	 * Fourni une chaine de caractères à partir des elements de la liste.
	 * Le ToString des elements de la liste sera appelé.
	 * @param liste
	 * @param separator
	 * @return
	 */
	public static String stringFromList(List<?> liste){
		return stringFromList(liste, "");
	}
	
	/**
	 * Fourni une chaine de caractères avec les elements de la liste espacés d'un sépérateur
	 * Le ToString des elements de la liste sera appelé.
	 * @param liste
	 * @param separator
	 * @return
	 */
	public static String stringFromList(List<?> liste, String separator){
		String s = "";
		String sTmp = "";
		
		int nb = liste.size();
		
		for (int i = 0; i < nb ; i++) {
			if(i< nb-1 ){
				sTmp = String.format("%s %s ", liste.get(i), separator);
			}else{
				sTmp = String.format("%s", liste.get(i));
			}
			s = s.concat( sTmp );
		}
		
		return s;
	}
	
	/**
	 * Retoure la  prévisualisation d'une chaine de caracteres selon une taille définie.
	 * Cette chaine se termine par '...'
	 * @param s
	 * @param longeur
	 * @return
	 */
	public static String stringPrewiev(String phrase, int longueur){
		String s = phrase;
		
		if( phrase.length() > longueur ){
			s = phrase.substring(0, longueur);
			s = s.concat( "..." );
		}
		
		return s;
	}
	
}
