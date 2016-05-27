package com.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dto.Warehouse;

public class WarehouseValidator implements Validator {

   public boolean supports(Class clazz) {
      return Warehouse.class.equals(clazz);
  }

  public void validate(Object obj, Errors e) {
      ValidationUtils.rejectIfEmpty(e, "name", "EMPTY_NAME");
      Warehouse w = (Warehouse) obj;
      
      Pattern pattern = Pattern.compile("\\p{IsLatin}+");
      Matcher matcher = pattern.matcher(w.getName());
      if(!matcher.matches()){
         e.rejectValue("name", "INVALID_WAREHOUSE_NAME");
      }
      
      else if (w.getLatitude() > 90 || w.getLatitude() < -90 ) {
          e.rejectValue("latitude", "INVALID_LATITUDE");
      } else if (w.getLongitude() > 180 || w.getLongitude() < -180) {
          e.rejectValue("longitude", "INVALID_LONGTITUDE");
      }
      else if(w.getName().toLowerCase().replaceAll(" ", "").equals(w.getDescription().toLowerCase().replaceAll(" ", ""))){
         e.rejectValue("description", "INVALID_DESCRIPTION");
      }
  }

}
