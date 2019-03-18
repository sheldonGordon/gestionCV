package utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* Classe utilitaire de logging.
* @author MCH MAURY Ludovic
*
*/
public final class LogUtils {
	
		private static Log log = LogFactory.getLog(LogUtils.class);
		
		private LogUtils(){
			//protection contre l'instanciation
		}
		
		//-- LOGGER INFO -----------------------------------------------------------------
		
		/**
		 * Affichage d'un message d'info
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void info(Object texte){
			afficherInfo( getCallingMethod(), texte.toString() , (Object[]) null);
		}
		
		/**
		 * Affichage d'un message d'info paramétré
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void info (String pattern, Object... args){
			afficherInfo(getCallingMethod(),pattern, args);
		}
		
		/**
		 * Méthode d'affichage d'une info
		 * @author MCH MAURY Ludovic
		 * @param classeAppelante
		 * @param pattern
		 * @param args
		 */
		private static void afficherInfo(String classeAppelante, String pattern, Object... args ){
			if(log.isInfoEnabled()){
				log.info( stringFormat(classeAppelante ,pattern, args));
			}
		}

		//-- LOGGER DEBUG -----------------------------------------------------------------
		/**
		 * Affichage d'un message de debug
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void debug(Object texte){
			afficherDebug(getCallingMethod(), texte.toString() , (Object[]) null);
		}
		
		/**
		 * Affichage d'un message de debug paramétré
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void debug (String pattern, Object... args){
			afficherDebug(getCallingMethod(),pattern, args);
		}
		
		/**
		 * Méthode d'affichage d'un debug
		 * @author MCH MAURY Ludovic
		 * @param classeAppelante
		 * @param pattern
		 * @param args
		 */
		private static void afficherDebug(String classeAppelante, String pattern, Object... args ){
			if(log.isDebugEnabled()){
				log.debug( stringFormat(classeAppelante ,pattern, args));
			}
		}
		
		//-- LOGGER ERROR -----------------------------------------------------------------
		/**
		 * Affichage d'un message d'erreur
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void error(Object texte){
			afficherError( getCallingMethod(), texte.toString(), (Object[]) null);
		}
		
		/**
		 * Affichage d'un message d'erreur
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void error(String pattern, Object... args){
			afficherError(getCallingMethod(),pattern, args);
		}
		
		/**
		 * Méthode d'affichage d'une erreur
		 * @author MCH MAURY Ludovic
		 * @param classeAppelante
		 * @param pattern
		 * @param args
		 */
		private static void afficherError(String classeAppelante, String pattern, Object... args ){
			if(log.isErrorEnabled()){
				log.error( stringFormat(classeAppelante ,pattern, args));
			}
		}
		
		
		//-- LOGGER TRACE -----------------------------------------------------------------
		
		/**
		 * Affichage d'une trace
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void trace(Object texte){
			afficherTrace( getCallingMethod(), texte.toString(), (Object[]) null);
		}
		
		/**
		 * Affichage d'une trace
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void trace(String pattern, Object... args){
			afficherTrace(getCallingMethod(),pattern, args);
		}
		
		/**
		 * Méthode d'affichage d'une trace
		 * @author MCH MAURY Ludovic
		 * @param classeAppelante
		 * @param pattern
		 * @param args
		 */
		private static void afficherTrace(String classeAppelante, String pattern, Object... args ){
			if(log.isTraceEnabled()){
				log.trace( stringFormat(classeAppelante ,pattern, args));
			}
		}
		
		//-- LOGGER FATAL -----------------------------------------------------------------
		
		/**
		 * Affichage d'une trace
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void fatal(Object texte){
			afficherFatal( getCallingMethod(), texte.toString(), (Object[]) null);
		}
		
		/**
		 * Affichage d'une trace
		 * @author MCH MAURY Ludovic
		 * @param texte
		 */
		public static void fatal(String pattern, Object... args){
			afficherFatal(getCallingMethod(),pattern, args);
		}
		
		/**
		 * Méthode d'affichage d'une trace
		 * @author MCH MAURY Ludovic
		 * @param classeAppelante
		 * @param pattern
		 * @param args
		 */
		private static void afficherFatal(String classeAppelante, String pattern, Object... args ){
			if(log.isFatalEnabled()){
				log.fatal( stringFormat(classeAppelante ,pattern, args));
			}
		}
	
		//====================================================================================
		// METHODES PRIVEES
		
		/**
		 * Formatte le message a afficher
		 * @author MCH MAURY Ludovic
		 * @param classe
		 * @param pattern
		 * @param args
		 * @return
		 */
		private static String stringFormat(String classe, String pattern, Object... args){
			String p = String.format( "%s ==> %s", classe,pattern );
			return String.format(p , args);
		}
		
		/**
		 * Récupération de la method appelante
		 * @author MCH MAURY Ludovic
		 * @return
		 * 	String : classe appelante
		 */
		private static String getCallingMethod() {
	        return getTrace(Thread.currentThread().getStackTrace(), 3);
	    }
	 
		/**
		 * Permet de récupérer à partir d'un {@link StackTraceElement} la source appelante.
		 * @author MCH MAURY Ludovic
		 * @param e
		 * 	Le stackTraceElement
		 * @param level
		 * 	Le niveau à analyser
		 * @return
		 * 	String : "package.classe.methode" appelante
		 */
	    private static String getTrace(StackTraceElement[] e, int level) {
	        if (e != null && e.length >= level) {
	            StackTraceElement s = e[level];
	            if (s != null) {
	                return String.format( "%s.%s", s.getClassName() , s.getMethodName());
	            }
	        }
	        return null;
	    }
}