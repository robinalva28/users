package com.usermanagement.user.common.exceptions;

public class BadRequestException extends RuntimeException{

        public BadRequestException(String message) {
            super(message);
        }
}