import java.util.*;
import java.io.*;
import java.util.*;

public class CharModel {
   //physical attributes
   private int age, height;
   //metadata
   private String creator;
   //stats
   private int endurance, strength, constitution, dexterity, 
   draiocht, wisps, replenishRate, arcana;
   
   private boolean hasStats = false;
   
   private String myEthnicity = "";
   private String genderString = "";
   private int myGender = -1, ethNum = -1;
   private String myRace = "";
   private String myClass = "";
   private String myName = "";
   private int myLevel = 1;
   
  // String[] fields = new String[age, height, ];

  

   
   public CharModel(String creator) {
      hasStats =false;
      this.creator = creator;
   }
   public void setName(String name, String eth, int gen)
   {
      myName = name;
      myEthnicity = eth;
      myGender = gen;
   
      if(gen==1) {
         genderString = "Female";
      }
      if(gen==0) {
         genderString = "Male";
      }
   }
   public void setRace(String r)
   {
      myRace = r;
   }
   public void setClass(String c)
   {
      myClass = c;
   }
   public void setStats(StatsGen statsGen)
   {
      hasStats = true;
      myLevel = statsGen.level;
      wisps = statsGen.wisps;
                  
      endurance = statsGen.end;
      dexterity = statsGen.dex;
      strength = statsGen.str;
      arcana =statsGen.arc;
                  
      draiocht=statsGen.draiocht;
      replenishRate = statsGen.replenishRate;
   }
   public String toString()
   {
      String charString = "";
      if(!myName.equals(""))
      {
         charString+="\nName: " + myName +
                  "\nOrigin of Name: " +myEthnicity +
                  "\nGender: " + genderString;
      
      }
       
      if(!myClass.equals("")){                  
         charString+="\n\nClass: " + myClass;
      }
                  
      if(!myRace.equals("")){
         charString+="\nRace: " + myRace;
      }
      if(hasStats)
      {
         charString+="\nLevel: " + myLevel+
                  "\nWisps: " + wisps + "\n"+
                  
                  "\nEndurance: " + endurance+
                  "\nDexterity: " + dexterity+
                  "\nStrength: " + strength+
                  "\nArcana: " + arcana + "\n"+
                  
                  "\nDraiocht: " + draiocht+
                  "\nReplenish Rate: " + replenishRate;
      }
      charString+="\n------------------\n";
      return charString;
   }
   public void openSaved(Scanner s)
   {
      while(s.hasNextLine()){
         String temp = s.nextLine();
         checkForName(temp);
         checkForClass(temp);
         checkForRace(temp);
         checkForStats(temp);
         checkMetadata(temp);
      }
   }
   public void checkForStats(String temp)
   {
  //  endurance, strength, dexterity, 
//    draiocht, wisps, replenishRate, arcana;
      if(temp.contains("Level: "))
      {
         hasStats = true;
         myLevel = Integer.parseInt(temp.substring("Level: ".length()+1, temp.length()));
      }
      if(temp.contains("Wisps: "))
      {
         wisps = Integer.parseInt(temp.substring("Wisps: ".length()+1, temp.length()));
      }
       if(temp.contains("Dexterity: "))
      {
         dexterity = Integer.parseInt(temp.substring("Dexterity: ".length()+1, temp.length()));
      }
      if(temp.contains("Draiocht: "))
      {
         draiocht = Integer.parseInt(temp.substring("Draiocht: ".length()+1, temp.length()));
      }if(temp.contains("Endurance: "))
      {
         endurance = Integer.parseInt(temp.substring("Endurance: ".length()+1, temp.length()));
      }
   
   }
   public void checkForName(String temp){
      if(temp.contains("Name: "))
      {
         myName = temp.substring("Name: ".length()+1, temp.length());
      }
      if(temp.contains("Origin of Name: ")){
         myEthnicity = temp.substring("Origin of Name: ".length()+1, temp.length());
      }
      if(temp.contains("Gender: ")){
         genderString = temp.substring("Gender: ".length()+1, temp.length());
         if(genderString.equals("Female")) {
         
            myGender = 1;
         }
         if(genderString.equals("Male")) {
         
            myGender = 0;
         }
      }
   }
   public void checkForClass(String temp)
   {
      if(temp.contains("Class: "))
      {
         myClass = temp.substring("Class: ".length()+1, temp.length());
      }
   }
   public void checkForRace(String temp)
   {
      if(temp.contains("Race: "))
      {
         myRace = temp.substring("Race: ".length()+1, temp.length());
      }
   }
}