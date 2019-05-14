package pers.auly.cmcwip.exceptions;

import org.apache.logging.log4j.util.Strings;

public class CmcWebException extends RuntimeException {
    
    private String reason = Strings.EMPTY;
    
    CmcWebException() {
    }
    
    public CmcWebException(String message) {
        super(message);
    }
    
    public CmcWebException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CmcWebException setReason(String reason) {
        this.reason = reason;
        return this;
    }
    
    public String getReason() {
        return reason;
    }
}
