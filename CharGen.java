/*
--------------
CharGen v1.0
--------------
This is the first release of Emily Haggard's character generator for the
CoG roleplaying system. It is currently stable, but not secured, and even minor changes could destabilize the code.

This version will only be available as .java (and by extension .class) file, that will be executed
by a batch script.

Users are welcome to play around with the code, but Emily will not fix it for you
if you break it. Consider yourself warned.

Please note that the code has not been cleaned and organized since it was created, and may be convoluted or
unreadable in some places. A guide to code changes and customization will not be provided until version 2.0
is released.

You may notice the word 'dummy' used in comments throughout the code. This is simply a keyword that Emily can search for
to find important reminders, and should be disregarded.

Now for the legalese:

 Copyright 2017 Emily Haggard

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
--------------
*/


import java.util.*;
import java.io.*;
import java.text.*;

public class CharGen {

// SPECIALTY CLASSES
   private static SysLog log;
   private StatsGen statsGen;
   private ReadFiles fileReader;
   private CharModel character; 
 // USER INPUT
   private String input = "";
   private int mode = -1; //stores overall generator mode
   private Scanner console = new Scanner(System.in);
   private String desiredFields; // used in specific mode to store desired outputs
 // CHARACTER INFO 
   private String ethnicity = "";
   private String genderString;
   private int gender = -1, ethNum = -1;
   private String myRace = "";
   private String myClass = "";
   private String myName = "";
   private int myLevel = 1;
 // SYSLOG-RELATED OUTPUT VARIABLES
   private static String prompt = "CoG> ";
   //ALL THE BOOLEANS YAY
   //this boolean controls whether stats are printed
   private boolean usingStats = true;
   //special modes predefined by Emily
   private boolean dmPresets;
   private boolean testMode;
   //used to tailor console messages when repeating a mode
   private boolean repeating;
   //determines whether a last name will be generated in addition to a first name
   private boolean fullName =false; 
   //the following are all toggled in specific mode when the user specifes a custom value
   private boolean setRace = false;
   private boolean setClass = false;
   private boolean setGender = false;
   private boolean setName = false;
   private boolean setEthnicity = false;
   private boolean setLevel = false;
  
   
   
   public static void main (String[] args) {
      CharGen me = new CharGen();
      log = new SysLog(me);
      me.welcome();
      me.go();    
   }
   public CharModel getCharacter()
   {
   return character;
   }
   public  void go() {
      fullName=false;
      enterMode(); //auto-random generation, specific generation, or custom generation; then executes the mode
      whetherSave(); //save to a file or don't save - build functionality dummy
      shouldContinue(); //whether to continue or end program, calls main if applicable
   
   }
   public void welcome() {
      log.println("Welcome to the CoG Character Generator.");
   }
   public  void enterMode() {
      prompt = "CoG> ";
     
     //reset all values
      dmPresets = false;
      setRace = false;
      setClass = false;
      setGender = false;
      setName = false;
      setEthnicity = false;
      ethnicity = "";
      genderString = "";
      myRace = "";
      myClass = "";
      myName = "";
      myLevel = 1;
      
      
      if(log.logging){    
         log.println("\nEnter the number corresponding to the desired mode."
                        + "\n0. Help (See more information)"
                        + "\n1. Random"
                        + "\n2. Specific"
                        + "\n3. Custom -- COMING SOON!"
                        + "\n6. Toggle SysLog (Enabled)");}
      else{
         log.println("\nEnter the number corresponding to the desired mode."
                        + "\n0. Help (See more information)"
                        + "\n1. Random"
                        + "\n2. Specific"
                        + "\n3. Custom -- COMING SOON!"
                        + "\n6. Toggle SysLog (Disabled)");}
                       
      input = log.takeInput();
      try{
         Integer.parseInt(input);
      }
      catch(java.lang.NumberFormatException e)
      {
         enterMode();
      }
      mode = Integer.parseInt(input);
      if(mode==0)
      { new Helper("enterMode"); }
      if(mode <1 || mode > 6)
      { enterMode(); }
         
          
       //execute method for selected mode
      switch (mode) {
      
         case 1:   
            prompt = "CoG(random)> "; 
            usingStats=true;         
            random();
                  
            break;
         case 2: 
            prompt = "CoG(specific)> ";
            //log.println("\nSpecific mode selected.");
            fullName =false;
            setRace = false;
            setClass = false;
            setGender = false;
            setName = false;
            setEthnicity = false;
            setLevel = false;
         
            specific();
                  
            break;
         case 3:     
            prompt = "CoG(custom)> ";       
            custom();
            break;
         case 4: 
         //this will run random mode, however certain aspects of the generations 
         //will be overridden by the dm's presets
            prompt = "CoG(dmPresets)> ";
            dmPresets = true;
            usingStats=true;
            fullName = true;
            mode = 1;
            random();       
            break;
         case 5:  //TESTING MODE
            //this will run random mode, however the user will be prompted to enter
            //the level of the character
            prompt = "CoG(testing)> ";
            mode = 1;
           // log.println("\nTesting mode selected.");
            testMode = true;
            pickLevel();
            random();
            break;
         case 6:
            if(!log.logging){log.startLog();}
            else{log.stopLog();}
            enterMode();
      }
   }
   
