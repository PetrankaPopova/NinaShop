package diplomna.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User not found exception!")
public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
