public class Helper
{
   private int specificCount;
   private SysLog log;
   public Helper(SysLog s)
   {
      specificCount=0;
      log = s;
   
   }
   public void specific()
   {   //corresponds to CharGen.specific()
      
      log.println("Below are the available keywords, with options."+
         "\nEach command should be entered on a separate line."+ 
         "\n"+ 
         "\nNAME"+
             "\n\tOptions (Pick at least one): "+
             "\n"+
             "\n\t"+"-f = Generate with first and last name"+
             "\n"+
             "\n\t"+"-g {Male|Female} = Specify the desired gender"+
               "\n\t\t"+"Example: name -g female"+
             "\n"+ 
             "\n\t"+"-e [ethnicity] = Specify the desired ethnicity" +
                "\n\t\t"+"Example: name -e irish"+
             "\n"+   
             "\n\t"+"-n [name] = Specify the desired name"+    
                "\n\t\t"+"Example: name -n Alistair"+
                "\n\t\t"+"Example: name -f -n Alistair Flynn"+
                "\n\t\t"+"Please note: "+ 
                "\n\t\t"+"The order of the options is significant."+
                "\n\t\t"+"The name specified is case-sensitive."+       
         "\n"+ 
         "\nRACE = Generate and display race" +
          "\n\tOptions (not required):"+ 
          "\n"+
          "\n\t-r [race] = Specify the desired race"+ 
            "\n\t\t"+"Example: race -r human"+
         "\n"+ 
         "\nCLASS = Generate and display class" +
          "\n\tOptions (not required):"+ 
          "\n"+
          "\n\t-c [class] = Specify the desired class"+ 
            "\n\t\t"+"Example: class -c heart"+
         "\n"+ 
         "\nSTATS" +
            "\n\tDependencies: Race"+
            "\n"+
            "\n\tOptions (not required):"+
            "\n"+
            "\n\t-a [number] = Specify the desired level"+
               "\n\t\t"+"Example: stats -a 3"+
            "\n"+
            "\n\t-i = Use interactive stat generation"+
         "\n"+     
         "\nSTART = End command entry and begin generation"+
         "\n"+  
         "\nTo combine options, list all options with their respective parameters"+
         "\non the same line as the command, like this:"+
         "\nname -g female -e irish -f"  );
      
   }
   public void enterMode()
   {
      System.out.println("\nAvailable Modes:"+
                         "\nRandom - generate all fields with default options" +
                         "\nSpecific - generate specific fields with customization options" +
                         "\nCustom - Haven't decided what this does yet."+
                         "\nDM Presets  - generate all fields with DM's preset options"+
                         "\nTesting - I don't even know what this does anymore."+
                         "\nLog [Enable|Disable] - Toggle logging");
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