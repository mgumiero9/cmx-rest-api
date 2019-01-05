package application.exception;

import org.springframework.web.server.ServerWebInputException;

public class InputException extends ServerWebInputException {

    public InputException(String reason) {
        super(reason);
    }
}
