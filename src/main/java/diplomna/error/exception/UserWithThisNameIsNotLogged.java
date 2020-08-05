package diplomna.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User with this name is not logged!")
public class UserWithThisNameIsNotLogged extends BaseException {

    public UserWithThisNameIsNotLogged(String message) {
        super(message);
    }
}
