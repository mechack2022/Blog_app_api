package com.fragile.blog_api.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    long fieldValue;

 public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue){
     super(String.format("%s is not found with %s : %d", resourceName, fieldName, fieldValue));
     this.resourceName = resourceName;
     this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

}
