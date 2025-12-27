package com.project.flowly.shared.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String id) {
        super(String.format("%s with ID '%s' not found", resource, id));
    }
}
