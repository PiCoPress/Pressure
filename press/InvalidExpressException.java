package Pressure.press;


public class InvalidExpressException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5745515707366185895L;
	
	public InvalidExpressException(String InvalidValue) {
		super(InvalidValue + " : Number Expression is wrong.");
	}
}
