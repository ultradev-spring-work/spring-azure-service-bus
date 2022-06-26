package com.ultradev.servicebus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ultradev.servicebus.User;

@RestController
public class SendController {

	//topic
    private static final String DESTINATION_NAME = "test";
    // subscription
    private static final String DESTINATION_TOPIC = "test-topic";

    private static final Logger logger = LoggerFactory.getLogger(SendController.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/messages")
    public String postMessage(@RequestParam("msg") String message) {
        logger.info("Sending message");
        jmsTemplate.convertAndSend(DESTINATION_NAME, new User(message));
        jmsTemplate.convertAndSend(DESTINATION_TOPIC, new User(message));
        return message;
    }
}