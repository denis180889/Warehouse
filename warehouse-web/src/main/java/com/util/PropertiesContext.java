package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesContext {

   private PropertiesContext() {
      init();
   }

   private Properties generalMap;

   private void init() {
      String propFileName = "app.properties";
      generalMap = new Properties();
      try {
         InputStream inputStream = new FileInputStream(propFileName);
         generalMap.load(inputStream);
         inputStream.close();
      } catch (IOException e) {
         e.getMessage();
      }
   }

   public String getProperty(String key) {
      return (String) generalMap.get(key);
   }
}
