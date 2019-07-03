package fr.epita.quiz.exception;

/**
 * 
 * @author Krishna, Abhigna
 *
 */
public class CreateFailedException extends DataAccessException{


	private static final long serialVersionUID = -925222541305304182L;

	public CreateFailedException(Object beanThatWasNotCreated) {
		super(beanThatWasNotCreated);
	}
	
	public CreateFailedException(Object beanThatWasNotCreated, Exception initialCause) {
		super(beanThatWasNotCreated, initialCause);
	}

}
