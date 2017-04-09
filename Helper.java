public class Helper
{
   public Helper(String caller)
   {
      if(caller.equals("specific")) //corresponds to CharGen.specific()
      {
         System.out.println("\nName"+
                         "\nLevel (1-10)" +
                         "\nRace" +
                         "\nClass" +
                         "\nStats" +
                              "\n\tDependencies: Race");
      }
      else if(caller.equals("enterMode"))
      {
         System.out.println("\nRandom - generate all fields with default options" +
                         "\nSpecific - generate specific fields with customization options" +
                         "\nCustom - Haven't decided what this does yet."+
                         "\nDM Presets  - generate all fields with DM's preset options"+
                         "\nTesting - I don't even know what this does anymore.");
      }
      else if(caller.equals("specific_races"))
      {
         listRaces();
      }
      else if(caller.equals("specific_classes"))
      {listClasses();}
      else if(caller.equals("specific_ethnicities"))
      {listEthnicities();}
      else
      {
         System.out.println("Helper: \"" + caller + "\" is an invalid ID\n");
      }
   }
   public void listRaces()
   {
      System.out.println("Available Races (case sensitive):" +
         "\nElf"+
         "\nDwarf"+
         "\nHuman"+
         "\nHalf-elf"+
         "\nHalf-orc"+
         "\nTiefling"+
         "\nDragonborn"+
         "\nDrow"+
         "\nEladrin"+
         "\nHobbit"+
         "\nFaunus\n");
   }
   public void listClasses()
   {
      System.out.println("Available Classes (case sensitive):"+
         "\nFist"+
         "\nHeart"+
         "\nFace"+
         "\nSpine"+
         "\nMind\n"
         );
   }
   public void listEthnicities()
   {
      System.out.println("Available Ethnicites (case sensitive):"+
         "\nFrench"+
         "\nGerman"+
         "\nGreek"+
         "\nHebrew"+
         "\nHindu"+
         "\nIrish"+
         "\nItalian"+
         "\nJapanese"+
         "\nLatin"+
         "\nRussian\n"
         );
   }
}