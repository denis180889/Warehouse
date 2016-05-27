package com.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dto.Goods;

public class GoodsValidator implements Validator {

   public boolean supports(Class clazz) {
      return Goods.class.equals(clazz);
  }

  public void validate(Object obj, Errors e) {
      ValidationUtils.rejectIfEmpty(e, "name", "EMPTY_NAME");
      Goods g = (Goods) obj;
      
      Pattern pattern = Pattern.compile("\\p{IsLatin}+");
      Matcher matcher = pattern.matcher(g.getName());
      if(!matcher.matches()){
         e.rejectValue("name", "INVALID_GOODS_NAME");
      }
      else if(g.getName().toLowerCase().replaceAll(" ", "").equals(g.getDescription().toLowerCase().replaceAll(" ", ""))){
         e.rejectValue("description", "INVALID_DESCRIPTION");
      }
     
  }

}
