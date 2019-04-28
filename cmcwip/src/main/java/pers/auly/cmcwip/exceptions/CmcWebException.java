package pers.auly.cmcwip.exceptions;

public class CmcWebException extends RuntimeException {
    
    private final String reason;
    
    public CmcWebException(String reason) {
        super(reason);
        this.reason = reason;
    }
    
    public CmcWebException(String message, String reason) {
        super(message);
        this.reason = reason;
    }
    
    public CmcWebException(Throwable cause, String reason) {
        super(cause);
        this.reason = reason;
    }
    
    public CmcWebException(String message, Throwable cause, String reason) {
        super(message, cause);
        this.reason = reason;
    }
    
    public String getReason() {
        return reason;
    }
}
