package mylearning.servicecomb.springmvc.vertx.consumer.controller;

import org.springframework.stereotype.Component;


@Component
public class SpringMVCConsumerDelegate {

    public String helloworld(String name){

        // Do Some Magic Here!
        return name;
    }
}
