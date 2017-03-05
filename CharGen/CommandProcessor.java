public class CommandProcessor
{
   public CommandProcessor(int mode)
   {
      if(mode==0)
      { new Helper("enterMode"); }
      if(mode <1 || mode > 6)
      { CharGen.enterMode(); }
         
          
       //execute method for selected mode
      switch (mode) {
      
         case 1:   
            prompt = "CoG(random)# "; 
            usingStats=true;         
            random();
                  
            break;
         case 2: 
            prompt = "CoG(specific)# ";
            log.println("\nSpecific mode selected.");
            fullName =false;
            setRace = false;
            setClass = false;
            setGender = false;
            setName = false;
            setEthnicity = false;
         
            specific();
                  
            break;
         case 3:     
            prompt = "CoG(custom)# ";       
            custom();
            break;
         case 4: 
         //this will run random mode, however certain aspects of the generations 
         //will be overridden by the dm's presets
            prompt = "CoG(dmPresets)# ";
            dmPresets = true;
            usingStats=true;
            fullName = true;
            mode = 1;
            random();       
            break;
         case 5:  //TESTING MODE
            //this will run random mode, however the user will be prompted to enter
            //the level of the character
            prompt = "CoG(testing)# ";
            mode = 1;
            log.println("\nTesting mode selected.");
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
}