   public void random() {
    /*  if(!repeating) {
         if(dmPresets) {
           // log.println("\n\nRandom mode with DM presets selected.");
           // log.println();
         }
         else {
           // log.println("\nRandom mode selected.");
            //log.println();
         }
      }*/
      genAll();//all fields are randomly generated
   }
   
   
   public void specific() {
   
   
      if(!repeating) {
         
         log.println("\n\nEnter the desired fields to output, separated by commas" +
            "\n\t(use 'help' to list available fields)");
        
      
         desiredFields = log.takeInput().toLowerCase();
      }
      
       
      
      if(desiredFields.contains("help"))
      {
         new Helper("specific");
         specific();
      
      }
     //  if(desiredFields.contains("level"))
   //       {
   //          log.println("Level is currently a work-in-progress.");
   //         // myLevel = (int)(Math.random()*10) + 1;
   //       
   //       }
      if(desiredFields.contains("name"))
      {
      
         if(repeating && !setName)
         {
            pickNamePrereqs();
            fileReader = new ReadFiles(mode, ethnicity, gender);
            fileReader.usingNames=true;
            fileReader.initialize();
            fileReader.usingNames=false;
            pickName();
         }
         else if(!setName)
         {desiredFieldsName(); }         
        
        
      }
      if(desiredFields.contains("class"))
      {
         if(repeating && !setClass){
         
            fileReader = new ReadFiles(mode, ethnicity, gender);
            fileReader.usingClasses=true;
            fileReader.initialize();
            fileReader.usingClasses=false;
            pickClass();
         }
         else if(!setClass)
         {desiredFieldsClass();} 
      }
      
      if(desiredFields.contains("race"))
      {
         if(repeating && !setRace){
            fileReader = new ReadFiles(mode, ethnicity, gender);
            fileReader.usingRaces=true;
            fileReader.initialize();
            fileReader.usingRaces=false;
            pickRace();
         }
         else if(!setRace)
         {desiredFieldsRace();}
      }
         
      if(desiredFields.contains("stats"))
      {
         if(myRace.equals(""))
         {
            log.println("WARNING: A race has not been selected and will be generated automatically.");
            fileReader = new ReadFiles();
            fileReader.usingRaces = true;
            fileReader.initialize();
            fileReader.usingRaces = false;
            pickRace();
         }
         if(!setLevel){ 
            pickLevel();
         }
         readStats();
         usingStats=true;
      }
      
      else{usingStats = false;}
   
      printCharacter();
      whetherSave();
      shouldContinue();
   }
   
   public  void custom() {
      log.println("Sorry! This mode is currently under development and therefore unavailable.");
      shouldContinue();
   //prompt for multiple desired fields and their options, then generate
   }
   
