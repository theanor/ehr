package be.monolith.ehr.iam;

@SuppressWarnings("serial")
public class AuthenticationServiceException extends Exception {

	public AuthenticationServiceException() {
		super();
	}

	public AuthenticationServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthenticationServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationServiceException(String message) {
		super(message);
	}

	public AuthenticationServiceException(Throwable cause) {
		super(cause);
	}

}
