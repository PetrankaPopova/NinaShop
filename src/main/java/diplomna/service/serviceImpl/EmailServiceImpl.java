package diplomna.service.serviceImpl;

import diplomna.service.EmailService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LogManager.getLogger(LoggingController.class);


    @Override
    public boolean send(Greeting greeting) {

        logger.info("> send");

        Boolean success = Boolean.FALSE;

        long pause = 5000;
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Processing time was {} seconds", pause / 1000);

        success = Boolean.TRUE;
        logger.info("< send");
        return success;
    }

    @Override
    public void sendAsync(Greeting greeting) {
        logger.info("> sendAsync");
        try {
            send(greeting);
        }catch (Exception e){
            logger.warn("Exception caught sending asynchronous mail", e);
        }
        logger.info("< sendAsync");

    }

    @Override
    public Future<Boolean> sendAsyncWithResult(Greeting greeting) {
        logger.info("> sendAsyncWithResult");
        AsyncResponse<Boolean>response = new AsyncResponse<Boolean>();

        try {
            Boolean success = send(greeting);
            response.complete(success);
        }catch (Exception e){
            logger.warn("Exception caught sending asynchronous mail", e);
            response.completeExceptionally(e);
        }
        logger.info(", sendAsyncWithResult");

        return sendAsyncWithResult(greeting);
    }
}
