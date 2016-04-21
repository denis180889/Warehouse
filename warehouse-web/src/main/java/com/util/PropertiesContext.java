package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesContext {

   private static PropertiesContext instance;

   private PropertiesContext() {
      init();
   }

   private Properties generalMap;

   private void init() {
      String propFileName = "app.properties";
      generalMap = new Properties();
      try {
         InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
         generalMap.load(inputStream);
         inputStream.close();
      } catch (IOException e) {
         e.getMessage();
      }
   }

   public static PropertiesContext getPropertContext() {
      if (instance == null) {
         instance = new PropertiesContext();
      }
      return instance;
   }

   public String getProperty(String key) {
      return (String) generalMap.get(key);
   }
}
