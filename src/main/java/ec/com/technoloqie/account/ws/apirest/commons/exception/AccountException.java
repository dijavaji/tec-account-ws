package ec.com.technoloqie.account.ws.apirest.commons.exception;

public class AccountException extends RuntimeException{
	
	public AccountException() {
        super();
    }
    
	public AccountException (String msg, Throwable nested) {
        super(msg, nested);
    }
    
	public AccountException (String message) {
        super(message);
    }
    
	public AccountException(Throwable nested) {
        super(nested);
	}
	
	private static final long serialVersionUID = 1L;

}