package com.dto.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents REST API data model for single result
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SingleResult {
   
   private long id;

   public SingleResult(){
   }
   
   public SingleResult(long id){
      this.id = id;
   }
   
   public long getId() {
      return id;
   }
   
}
