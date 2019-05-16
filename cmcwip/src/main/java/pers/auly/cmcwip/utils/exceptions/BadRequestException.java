package pers.auly.cmcwip.utils.exceptions;

public class BadRequestException extends CmcWebException {
    
    public BadRequestException() {
        reason("Request param error. ");
    }
}
