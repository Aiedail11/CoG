import java.util.*;
import java.io.*;
public class SpellbookTester
{
   public static void main(String[] args)
   {
   Scanner spellTxt = new Scanner("Error");
   try {
         File spellFile = new File(".\\TestSpellList.txt");
         spellTxt = new Scanner(spellFile);        
      } 
      catch (FileNotFoundException fnfe) {
         
         fnfe.printStackTrace();
      }
   Spellbook one = new Spellbook(spellTxt, "one");
   one.createSpell();
   one.createSpell();
   one.createSpell();
   System.out.println(one);
   one.removeSpell();
   System.out.println(one);
   one.setScanner(new Scanner(System.in));
   System.out.println(one.findSpellByDescription());
   System.out.println(one.findSpellByName());
  
  one.save();
   }
}