package pers.auly.cmcwip.utils.exceptions;

public class NotFoundException extends CmcWebException {
    
    public NotFoundException() {
        reason("Resources not found. ");
    }
    
}
