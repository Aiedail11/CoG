/*
--------------
CharGen v 0.9a
--------------
This is the first user-testable version of Emily Haggard's character generator for the
CoG roleplaying system. It is currently stable, but not secured, and even minor changes could destabilize the code.

This version will only be available as .java (and by extension .class) file.

Users are welcome to test and play around with the code, but Emily will not fix it for you
if you break it. Consider yourself warned, and at least make a copy before you try anything crazy.

Suggestions may be submitted to Emily at any time before the release of version 1.0
She will include the suggestions at her discretion, but you will be credited for your work if it is used.

Please note that the code has not been cleaned and organized since it was created, and may be convoluted or
unreadable in some places. A guide to code changes and customization will not be provided until version 1.0
is released.

You may notice the word 'dummy' used in comments throughout the code. This is simply a keyword that Emily can search for
to find important reminders, and should be disregarded.

Before receiving this code, you made an agreement with Emily not to share it or show it to anyone else without
permission. Any exceptions were explicitly given to you. However, you may demonstrate a run of this
code without permission, as long as Emily Haggard is credited.

Emily may be contacted at emilyhaggard02@gmail.com
--------------
*/


import java.util.*;
import java.io.*;

public class CharGen {

   String input = "";
   int mode = 0;
   Scanner console = new Scanner(System.in);
   
   StatsGen statsGen;
   
   ReadFiles fileReader;
   String ethnicity;
   String genderString;
    
   String myRace = "";
   String myClass = "";
   String myName = "";
  
   String[] emptyArray = new String[1];
   String token1 = "";
   int num;
   
   //ALL THE BOOLEANS YAY
   boolean usingStats = true;
   boolean going = true;
   boolean dmPresets;
   
   public static void main (String[] args) {
      CharGen me = new CharGen();
  //    me.whetherSave(); dummy
      me.welcome();
      me.go();    
   }
   
   public  void go() {
      if(going) {
      
         enterMode(); //auto-random generation, specific generation, or custom generation; then executes the mode
      //  whetherSave(); //save to a file or don't save - build functionality dummy
         shouldContinue(); //whether to continue or end program, calls main if applicable
      }
      else {
         System.out.println("Goodbye!");
         System.exit(1);
      }
     
      
   }
   public void welcome() {
      System.out.println("Welcome to the CoG Character Generator.");
   }
   public  void enterMode() {
   //Print/scanner prompt for mode
      mode = 0;
      dmPresets = false;
      System.out.println("Enter the number corresponding to the desired mode."
                        + "\n1. Randomly generate all fields with default options."
                        + "\nCOMING SOON! -- 2. Generated one field with selected options."
                        + "\nCOMING SOON! -- 3. Generated all fields with selected options.");
      try {
         mode = console.nextInt();
           
      }
      catch (java.util.InputMismatchException e) {
         System.out.println("Good job, you're brilliant.\nNow you have to start this *allll* over again."
            + "\nSee? The only one you've hurt is yourself.");
         System.exit(1);
       
      }
    
     
       //execute method for selected mode
      switch (mode) {
      
         case 1:    
            usingStats=true;         
            random();
                  
            break;
         case 2: 
            specific();
                  
            break;
         case 3:            
            custom();
            break;
         case 4: 
         //this will run random mode, however certain aspects of the generations 
         //will be overridden by the dm's presets
            dmPresets = true;
            mode = 1;
            random();       
            break;
      }
   }
   
   public void random() {
      if(dmPresets) {
         System.out.println("Random mode with DM presets selected.");
         System.out.println();
      }
      else {
         System.out.println("Random mode selected.");
         System.out.println();
      }
      genAll();//all fields are randomly generated
   }
   
   
   public void specific() {
      System.out.println("Specific mode selected.");
      System.out.println("Sorry! This mode is currently under development and therefore unavailable.");
      shouldContinue();
      
   //prompt for single desired field and its options, then generate
   }
   
   public  void custom() {
      System.out.println("Custom mode selected.");
      System.out.println("Sorry! This mode is currently under development and therefore unavailable.");
      shouldContinue();
   //prompt for multiple desired fields and their options, then generate
   }
   
