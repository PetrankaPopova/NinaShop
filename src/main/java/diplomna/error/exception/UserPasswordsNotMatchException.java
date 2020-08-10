package diplomna.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User is not exist!")
public class UserPasswordsNotMatchException extends Throwable {
    public UserPasswordsNotMatchException(String msg) {
        super();
    }
}
