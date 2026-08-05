package com.evolvedadrian.inventory.exception;

import java.util.Objects;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
