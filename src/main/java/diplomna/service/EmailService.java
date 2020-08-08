package diplomna.service;


import diplomna.service.serviceImpl.Greeting;

import java.util.concurrent.Future;

public interface EmailService {

    boolean send(Greeting greeting);

    void sendAsync(Greeting greeting);

    Future<Boolean> sendAsyncWithResult(Greeting greeting);
}
