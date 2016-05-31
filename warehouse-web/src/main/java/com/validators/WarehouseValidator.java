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
      ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
      Warehouse w = (Warehouse) obj;
      if (w.getLatitude() > 90 || w.getLatitude() < -90 ) {
          e.rejectValue("latitude", "wrongValue");
      } else if (w.getLongitude() > 180 || w.getLongitude() < -180) {
          e.rejectValue("longitude", "wrongValue");
      }
  }

}
