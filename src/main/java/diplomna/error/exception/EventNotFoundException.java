package diplomna.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "This event is not found!")
public class EventNotFoundException extends BaseException {
    public EventNotFoundException(String msg) {
        super(msg);
    }
}
