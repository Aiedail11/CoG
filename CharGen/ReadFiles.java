import java.util.*;
import java.io.*;

public class ReadFiles {

   Scanner classesTxt, fieldsTxt, racesTxt, namesTxt, ethnicitiesTxt;    
   File namesFile, fieldsFile, racesFile, classesFile;
   
   ArrayList<String> fields = new ArrayList<String>();
   
   ArrayList<String> races = new ArrayList<String>();
  
   
   ArrayList<String> classes = new ArrayList<String>();
   
ArrayList<String> ethnicityList = new ArrayList<String>();
   
   ArrayList<String> namesList = new ArrayList<String>();
   String token1= "";
   
   int end;
   int str;
   int dex;
   int arc;
   int draiocht;
   int wisps;
   int replenishRate;
   int level;

int numEth;

   int mode;
   int gender;
   String ethnicity;
   String race_fr;

   boolean usingClasses;
   boolean usingRaces;
   boolean usingPictures;

   boolean usingNames;

   
   public ReadFiles(int m, String e, int g) {
      mode = m;
      ethnicity = e;
      gender = g;
      
   }
   public ReadFiles()
   {
   mode = 2; ///dummy, you are assuming that whether it is 2 or 3 won't matter in this class
   ethnicity = "Irish";
   gender = 1;
   }
   public void initialize() {
   
      if(mode==1) {
         readClasses();
         readRaces(); 
         readNames();
      
        
         
      }
      if(mode==2 || mode==3) {
         readFields();
        
         if(usingClasses) {
            readClasses();
         }
         if(usingRaces) {
            readRaces();
         }
         if(usingNames) {
            readNames();
         }
      
      }
   }
   public void readClasses() {
      try {
         File classesFile = new File(".\\Readable\\Classes.txt");
         classesTxt = new Scanner(classesFile);        
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
   
   
    // while loop
      while (classesTxt.hasNextLine()) {
      // find next line
         token1 = classesTxt.nextLine();
         classes.add(token1);
         token1 = "";
      }
   }
   
   public void readFields() {
      try {
         File fieldsFile = new File(".\\Readable\\Fields.txt");
         fieldsTxt = new Scanner(fieldsFile);
        
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
      while (fieldsTxt.hasNextLine()) {
      // find next line
         token1 = fieldsTxt.nextLine();
         fields.add(token1);
         token1 = "";
      }
   
   }
 
   public void readRaces() {
      try {
         File racesFile = new File(".\\Readable\\AllRaces.txt");
         racesTxt = new Scanner(racesFile);
        
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
      while (racesTxt.hasNextLine()) {
      // find next line
         token1 = racesTxt.nextLine();
         races.add(token1);
         token1 = "";
      }
      
   }
   
   public void readNames() {
      String fileName;
      try {
      //all female name files
         if(gender==1) {
            String tempName = ".\\\\Names\\"+ethnicity+"Female.txt";
            namesFile = new File(tempName);
         }
         //all male name files
         else if(gender==0) {
            String tempName = ".\\\\Names\\"+ethnicity+"Male.txt";
            namesFile = new File(tempName);
              
         }
       
         namesTxt = new Scanner(namesFile);
        
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
      while (namesTxt.hasNextLine()) {
      // find next line
         token1 = namesTxt.nextLine();
         namesList.add(token1);
      
         token1 = "";
      }
   }
   public void readEthnicities() {
      try {
         File ethnicitiesFile = new File(".\\\\Names");
         File[] listOfEthnicities = ethnicitiesFile.listFiles();
      
         numEth = listOfEthnicities.length;
         ethnicitiesTxt = new Scanner(ethnicitiesFile);
        
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
     /* while (ethnicitiesTxt.hasNextLine()) {
      // find next line
         token1 = ethnicitiesTxt.nextLine();
         ethnicityList.add(token1);
         token1 = "";
      }*/
   
   
   }
}