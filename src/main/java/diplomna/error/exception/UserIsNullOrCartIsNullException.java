package diplomna.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User is null or cart is null exception!")
public class UserIsNullOrCartIsNullException extends BaseException {
    public UserIsNullOrCartIsNullException(String message) {
        super(message);
    }
}
