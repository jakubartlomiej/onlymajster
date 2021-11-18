package com.jakubartlomiej.onlymajster.category.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(long id) {
        super("Could not find category: " + id);
    }
}