package diplomna.web.controllers;

//import diplomna.model.service.GreetingService;
import diplomna.service.EmailService;
import diplomna.service.serviceImpl.Greeting;
import diplomna.service.serviceImpl.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//@Controller
//public class GreetingController {

   // @Autowired
  //  private final GreetingService greetingService;
   // private final EmailService emailService;
  //  Logger logger = LoggerFactory.getLogger(LoggingController.class);

   // public GreetingController(GreetingService greetingService, EmailService emailService) {
   //     this.greetingService = greetingService;//     this.emailService = emailService;
 //   }

   // @RequestMapping(value = "/greetingd", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   // public ResponseEntity<Collection<Greeting>> getGreetings() {

        //logger.info("> getGreetings");

      //  Collection<Greeting> greetings = greetingService.findAll();

    //    logger.info("<getGreetings");

     //   return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
  //  }

  //  @RequestMapping(
          //  value = "greeting/{id}/send",
           // method = RequestMethod.POST,
           // produces = MediaType.APPLICATION_JSON_VALUE
   // )
  //  public ResponseEntity<Greeting>sendGreeting(@PathVariable("id") String id,
     //   @RequestParam(value = "wait",
     //   defaultValue = "false")boolean waitForAsyncResult){

    //    logger.info("> sendGreeting");

      //  Greeting greeting = null;

     //   try {
          //  greeting = greetingService.findOne(id);
           // if (greeting == null){
            //    logger.info("< sendGreeting");
            //    return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
           // }
           // if (waitForAsyncResult){
              //  Future<Boolean> asyncResponse = emailService
                  //      .sendAsyncWithResult(greeting);
               // boolean emailSent = asyncResponse.get();
             //   logger.info("- greeting email send? {}", emailSent);
          //  }else{
          //      emailService.sendAsync(greeting);
         //   }
       // } catch (InterruptedException e) {
           // e.printStackTrace();

       // } catch (ExecutionException e) {
        //   logger.error("A problem occurred sending the Greeting", e);
       //    return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
     //   }
      //  logger.info("< sendGreeting");
      //  return new ResponseEntity<Greeting>(greeting,HttpStatus.OK);
   // }
//}
