package com.example.bikash.Social.Media.Exceptions;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends  RuntimeException{


    String resourceName;
    String fieldName;
    Long fieldValue;


    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s with the %s : %d not found", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}