package com.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dto.Warehouse;
import com.entities.WarehouseEntity;

public class WarehouseValidator implements Validator {

   public boolean supports(Class clazz) {
      return Warehouse.class.equals(clazz);
  }

  public void validate(Object obj, Errors e) {
      ValidationUtils.rejectIfEmpty(e, "name", "EMPTY_NAME");
      Warehouse w = (Warehouse) obj;
      if (w.getLatitude() > 90 || w.getLatitude() < -90 ) {
          e.rejectValue("latitude", "INVALID_LATITUDE");
      } else if (w.getLongitude() > 180 || w.getLongitude() < -180) {
          e.rejectValue("longitude", "INVALID_LONGTITUDE");
      }
  }

}
