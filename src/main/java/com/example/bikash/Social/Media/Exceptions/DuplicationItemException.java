package com.example.bikash.Social.Media.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DuplicationItemException extends  RuntimeException {

    String resourceName;
    String fieldName;
    String fieldValue;
    public DuplicationItemException (String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s with the %s : %s exist already", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
