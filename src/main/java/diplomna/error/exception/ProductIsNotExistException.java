package diplomna.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "This product is not exist!")
public class ProductIsNotExistException extends BaseException {
    public ProductIsNotExistException(String msg) {
        super(msg);
    }
}