   public static void whetherSave() {
   //prompts the user to save or forget the generated info
   //if they decide to save, prompts for a save directory
   //if there is no default save directory, asks user if they want to
   // set the directory as default for THIS RUN ONLY
   
   //prompts user for filename
   //checks for files of the same name in the specified directory
   //saves if applicable, or asks for a new filename (due to previously existing file)
   FileWriter fw;
   try{
   fw = new FileWriter(new File(".\\Test.txt"));
   
      try{
      fw.write("Duh\n");
      //fw.append("ROFL\n"+"HELP I NEED SOMEBODY, HELP, I NEED SOMEONE");
      } catch (Exception e) {
      System.err.println("HELP:");
      e.printStackTrace();
      }
   
      try{
      fw.flush();
      fw.close();
      } catch (Exception e) {
      System.err.println("Error in Flush/close:");
      e.printStackTrace();
      }
   }catch(Exception e) {
   System.err.println("I will never use this error. Ever");
   e.printStackTrace();
   }
   
   
   }
   
   public void shouldContinue() {
   //prompts the user to continue
   //if they continue, calls main
      String in;
      Scanner console2 = new Scanner(System.in);
      
      System.out.println();
      System.out.println("Would you like to generate another character? (y/n)");
      System.out.println("");
      input = console2.nextLine().toLowerCase();
      if(input.equals("y")) {
         going=true;
      }
      else if(input.equals("n")){
         going=false;
      
      }
      else {
         System.out.println("I'll take that as a 'no'.");
         going = false;
      }
      go();
   }
   
   public  void genAll() {
   
      int gender = (int)(Math.random()*2);
      
      int ethNum = (int)(Math.random()*10);
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
      }
      if(dmPresets) { //dmPresets overrides the random generation
         ethnicity = "Irish";
      }
   
      fileReader = new ReadFiles(mode, ethnicity, gender);
      fileReader.initialize();
   //all fields are generated with default options
    
      int namesIndex = (int)(Math.random() * (fileReader.namesList.size()-1));
   
      myName=fileReader.namesList.get(namesIndex);
   
      int classIndex = (int)(Math.random() * (fileReader.classes.size()-1));
      myClass = fileReader.classes.get(classIndex);
      
   
      int raceIndex = (int)(Math.random() * (fileReader.races.size()-1));
      
      myRace = fileReader.races.get(raceIndex);
     
      fileReader.race_fr = myRace;
      
      if(usingStats) {
         readStats();   
         usingStats=false;
      }
      System.out.println("Name: " + myName);
      System.out.println("Origin of Name: " + ethnicity);
      System.out.println();
      if(fileReader.gender==1) {
         genderString = "Female";
      }
      if(fileReader.gender==0) {
         genderString = "Male";
      }
      System.out.println("Gender: " + genderString);
      
      System.out.println("Class: " + myClass);
      System.out.println("Race: " + myRace);
      System.out.println("Level: " + statsGen.level);
      System.out.println("Wisps: " + statsGen.wisps);
      System.out.println();
      System.out.println("Endurance: " + statsGen.end);
      System.out.println("Dexterity: " + statsGen.dex);
      System.out.println("Strength: " + statsGen.str);
      System.out.println("Arcana: " + statsGen.arc);
      System.out.println();
      System.out.println("Draiocht: " + statsGen.draiocht);
      System.out.println("Replenish Rate: " + statsGen.replenishRate);
      
      if(myName.length() > 101 && !ethnicity.equals("Custom")) {
      //if a name on one of the default lists is too long, this tells the user to report it
         System.out.println("------------------" +
            "\nERROR \nName length exceeds requirements."+
            "\nPlease notify Emily Haggard (emilyhaggard02@gmail.com) of the following: "+
            "\nName= " + myName.substring(0, 40) +
            "\nList= " + ethnicity + genderString + 
            "\nPlease Note: If this is a custom name that you have added, disregard this message." +
            "\n------------------" );
      }
   }
 
   public void readStats() {
      if(mode==1) {//random mode - defaults to level one
         statsGen = new StatsGen(1, myRace, false);  
      }
      else { //specific or custom modes
      
      }
   }

}