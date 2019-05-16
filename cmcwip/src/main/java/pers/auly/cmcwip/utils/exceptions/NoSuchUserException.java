package pers.auly.cmcwip.utils.exceptions;

public class NoSuchUserException extends CmcWebException {
    
    public NoSuchUserException() {
        reason("No such user signed up. ");
    }
}
