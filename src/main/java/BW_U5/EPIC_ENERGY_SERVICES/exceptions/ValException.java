package BW_U5.EPIC_ENERGY_SERVICES.exceptions;


import java.util.List;

public class ValException extends RuntimeException {
    private List<String> errorsMessages;

        public ValException(List<String> errorsMessages) {

            super("Ci sono stati errori nel payload");
            this.errorsMessages = errorsMessages;
        }

    public List<String> getErrorsMessages() {
        return errorsMessages;
    }
}
