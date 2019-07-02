package fr.epita.quiz.exception;

public class DataAccessException extends Exception {
	
	private static final long serialVersionUID = -8446384785560273367L;


	public Object getFaultInstance() {
		return faultInstance;
	}

	private final Object faultInstance;
	
	
	public DataAccessException(Object faultInstance) {
		this.faultInstance = faultInstance;
	}
	
	public DataAccessException(Object faultInstance, Exception initialCause) {
		this.faultInstance = faultInstance;
		this.initCause(initialCause);
	}


}
