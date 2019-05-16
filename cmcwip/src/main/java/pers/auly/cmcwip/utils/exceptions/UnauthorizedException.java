package pers.auly.cmcwip.utils.exceptions;

public class UnauthorizedException extends CmcWebException {
    
    public UnauthorizedException() {
        reason("Current user is not authorized. ");
    }
}
