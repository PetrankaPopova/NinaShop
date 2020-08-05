package diplomna.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User cannot be saved!")
public class UserCannotSaveException extends Throwable {
    public UserCannotSaveException(String msg) {
        super(msg);
    }
}