   public void whetherSave() {
   //prompts the user to save or forget the generated info
   //if they decide to save, prompts for a save directory
   //if there is no default save directory, asks user if they want to
   // set the directory as default for THIS RUN ONLY
   
   //prompts user for filename
   //checks for files of the same name in the specified directory
   //saves if applicable, or asks for a new filename (due to previously existing file)
      FileWriter fw;
      String save = "";
      if(myName.equals(""))
      { 
         myName = "Unnamed"; 
         ethnicity = "None";
         genderString = "None";
      
      }
      
      String[] nameArray = myName.split(" ~|/| ");
      String justName = nameArray[0];
   
     
     
      log.println("Would you like to save this configuration to a text file in .\\Saves? (y/n)");
      save = log.takeInput();
      save=save.toLowerCase();
      if(save.equals("y")) {
         log.println("Configuration will be saved as " + nameArray[0] +".txt in the Saves directory.");
         try{
            File theFile = new File(".\\\\Saves\\"+nameArray[0]+".txt");
            if(new File(".\\\\Saves\\"+nameArray[0]+".txt").exists()) //if file already exists
            {
               theFile = new File(renameFile());
            }
            fw = new FileWriter(theFile);  //note that this overwrites a pre-existing file DUMMY
            try{
               if(!myName.equals("")){
                  fw.write(
                     "Name: " + myName +
                     "\nOrigin of Name: " +ethnicity +
                     "\nGender: " + genderString);
               
                  if(!myClass.equals("")){                  
                     fw.append(
                        "\n\nClass: " + myClass);
                  }
                  if(!myRace.equals("")){
                     fw.append("\nRace: " + myRace);
                  }
                 
                  if(usingStats)
                  {
                     fw.append("\nLevel: " + statsGen.level+
                        "\nWisps: " + statsGen.wisps + "\n"+
                        
                        "\nEndurance: " + statsGen.end+
                        "\nDexterity: " + statsGen.dex+
                        "\nStrength: " + statsGen.str+
                        "\nArcana: " + statsGen.arc + "\n"+
                        
                        "\nDraiocht: " + statsGen.draiocht+
                        "\nReplenish Rate: " + statsGen.replenishRate);
                  }
               }
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
         }
         catch(Exception e) {
            System.err.println("I will never use this error. Ever");
            e.printStackTrace();
         }
      }
      else if(save.equals("n")){
         log.println("Configuration has not been saved.");
      }
      else {
         whetherSave();
      }
     
   }
   
   public void shouldContinue() {
   //prompts the user to continue
   //if they continue, calls main
      
      String in;
     // Scanner console2 = new Scanner(System.in);
      
      log.println("\nWould you like to generate another character?"+
         "\ny = yes, and select new mode\nn = no, end run and exit\nr = repeat, use current settings");
     
      input = log.takeInput().toLowerCase();
      if(input.equals("y")) {
         repeating = false;
         go();
      }
      else if(input.equals("n")){
         repeating = false;
         prompt = "CoG> ";
         if(log.logging){log.println("Session saved in \\Logs\\" + log.timeStamp + ".txt");}
         log.println("Goodbye!");
         System.exit(0);
      }
      else if(input.equals("r")){
         repeating = true;
         if(dmPresets)
         {
            mode = 1;
            usingStats=true;
            this.random();
            whetherSave();
            shouldContinue();
                 
         }
         else if(testMode)
         {
         
            mode = 1;
           // log.println("Testing mode selected.");
            this.pickLevel();
            this.random();
            whetherSave();
            this.shouldContinue();
         }
         else {
            switch (mode) {
            
               case 1:    
                  usingStats=true;   
                  genAll();
                  whetherSave();
                  shouldContinue();
                  break;
               case 2: 
                  specific();
                  break;
               case 3:            
                  custom();
                  break;
            }}
      }
      else {
         shouldContinue();
      }
     
   }
   
   public  void genAll() {  //used only for random mode
      
      pickNamePrereqs();
        
      fileReader = new ReadFiles(mode, ethnicity, gender);
      fileReader.initialize();
     
      usingStats=true;
   //all fields are generated with default options
    
      pickName();
      pickClass(); 
      pickRace();
      readStats();   
     
      printCharacter();
      
   }
 
   public void readStats() {
      if(mode==3) {//random mode - defaults to level one
         statsGen = new StatsGen(log, myLevel, myRace, true);  
      }
      else if(mode==2){ //specific or custom modes
         statsGen = new StatsGen(log, myLevel, myRace, false);
      }
      else { //mode ==3
         statsGen = new StatsGen(log, myLevel, myRace, false);
      }
   }
  
   public void pickRace() {
      if(setRace) {
         return;}
      int raceIndex = (int)(Math.random() * (fileReader.races.size()-1));
      
      myRace = fileReader.races.get(raceIndex);
      
      if(dmPresets) { //prevents generating from Custom lists
         while(myRace.equals("Custom")) {
            raceIndex = (int)(Math.random() * (fileReader.races.size()-1));
         
            myRace = fileReader.races.get(raceIndex);
         }
      }
      
      fileReader.race_fr = myRace;
   
   }
   
   public void pickClass() {
      int classIndex = (int)(Math.random() * (fileReader.classes.size()-1));
      myClass = fileReader.classes.get(classIndex);
      
   }
   
   public void pickName() {
      String[] firstName;
      String[] lastName;
      
         
      if(gender ==-1 || ethnicity.equals("") || ethnicity.equals(""))
      { pickNamePrereqs(); }
      fileReader = new ReadFiles(mode, ethnicity, gender);
      fileReader.usingNames = true;
      fileReader.initialize();
      
      int namesIndex = (int)(Math.random() * (fileReader.namesList.size()-1));
   
      myName=fileReader.namesList.get(namesIndex);
      if(fullName)
      {
         firstName = myName.split("~"); //separates name and definition portion of first name
         myName = "";
         namesIndex = (int)(Math.random() * (fileReader.namesList.size()-1));
      
         myName=fileReader.namesList.get(namesIndex);
         lastName = myName.split("~"); //separates name and definition portion of last name
         myName = firstName[0] + lastName[0] + "\n~"+firstName[1] + lastName[1]+" ~";
      }
      if(gender==1) {
         genderString = "Female";
      }
         
      if(gender==0) {
         genderString = "Male";
      } 
   }
   
   public void checkNameLength() {
      if(myName.length() > 101 && !ethnicity.equals("Custom") && !fullName) {
      //if a name on one of the default lists is too long, this tells the user to report it
         log.println("ERROR \nName length exceeds requirements."+
            "\nPlease notify Emily Haggard (emilyhaggard02@gmail.com) of the following: "+
            "\nName= " + myName.substring(0, 40) +
            "\nList= " + ethnicity + genderString + 
            "\nPlease Note: If this is a custom name that you have added, disregard this message.");
      }
   
   }
   
   public void printCharacter() {
      String charString = "";
      if(gender==1) {
         genderString = "Female";
      }
      if(gender==0) {
         genderString = "Male";
      }
      
      //log.print("\n------------------");
      charString+="Character successfully generated."
                  +"\n------------------";
      if((!myName.equals("") && mode!=2) ||
      (desiredFields.contains("name") && mode==2)){
         charString+="\nName: " + myName +
                  "\nOrigin of Name: " +ethnicity +
                  "\nGender: " + genderString;
      }
      
      if((!myClass.equals("") && mode!=2) ||
      (desiredFields.contains("class") && mode==2)){                  
         charString+="\n\nClass: " + myClass;
      }
                  
      if((!myRace.equals("") && mode!=2) ||
      (desiredFields.contains("race") && mode==2)){
         charString+="\nRace: " + myRace;
      }
      if(usingStats)
      {
         charString+="\nLevel: " + statsGen.level+
                  "\nWisps: " + statsGen.wisps + "\n"+
                  
                  "\nEndurance: " + statsGen.end+
                  "\nDexterity: " + statsGen.dex+
                  "\nStrength: " + statsGen.str+
                  "\nArcana: " + statsGen.arc + "\n"+
                  
                  "\nDraiocht: " + statsGen.draiocht+
                  "\nReplenish Rate: " + statsGen.replenishRate;
      }
      charString+="\n------------------\n";
      log.println(charString);
      checkNameLength();           
   
   }
   public void pickLevel()
   {
      log.println("Enter the desired level.");
     // log.takeInput();
      myLevel = Integer.parseInt(log.takeInput());
      setLevel = true;
   }
   public String renameFile() 
   {
      int num = 1;
      String[] nameArray = myName.split(" ~|/");
     
      File tempFile = new File(".\\\\Saves\\"+nameArray[0]+".txt");
      String newName = "";
    
      log.println(nameArray[0] + ".txt already exists. Overwrite (y/n)?");
      log.takeInput();
      String in1 = log.takeInput();
      if(in1.equalsIgnoreCase("y"))
      {
         log.println("File will be overwritten.");
         newName = ".\\\\Saves\\"+nameArray[0]+".txt";
         return newName;
      }
      else
      {
      //appends this number to name, and increments until the file will not overwrite a
         //previously existing one
         while(tempFile.exists())
         {
            num++;
            tempFile = new File(".\\\\Saves\\"+nameArray[0]+num+".txt");
         }
      }
      newName = nameArray[0] + num;
      log.println("File renamed: " + newName);
      return ".\\\\Saves\\"+newName+".txt";
   }
   public void pickNamePrereqs()
   {
      if (!setGender || myName.equals(""))
      {gender = (int)(Math.random()*2);}
      
     // int ethNum = (int)(Math.random()*11);  this is only used once I get custom names working, dummy
      //possibly read names of all files in the Names directory and base it off an
      //array created from those names
      if(setEthnicity){
         return;}
      ethNum = (int)(Math.random()*10); 
      //ethnicity = fileReader.races.get(ethNum);
      switch (ethNum) {
         case 0: 
            ethnicity = "Hebrew";
            break;
         case 1:
            ethnicity = "German";
            break;
         case 2:
            ethnicity = "Latin";
            break;
         case 3:
            ethnicity = "Greek";
            break;
         case 4:
            ethnicity = "French";
            break;
         case 5:
            ethnicity = "Hindu";
            break;
         case 6:
            ethnicity = "Japanese";
            break;
         case 7:
            ethnicity = "Russian";
            break;
         case 8:
            ethnicity = "Italian";
            break;
         case 9:
            ethnicity = "Irish";
            break;
         case 10:
            ethnicity = "Custom";
            break;
      }
      if(dmPresets) { //dmPresets overrides the random generation
         ethnicity = "Irish";
      }
   
   
   }
   public void desiredFieldsName()
   {
      String response = "";
      log.println("Enter desired name, or type 'none' "+
            "\nto continue to gender and ethnicity selection.");
      response = log.takeInput();
      log.println();
      if(!response.equalsIgnoreCase("none"))
      {setName = true;
         myName = response;
         
      }
      if(!setGender){
         log.println("Enter desired gender ('male' or 'female'), or type "+
            "\n'none' to randomly generate gender.");
         response = log.takeInput();
         log.println();
         if(response.equalsIgnoreCase("male"))
         {setGender = true;
            gender = 0;  }
         else if(response.equalsIgnoreCase("female"))
         {setGender = true;
            gender = 1;}
      }
      if(!setEthnicity)
      {
         do{
            log.println("Enter desired ethnicity, or" +
               "\ntype 'none' to randomly generate ethnicity, or" +
               "\ntype 'help' to view the list of ethnicities.");
            response = log.takeInput();
            log.println();
            if(response.equalsIgnoreCase("help"))
            {
               new Helper("specific_ethnicities");
               ethnicity = "";
            }
            else if(!response.equalsIgnoreCase("none"))
            {
               setEthnicity = true;
               ethnicity = response;
            }
            else if(!response.equalsIgnoreCase("help"))
            {pickNamePrereqs();}
         }while(ethnicity.equals(""));
      }
         
      if(!fullName && !setName){
         log.println("Generate full name? (y/n)");
         if(log.takeInput().equalsIgnoreCase("y")){
            fullName = true;
         }
         log.println();
      }
      if(myName.equals("")){
         pickNamePrereqs();
         pickName();
      }
   
   }
   public void desiredFieldsClass()
   {
      String response = "";
      log.println("Enter desired class, or \ntype 'none'"+
            " to randomly generate class, or\ntype 'help' to view available classes.");
      response = log.takeInput();
      if(response.equalsIgnoreCase("help"))
      {new Helper("specific_classes");
         desiredFieldsClass();}
      else if(!response.equalsIgnoreCase("none"))
      {setClass = true;
         myClass = response;
         return;
      }
      fileReader = new ReadFiles();
      fileReader.usingClasses = true;
      fileReader.initialize();
      fileReader.usingClasses = false;
      pickClass();
   }
   public void desiredFieldsRace()
   {
      String response = "";
      log.println("Enter the desired race, \nor type 'none'"+
         " to randomly generate race, or \ntype 'help' to view a list of available races.");
      response = log.takeInput();
      if(response.equalsIgnoreCase("help"))
      {new Helper("specific_races");
         desiredFieldsRace();}
      else if(!response.equalsIgnoreCase("none"))
      {setRace = true;
         myRace = response;}
      fileReader = new ReadFiles();
      fileReader.usingRaces = true;
      fileReader.initialize();
      fileReader.usingRaces = false;
      pickRace();
   }
   public String getPrompt()
   {
      return prompt;
   }
   public void setPrompt(String s){
      prompt = s;
   }
}