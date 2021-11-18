package com.jakubartlomiej.onlymajster.publication.exception;

public class PublicationNotFoundException extends RuntimeException {
    public PublicationNotFoundException(long id) {
        super("Could not find publication: " + id);
    }
}