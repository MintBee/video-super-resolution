package com.example.demovideosuperresolution.exceptions;

import java.io.IOException;

public class VideoAlreadyExistsException extends RuntimeException {
    public VideoAlreadyExistsException() {
    }

    public VideoAlreadyExistsException(String message) {
        super(message);
    }
}
