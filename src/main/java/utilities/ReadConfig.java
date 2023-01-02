package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
 Properties pro;

 public ReadConfig() {
     File src = new File("src/main/resources/resources.properties");
     try {
         FileInputStream fis = new FileInputStream(src);
         pro = new Properties();
         pro.load(fis);
     }
     catch (Exception e)
     {
         System.out.println(e.getMessage());
     }
 }

     public String getApplicationURL()
 {
         String url = pro.getProperty("baseurl");
         return url;
     }
     public String getUserName() {
         String name = pro.getProperty("username");
         return name;
     }

     public  String getPassword() {
         String password = pro.getProperty("password");
         return password;
     }
     public String getChromePath(){
     String chrome=pro.getProperty("chromePath");
     return chrome;
      }
public String getFirefoxPath(){
     String firefox=pro.getProperty("firefoxPath");
     return firefox;
}

}
