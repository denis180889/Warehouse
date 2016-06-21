package com.dto.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents REST API data model for single result
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorResult {

   public ErrorResult() {
    //need this empty constructor for Jersey 
   }

   public ErrorResult(String error){
      this.ERROR_CODE = error;
   }
   
   private String ERROR_CODE;

   public String getError() {
      return ERROR_CODE;
   }

   public void setError(String error) {
      this.ERROR_CODE = error;
   }
   
}
