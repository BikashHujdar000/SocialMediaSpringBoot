package com.example.bikash.Social.Media.Exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UnauthorizedTaskException extends RuntimeException {

    private String message;

    public UnauthorizedTaskException(String message) {

        super(String.format("%s", message));
        this.message = message;
    }
}
