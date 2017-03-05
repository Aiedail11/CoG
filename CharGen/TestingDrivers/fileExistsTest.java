import java.io.*;
import java.util.*;

public class fileExistsTest
{
   public static void main (String[] args) 
   {
      File fw;
    //  try{
      fw = new File(".\\\\Saves\\aadfasdfasdf.txt");  //note that this overwrites a pre-existing file DUMMY
      if(!fw.exists())
         System.out.println("Yay it works!");
        /* try{
          //  fw.write();
            
            //fw.append("ROFL\n"+"HELP I NEED SOMEBODY, HELP, I NEED SOMEONE");
         } 
         catch (Exception e) {
            System.err.println("HELP:");
            e.printStackTrace();
         }
         
         try{
            fw.flush();
            fw.close();
         } 
         catch (Exception e) {
            System.err.println("Error in Flush/close:");
            e.printStackTrace();
         }
         
         catch(Exception e) {
            System.err.println("I will never use this error. Ever");
            e.printStackTrace();
         }
      }
      */
      //else if(!save.equals("y")){
        // System.out.println("Configuration has not been saved.");
      
      
      
   }
}