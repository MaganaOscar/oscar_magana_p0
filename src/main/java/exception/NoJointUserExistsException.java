package exception;

public class NoJointUserExistsException extends Exception{
	
	@Override
	public String getMessage() {
		return "No joint user exists for this account!";
	}
}
