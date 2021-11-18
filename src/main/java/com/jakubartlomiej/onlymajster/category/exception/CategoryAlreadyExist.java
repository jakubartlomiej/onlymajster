package com.jakubartlomiej.onlymajster.category.exception;

public class CategoryAlreadyExist extends RuntimeException {
    public CategoryAlreadyExist(String category) {
        super("Category is exist: " + category);
    }
}