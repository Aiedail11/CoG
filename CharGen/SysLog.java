import java.util.*;
import java.io.*;
import java.text.*;

public class SysLog
{
   Scanner scan;
   boolean logging;
   String timeStamp = "";
   boolean firstLog = true;
   boolean rennamedLog = false;
   CharGen charGenInstance;
//this is a normal scanner that outputs to the console and a text file

   public SysLog(CharGen c)
   {
      charGenInstance = c;
   }
   public void startLog()
   {
      logging = true;
   }
   public void stopLog()
   {
      println("Session saved in \\Logs\\" + timeStamp + ".txt");
      logging = false;
   }
   public void startNewLog()
   {
      firstLog = false;
      timeStamp = renameFile(timeStamp);
   }
   public void renameLog()
   {
      Scanner console = new Scanner(System.in);
      rennamedLog = true;
      print("\nEnter desired log name: ");
      timeStamp = console.nextLine();
   }
   public String takeInput()
   {
   Scanner console = new Scanner(System.in);
      charGenInstance.setPrompt(charGenInstance.getPrompt().split("> ")[0] + "# ");
      println();
      String temp =  console.nextLine();
      if(logging){printlnToLog(charGenInstance.getPrompt() + temp);}
      charGenInstance.setPrompt(charGenInstance.getPrompt().split("# ")[0] + "> ");
      return temp;
   }
   public void print(String s)
   {
      try {
         if(firstLog){timeStamp = new SimpleDateFormat("yyyy.MM.dd_hh").format(new Date());}
         File logFile = new File(".\\Logs\\"+timeStamp+".txt");
         FileWriter logTxt = new FileWriter(logFile, true);
         
         
         System.out.print( charGenInstance.getPrompt()+s);
         try{
            if(logging){logTxt.append(charGenInstance.getPrompt()+s);}
         }
         catch(IOException  fe){
            fe.printStackTrace();
         }
         try{
            logTxt.flush();
            logTxt.close();
         } 
         catch (Exception e) {
            System.err.println("Error in Flush/close line 816:");
            e.printStackTrace();
         }
      
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
      catch(IOException  fe){
         fe.printStackTrace();
      }
         
   }
   public void printlnToLog(String s)
   {
      try {
         if(firstLog){timeStamp = new SimpleDateFormat("yyyy.MM.dd_hh").format(new Date());}
         File logFile = new File(".\\Logs\\"+timeStamp+".txt");
         FileWriter logTxt = new FileWriter(logFile, true);
       
         try{
            if(logging){logTxt.append("\n" + charGenInstance.getPrompt()+s);}
         }
         catch(IOException  fe){
            fe.printStackTrace();
         }
         try{
            logTxt.flush();
            logTxt.close();
         } 
         catch (Exception e) {
            System.err.println("Error in Flush/close line 816:");
            e.printStackTrace();
         }
      
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
      catch(IOException  fe){
         fe.printStackTrace();
      }
   
   
   }
   public void printlnToLog()
   {
      try {
         if(firstLog){timeStamp = new SimpleDateFormat("yyyy.MM.dd_hh").format(new Date());}
         File logFile = new File(".\\Logs\\"+timeStamp+".txt");
         FileWriter logTxt = new FileWriter(logFile, true);
         
         try{
            if(logging){logTxt.append("\n" + charGenInstance.getPrompt());}
         }
         catch(IOException  fe){
            fe.printStackTrace();
         }
         try{
            logTxt.flush();
            logTxt.close();
         } 
         catch (Exception e) {
            System.err.println("Error in Flush/close line 816:");
            e.printStackTrace();
         }
      
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
      catch(IOException  fe){
         fe.printStackTrace();
      }
   
   }
   public void printToLog(String s)
   {
      try {
         if(firstLog){timeStamp = new SimpleDateFormat("yyyy.MM.dd_hh").format(new Date());}
         File logFile = new File(".\\Logs\\"+timeStamp+".txt");
         FileWriter logTxt = new FileWriter(logFile, true);
         
         try{
            if(logging){logTxt.append(charGenInstance.getPrompt()+s);}
         }
         catch(IOException  fe){
            fe.printStackTrace();
         }
         try{
            logTxt.flush();
            logTxt.close();
         } 
         catch (Exception e) {
            System.err.println("Error in Flush/close line 816:");
            e.printStackTrace();
         }
      
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
      catch(IOException  fe){
         fe.printStackTrace();
      }
   
   }
   public void println(String s)
   {
      try {
         if(firstLog){timeStamp = new SimpleDateFormat("yyyy.MM.dd_hh").format(new Date());}
         File logFile = new File(".\\Logs\\"+timeStamp+".txt");
         FileWriter logTxt = new FileWriter(logFile, true);
         
         
         //System.out.println( charGenInstance.getPrompt()+s);
         System.out.print( "\n"+charGenInstance.getPrompt()+s);
         
         try{
            if(logging){logTxt.append("\n"+charGenInstance.getPrompt()+s);}
         }
         catch(IOException  fe){
            fe.printStackTrace();
         }
         try{
            logTxt.flush();
            logTxt.close();
         } 
         catch (Exception e) {
            System.err.println("Error in Flush/close line 816:");
            e.printStackTrace();
         }
      
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
      catch(IOException  fe){
         fe.printStackTrace();
      }
   
   }
   public void println()
   {
      try {
         if(firstLog){timeStamp = new SimpleDateFormat("yyyy.MM.dd_hh").format(new Date());}
         File logFile = new File(".\\Logs\\"+timeStamp+".txt");
         FileWriter logTxt = new FileWriter(logFile, true);
         
         
         System.out.print("\n"+charGenInstance.getPrompt());
         try{
            if(logging){logTxt.append("\n" +charGenInstance.getPrompt());}
         }
         catch(IOException  fe){
            fe.printStackTrace();
         }
         try{
            logTxt.flush();
            logTxt.close();
         } 
         catch (Exception e) {
            System.err.println("Error in Flush/close line 816:");
            e.printStackTrace();
         }
      
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
      catch(IOException  fe){
         fe.printStackTrace();
      }
   
   }
   
   public String renameFile(String myName) 
   {
      Scanner console = new Scanner(System.in);
      int num = 1;
      File tempFile = new File(".\\\\Logs\\"+myName+".txt");
      String newName = "";
    //new File(".\\\\Saves\\"+nameArray[0]+".txt")
      println(myName + ".txt already exists. Overwrite (y/n)?");
      console.nextLine();
      String in1 = console.nextLine();
      if(in1.equalsIgnoreCase("y"))
      {
         println("File will be overwritten.");
         newName = ".\\\\Logs\\"+myName+".txt";
         return newName;
      }
      else
      {
      //appends this number to name, and increments until the file will not overwrite a
         //previously existing one
         
       //  log.println("you said nooooo"); Thanks, Alex
         while(tempFile.exists())
         {
         // log.println("Hello"); Thanks, Alex
            num++;
            tempFile = new File(".\\\\Logs\\"+myName+num+".txt");
         }
      }
      newName = myName + num;
      println("Log saved as: " + newName);
      return ".\\\\Logs\\"+newName+".txt";
   }


}