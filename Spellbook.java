import java.util.*;
import java.io.*;
public class Spellbook
{
   private String owner;
   private ArrayList<String[]> spells = new ArrayList<String[]>(0);;
   private Scanner console;
   private boolean openedPreviousBook = false;
   public Spellbook(Scanner sc, String s)
   {
      owner = s;
      console = sc;
      //String[] spellHeader = {"Spell Name", "Threshold", "Description"};
     // spells.add(spellHeader);
   }
   public void createSpell()
   {
      System.out.print("Enter the name of the spell to be created: ");
      String n = console.nextLine();
      System.out.print("Enter the threshold of the spell (1-10000): ");
      String t = console.nextLine();
      System.out.print("Enter the description of the spell: ");
      String d = console.nextLine();
      String[] newSpell = {n, t, d};
      spells.add(newSpell);
   }
   public String[] removeSpell()
   {
      System.out.print("Enter the name of the spell to remove: ");
      String spellName = console.nextLine();
      String[] feedback = {""};
      for(int spellIndex = 0; spellIndex < spells.size(); spellIndex++)
      {
         if(spells.get(spellIndex)[0].equalsIgnoreCase(spellName))
         {
            feedback = spells.remove(spellIndex);
            System.out.println("\""+spellName + "\" removed successfully.");
         }
      }
      if(feedback[0].equals(""))
      {System.out.println("\"" + spellName + "\" not found.");}
      return feedback;
   }
   
   public String findSpellByDescription()
   {
      String relatedSpells = "";
   
      System.out.print("Enter the keywords that the description must contain: ");
      String input = console.nextLine();
      String[] testValues = input.toLowerCase().split(" ");
      boolean containsAll = true;
      for(int spellIndex = 0; spellIndex < spells.size(); spellIndex++)
      {
         String temp = spells.get(spellIndex)[2].toLowerCase();
         for(int i = 0; i < testValues.length; i++)
         {
            if(!temp.contains(testValues[i]))
            {
               containsAll=false;        
            }
         }
         if(containsAll)
         {
            relatedSpells+=getSpell(spellIndex)+"\n";
         }
      }
      if(relatedSpells.equals(""))
      {relatedSpells = "No related spells found.\n";}
      return relatedSpells;
      
   }
   public void setScanner(Scanner s)
   {
      console = s;
   }
   public String getSpell(int index)
   {
      String mySpell = "\nIndex is invalid.";
      if(index >-1 && index < spells.size())
      {
         mySpell = "\n" + spells.get(index)[0] +
            "\nd10,000 Threshold: " + spells.get(index)[1] +
            "\n" + spells.get(index)[2];
      }
      return mySpell;
   }
   public String findSpellByName()
   {
      System.out.print("Enter the name of the spell you wish to find: ");
      String name = console.nextLine();
      name = name.toLowerCase();
      String mySpell = "";
   
      for(int i = 0; i < spells.size(); i++)
      {
         if(spells.get(i)[0].toLowerCase().contains(name))
         {
            mySpell += "\n" + spells.get(i)[0] +
               "\nd10,000 Threshold: " + spells.get(i)[1] +
               "\n" + spells.get(i)[2]+"\n";
         
         }
      }
      if(mySpell.equals(""))
      {mySpell = "\""+name + "\" not found.\n";}
      return mySpell;
   }
   public String toString()
   {
      String mySpellbook = "\nThe Spellbook of "+ owner+"\n";
      for(int k = 0; k < spells.size(); k++)
      {
         mySpellbook+= getSpell(k) + "\n";
      }
      return mySpellbook;
   }
   public void save()
   {
      FileWriter fw;
      System.out.println("Spellbook will be saved as " + owner +"_Spellbook.txt in the Saves directory.");
      try{
         File theFile = new File(".\\\\Saves\\"+owner+"_Spellbook.txt"); 
         fw = new FileWriter(theFile, true);  //note that this overwrites a pre-existing file DUMMY
         
         try{
            if(!openedPreviousBook)
            {System.out.println("WARNING: You have not opened an existing spellbook."+
                  "\nThis will overwrite any existing versions of this spellbook.");
            System.out.println("Continue? (y/n)");
            String reply = console.nextLine().toLowerCase();
            if(reply.equals("n"))
            {return; }
            
            }
            fw.write(this.toString()); 
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
}