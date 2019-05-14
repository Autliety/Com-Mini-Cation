package pers.auly.cmcwip.exceptions;

public class UserNotFoundException extends CmcWebException {
    
    public UserNotFoundException() {
        setReason("Can not found your user, please sign up first");
    }
}
