package pers.auly.cmcwip.exceptions;

public class PermissionDeniedException extends CmcWebException {
    
    public PermissionDeniedException() {
        setReason("Operation is denied by server");
    }
}
