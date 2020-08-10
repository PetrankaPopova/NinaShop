package diplomna.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User is not exist!")
public class UserWithUsernameAlreadyExistException  extends Throwable {
    public UserWithUsernameAlreadyExistException(String msg) {
        super();
    }
}
