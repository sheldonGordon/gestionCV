package appli.gestionCV.exceptions;

@SuppressWarnings("serial")
public class GestionCVException extends Exception {

	public GestionCVException() {
		super();
	}

	public GestionCVException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GestionCVException(String message, Throwable cause) {
		super(message, cause);
	}

	public GestionCVException(String message) {
		super(message);
	}

	public GestionCVException(Throwable cause) {
		super(cause);
	}
	
	

}
