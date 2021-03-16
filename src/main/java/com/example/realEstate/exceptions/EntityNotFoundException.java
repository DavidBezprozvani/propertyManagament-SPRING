package com.example.realEstate.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super("Entity not found with ID:" + id);
    }
}