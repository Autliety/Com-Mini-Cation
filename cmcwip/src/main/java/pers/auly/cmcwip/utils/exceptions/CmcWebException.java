package pers.auly.cmcwip.utils.exceptions;

import java.util.Optional;

public class CmcWebException extends RuntimeException {
    
    private String reason = null;
    
    CmcWebException() {
    }
    
    public CmcWebException(String message) {
        super(message);
    }
    
    public CmcWebException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CmcWebException reason(String reason) {
        this.reason = reason;
        return this;
    }
    
    public String getReason() {
        return Optional.of(reason)
            .orElse("Unknown server error. ");
    }
}
