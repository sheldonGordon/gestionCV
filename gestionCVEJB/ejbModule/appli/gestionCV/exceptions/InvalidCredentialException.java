package appli.gestionCV.exceptions;

@SuppressWarnings("serial")
public class InvalidCredentialException extends GestionCVException {

	public InvalidCredentialException() {
		super();
	}

	public InvalidCredentialException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidCredentialException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCredentialException(String message) {
		super(message);
	}

	public InvalidCredentialException(Throwable cause) {
		super(cause);
	}

}
