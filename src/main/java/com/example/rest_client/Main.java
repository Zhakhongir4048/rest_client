package com.example.rest_client;

import com.example.rest_client.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RestClientApplication.class);
        Communication communication = context.getBean("communication", Communication.class);
        List<User> allUsers = communication.getAllUsers();
        User userById = communication.getUser(6);
        User user = new User("Sveta", "Sokolova", "IT", 900);
        communication.saveUser(user);
        log.info(String.valueOf(allUsers));
        log.info(String.valueOf(userById));
    }

}