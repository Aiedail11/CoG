import java.io.*;
import java.util.*;

public class ethListTest {
//static File ethnicitiesFile;
   public static void main (String[] args) {
      try {
         
         File ethnicitiesFile = new File(".\\//Names");
         File[] listOfEthnicities = ethnicitiesFile.listFiles();
      System.out.println(listOfEthnicities);
         int numEth = listOfEthnicities.length;
         Scanner ethnicitiesTxt = new Scanner(ethnicitiesFile);
        
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
   
   
   }

}