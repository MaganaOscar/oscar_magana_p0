package exception;

public class NoBankAccountException extends Exception{

	@Override
	public String getMessage() {
		return "No bank accounts exist for this user!";
	}
	
}
