package com.user.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.entities.OrderException;
import com.user.entities.UserException;
import com.user.exception.InvalidOrderException;
import com.user.exception.InvalidRoleException;
import com.user.exception.InvalidUserIdException;

import feign.FeignException;

@RestControllerAdvice
public class UserControllerAdvice {

    private Logger logger = LoggerFactory.getLogger(UserControllerAdvice.class);

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<Object> handleorder(InvalidOrderException ie) {
        logger.error("InvalidOrderException occurred: {}", ie.getMessage());
        String suggestion = "Try these as Status {'PLACED','VERIFIED','PICKED_UP','CANCELLED','OUT_OF_STOCK'}";
        UserException ue = new UserException(ie.getMessage(), suggestion, "InvalidOrderException");
        
        return new ResponseEntity<>(ue, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<Object> handlerole(InvalidRoleException ie) {
        logger.error("InvalidRoleException occurred: {}", ie.getMessage());
        String suggestion = "Invalid Role !!";
        UserException ue = new UserException(suggestion, ie.getMessage(), "InvalidRoleException");
        return new ResponseEntity<>(ue, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public OrderException handle(FeignException e) {
        logger.error("FeignException occurred: {}", e.getMessage());
        String errorBody = e.contentUTF8();
        ObjectMapper mapper = new ObjectMapper();
        try {
            OrderException orderException = mapper.readValue(errorBody, OrderException.class);
            logger.info("Parsed Feign error response: {}", orderException);
            return orderException;
        } catch (IOException ex) {
            logger.error("Error parsing Feign error response: {}", ex.getMessage());
            return new OrderException("Error parsing Feign error response", "Please check the logs", "ParseException");
        }
    }

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<Object> handleuserid(InvalidUserIdException ie) {
        logger.error("InvalidUserIdException occurred: {}", ie.getMessage());
        UserException ue = new UserException(ie.getMessage(), "Try with a Valid User Id", "InvalidOrderException");
        return new ResponseEntity<>(ue, HttpStatus.BAD_REQUEST);
    }
}