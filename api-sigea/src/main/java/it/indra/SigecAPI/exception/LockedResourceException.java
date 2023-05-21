package it.indra.SigecAPI.exception;


public class LockedResourceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public LockedResourceException(String errore) {
		super(errore);
	}
}
