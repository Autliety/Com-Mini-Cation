package pers.auly.cmcwip.utils.exceptions;

public class ForbiddenException extends CmcWebException {
    
    public ForbiddenException() {
        reason("Operation is denied by server. ");
    }
    
}
