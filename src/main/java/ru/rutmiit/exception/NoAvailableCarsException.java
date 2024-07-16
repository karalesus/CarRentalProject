package ru.rutmiit.exception;

public class NoAvailableCarsException extends RuntimeException {
        public NoAvailableCarsException(String message) {
            super(message);
        }
    }
