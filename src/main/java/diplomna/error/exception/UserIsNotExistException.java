package diplomna.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User is not exist!")
public class UserIsNotExistException extends BaseException{
    public UserIsNotExistException(String msg) {
        super(msg);
    }
}
