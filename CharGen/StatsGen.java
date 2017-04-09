import java.util.*;
import java.io.*;

public class StatsGen {   
  
   SysLog log;
   int draiocht;
   int points = 30;
   int replenishRate;
   int wisps;
   int level;
   boolean customConfig;
   
   int end = 5;  //endurance  
   int str = 5; //strength   
   int dex = 5; //dexterity  
   int arc = 5; //arcana     
   // priority set to 'any' makes it random
   
   int endPriority = 4;
   int strPriority = 4;
   int dexPriority = 4;
   int arcPriority = 4;
   
   String race;
   
   //draiocht die roll by race
   final int DWARF_MANA_DIE = 5;
   final int ELF_MANA_DIE = 20;
   final int HUMAN_MANA_DIE = 20;
   final int HALFELF_MANA_DIE = 20;
   final int HALFORC_MANA_DIE = 5;
   final int HOBBIT_MANA_DIE = 5;
   final int DRAGONBORN_MANA_DIE = 10;
   final int DROW_MANA_DIE = 20;
   final int ELADRIN_MANA_DIE = 20;
   final int TIEFLING_MANA_DIE = 10;
   final int FAUNUS_MANA_DIE = 20;
   
   //draiocht guaranteed bonus by race
   final int DWARF_MANA_BONUS = 0;
   final int ELF_MANA_BONUS = 15;
   final int HUMAN_MANA_BONUS = 4;
   final int HALFELF_MANA_BONUS = 10;
   final int HALFORC_MANA_BONUS = 0;
   final int HOBBIT_MANA_BONUS = 0;
   final int DRAGONBORN_MANA_BONUS = 0;
   final int DROW_MANA_BONUS = 10;
   final int ELADRIN_MANA_BONUS = 0;
   final int TIEFLING_MANA_BONUS = 0;
   final int FAUNUS_MANA_BONUS = 10;
   
   //DRAIOCHT - SPECIAL RULES BY RACE
   //drows roll their mana die twice and add the results togeter before adding the bonus
   final int DROW_DRAIOCHT_ROLLS = 2; 
   //eladrin roll their mana die thrice and take the sum of the results   
   final int ELADRIN_DRAIOCHT_ROLLS = 3;
   //if the eladrin's rolls total a number below 15, their draiocht automatically becomes 15
   final int ELADRIN_DRAIOCHT_MIN = 15;   
   //all other races roll once and have no minimum draiocht, 
   //however this funcationality is built in, just in case 
   final int DWARF_DRAIOCHT_ROLLS = 1;
   final int ELF_DRAIOCHT_ROLLS = 1;
   final int HUMAN_DRAIOCHT_ROLLS = 1;
   final int HALFELF_DRAIOCHT_ROLLS = 1;
   final int HALFORC_DRAIOCHT_ROLLS = 1;
   final int HOBBIT_DRAIOCHT_ROLLS = 1;
   final int DRAGONBORN_DRAIOCHT_ROLLS = 1;
   final int TIEFLING_DRAIOCHT_ROLLS = 1;
   final int FAUNUS_DRAIOCHT_ROLLS = 1;
   
   final int DWARF_DRAIOCHT_MIN = 0;
   final int ELF_DRAIOCHT_MIN = 0;
   final int HUMAN_DRAIOCHT_MIN = 0;
   final int HALFELF_DRAIOCHT_MIN = 0;
   final int HALFORC_DRAIOCHT_MIN = 0;
   final int HOBBIT_DRAIOCHT_MIN = 0;
   final int DRAGONBORN_DRAIOCHT_MIN = 0;
   final int TIEFLING_DRAIOCHT_MIN = 0;
   final int FAUNUS_DRAIOCHT_MIN = 0;
   final int DROW_DRAIOCHT_MIN = 0;

   //REPLENISH RATE BY RACE
   final int DWARF_REPLENISH = 1;
   final int ELF_REPLENISH = 3;
   final int HUMAN_REPLENISH = 8;
   final int HALFELF_REPLENISH = 5;
   final int HALFORC_REPLENISH = 1;
   final int TIEFLING_REPLENISH = 2;
   final int ELADRIN_REPLENISH = 4;
   final int DRAGONBORN_REPLENISH = 2;
   final int DROW_REPLENISH = 3;
   final int HOBBIT_REPLENISH = 2;
   final int FAUNUS_REPLENISH = 3;
   
   //Dwarf minimum stats:
   int dwarf_min_end = 15;
   
   //Elf minimum stats (PICK ONE):
   int elf_min_dex = 13;
   int elf_min_arc = 13;
   
   //Half-Elf minimum stats (PICK ONE):
   int halfelf_min_dex = 12;
   int halfelf_min_arc = 12;
   
   //half elf minimum stats -- this one is guaranteed, no matter what
   int halfelf_min_end = 11;
   
   //Half orc min stats:
   int halforc_min_str = 13;
   
   //human min stats (ALWAYS USE BOTH)
   int human_min_end = 12;
   int human_min_str = 11;
   
   //tiefling min stats
   int tiefling_min_end = 13;
   
   //dragonborn min stats (ALWAYS USE BOTH)
   int dragonborn_min_str = 12;
   int dragonborn_min_end = 13;
   
   //drow min stats
   //drow have four extra points to add to either arcana
   //or dexterity, these then become the min requirement for
   //these stats
   int drow_arcdex_bonus = 4;
   
   //eladrin min stats -- follows drow's rules
   int eladrin_arcdex_bonus = 5;
   
   //hobbit min stats
   int hobbit_min_end = 12;
   
   //faunus race bonus
   int faunus_any_bonus = 2;
   
   public StatsGen(SysLog s, int level2, String race2, boolean customConfig2) {
      level = level2;
      race = race2;
      log = s;
      customConfig = customConfig2;
      for(int k = level; k>1; k--) {
         wisps+=100;
      }
      calcDraiocht();
      calcBaseStats();
      calcExtraStats();
   }
  
   public void calcBaseStats() {
      int choice = 0;
      if(race.equals("Dwarf")) {
         if(!customConfig) {dwarfStdConfig();}
         else {dwarfCstmConfig();}
      }
      if(race.equals("Elf")) {
         if(customConfig) {elfCstmConfig();}
         else {elfStdConfig();}
      }
      if(race.equals("Human")) {
         if(customConfig) {humanCstmConfig();}
         else {humanStdConfig();}
      }
      if(race.equals("Half-elf")) { 
         if(customConfig) {halfelfCstmConfig();}
         else {halfelfStdConfig();}
         end = halfelf_min_end;
         points-=5;
      }
      if(race.equals("Half-orc")) {
         if(customConfig) {halforcCstmConfig();}
         else {halforcStdConfig();}
      }
      if(race.equals("Hobbit")) {
         if(customConfig) {halforcCstmConfig();}
         else {halforcStdConfig();}
         
      }
      if(race.equals("Dragonborn")) {
         if(customConfig) {dragonbornCstmConfig();}
         else {dragonbornStdConfig();}
      }
      if(race.equals("Drow")) {
         if(customConfig) {drowCstmConfig();}
         else {drowStdConfig();}
      }
      if(race.equals("Eladrin")) {
         if(customConfig) {eladrinCstmConfig();}
         else {eladrinStdConfig();}
      }
      if(race.equals("Tiefling")) {
         if(customConfig) {tieflingCstmConfig();}
         else {tieflingStdConfig();}
      }
      if(race.equals("Faunus")) {
         if(customConfig) {faunusCstmConfig(); }
         else {faunusStdConfig();}
      
      
      }
   //main section, after race is accounted for
      int totalPriority = endPriority + strPriority + arcPriority + dexPriority;
      int lookAhead;
      int lastPoints;
      int firstRange = endPriority;
      int secondRange = (endPriority+strPriority);
      int thirdRange = (endPriority+strPriority+arcPriority);
      int impossible = 0;
      
      while(points>0 && impossible <4) {
         int statChoice = (int)(Math.random()*totalPriority) +1;
         lastPoints = points;
         impossible = 0;
         //ENDURANCE PROCESS
         if(end>=20 && statChoice >0 && statChoice <= endPriority) {
            lookAhead = points-(int)((end+1)/10);
         }
         else {
            lookAhead = points - 1;
         }
         if(statChoice >0 && statChoice <= firstRange && lookAhead>=0) { // zero to endPriority ex: (0, 4]
            end++; //increment endurance
            points = lookAhead;
         }
         else if(lookAhead<0)
         {impossible++;}
         //STRENGTH PROCESS
         if(statChoice >firstRange && statChoice <= secondRange &&str>=20) {
            lookAhead = points-(int)((str+1)/10);
         }
         else {
            lookAhead = points - 1;
         }
         if(statChoice >firstRange && statChoice <= secondRange &&lookAhead>=0) { // endPriority to (endPriority+strPriority) ex: (4, 8]
            str++; //increment strength
            points = lookAhead; 
         }
         else if(lookAhead<0)
         {impossible++;}
         //ARCANA PROCESS
         if(arc>=20 && statChoice >secondRange && statChoice <= thirdRange) {
            lookAhead = points-(int)((arc+1)/10);
         }
         else {
            lookAhead = points - 1;
         }
         if(statChoice >secondRange && statChoice <= thirdRange &&lookAhead>=0) { // (endPriority+strPriority) to (end+str+arc) ex: (8, 12]
            arc++; //increment arcana
            points = lookAhead; 
         }
         else if(lookAhead<0)
         {impossible++;}
          //DEXTERITY PROCESS
         if(statChoice >thirdRange && statChoice <= (totalPriority)&&dex>=20) {
            lookAhead = points-(int)((dex+1)/10);
         }
         else {
            lookAhead = points - 1;
         }
         if(statChoice >thirdRange && statChoice <= (totalPriority)&& lookAhead>=0) { // (end+str+arc) to (end+str+arc+dex) ex: (12, 16]
            dex++; //increment dexterity
            points = lookAhead;
         }
         else if(lookAhead<0)
         {impossible++;}
         if(lastPoints==points) {
            incrementLowest();
            return;
         }
      }
   }
   
   public void calcExtraStats() { 
      int totalPriority = endPriority + strPriority + arcPriority + dexPriority;
      int lookAhead = 0;
      int firstRange = endPriority;
      int lastWisps = 0;
      int secondRange = (endPriority+strPriority);
      int thirdRange = (endPriority+strPriority+arcPriority);
      int statChoice = 0;
      int impossible = 0; //this is incremented when no stats cannot be increased, and
     //when it reaches four, no more stats can be increased
     
      while(wisps>0 && impossible <4) {
         statChoice = (int)(Math.random()*totalPriority) +1;
         lastWisps = wisps;
         impossible = 0;
         
         //ENDURANCE PROCESS
         if( statChoice >0 && statChoice <= endPriority) {
            lookAhead = calcWispCost(end);
         }
         if(statChoice >0 && statChoice <= firstRange && lookAhead>=0) { // zero to endPriority ex: (0, 4]
           
            end++; //increment endurance
            wisps = lookAhead;
            
         }
         else if(lookAhead<0)
         {impossible++;}
         
         //STRENGTH PROCESS
         if(statChoice >firstRange && statChoice <= secondRange ) {
            lookAhead = calcWispCost(str);
         }
       
         if(statChoice >firstRange && statChoice <= secondRange &&lookAhead>=0) { // endPriority to (endPriority+strPriority) ex: (4, 8]
            str++; //increment strength
            wisps = lookAhead; 
         }
         else if(lookAhead<0)
         {impossible++;}
         //ARCANA PROCESS
         if( statChoice >secondRange && statChoice <= thirdRange) {
            lookAhead = calcWispCost(arc);
         }
         
         if(statChoice >secondRange && statChoice <= thirdRange &&lookAhead>=0) { // (endPriority+strPriority) to (end+str+arc) ex: (8, 12]
           
            arc++; //increment arcana
            wisps = lookAhead; 
         }
         else if(lookAhead<0)
         {impossible++;}
         
         
         //DEXTERITY PROCESS
         if(statChoice >thirdRange && statChoice <= (totalPriority)) {
            lookAhead = calcWispCost(dex);
         }
         
         if(statChoice >thirdRange && statChoice <= (totalPriority)&& lookAhead>=0) { // (end+str+arc) to (end+str+arc+dex) ex: (12, 16]
          
            dex++; //increment dexterity
            wisps = lookAhead;
         
            
         }
         else if(lookAhead<0)
         {impossible++;}
       
      }
      
   } //end calcExtraStats()
   public boolean someStatPossible()
   {
      boolean answer = true;
      if(calcWispCost(dex) > 0 && calcWispCost(end) > 0 && calcWispCost(arc) > 0 && calcWispCost(str) > 0)
      {answer = false;}
      return answer;
   }
   public int calcWispCost(int curStat) 
   { 
   //returns the number of wisps remaining if this stat were increased
      int desiredStat = curStat++;
      //curStat is what this stat is currently, before incrementing.
      //We are trying to figure out what will happen to wisps if the stat increments.
      int predictedWisps = -1; //this is what will happen to wisps
      if(desiredStat<=10)
      {
         predictedWisps = wisps - 20;
         //log.println("Current: " + wisps + " Predicted: " + predictedWisps);
      }
      else
      {
         switch(curStat)
         {
            case 11:
               predictedWisps = wisps -22;
              // log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            case 12:
               predictedWisps = wisps -26;
              // log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            case 13:
               predictedWisps = wisps -30;
               //log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            case 14:
               predictedWisps = wisps -50;
               //log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            case 15:
               predictedWisps = wisps -80;
              // log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            case 16:
               predictedWisps = wisps -150;
              // log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            case 17:
               predictedWisps = wisps -210;
              // log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            case 18:
               predictedWisps = wisps -350;
              // log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            case 19:
               predictedWisps = wisps -600; 
              // log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            case 20:
               predictedWisps = wisps -1000;
               //log.println("Current: " + wisps + " Predicted: " + predictedWisps);
               break;
            default:
               predictedWisps = wisps - 1500;
               log.printlnToLog("WARNING: exceeds currently programmed stat ranges - see calcWispCost(int curStat)");
               break;
         }
      }
      return predictedWisps;
   }
   
   public void calcDraiocht() {
      draiocht=0;
      if(race.equals("Dwarf")) {
         calcDraiochtSubroutine(DWARF_DRAIOCHT_MIN, DWARF_DRAIOCHT_ROLLS, DWARF_MANA_DIE, 
            DWARF_MANA_BONUS, DWARF_REPLENISH);
      }
      if(race.equals("Elf")) {
         calcDraiochtSubroutine(ELF_DRAIOCHT_MIN, ELF_DRAIOCHT_ROLLS, ELF_MANA_DIE, 
            ELF_MANA_BONUS, ELF_REPLENISH);     
      }
      if(race.equals("Human")) {
         calcDraiochtSubroutine(HUMAN_DRAIOCHT_MIN, HUMAN_DRAIOCHT_ROLLS, HUMAN_MANA_DIE, 
            HUMAN_MANA_BONUS, HUMAN_REPLENISH); 
      }
      if(race.equals("Half-elf")) {
         calcDraiochtSubroutine(HALFELF_DRAIOCHT_MIN, HALFELF_DRAIOCHT_ROLLS, HALFELF_MANA_DIE, 
            HALFELF_MANA_BONUS, HALFELF_REPLENISH); 
      }
      if(race.equals("Half-orc")) {
         calcDraiochtSubroutine(HALFORC_DRAIOCHT_MIN, HALFORC_DRAIOCHT_ROLLS, HALFORC_MANA_DIE, 
            HALFORC_MANA_BONUS, HALFORC_REPLENISH); 
      }
      if(race.equals("Hobbit")) {
         calcDraiochtSubroutine(HOBBIT_DRAIOCHT_MIN, HOBBIT_DRAIOCHT_ROLLS, HOBBIT_MANA_DIE, 
            HOBBIT_MANA_BONUS, HOBBIT_REPLENISH); 
      }
      if(race.equals("Dragonborn")) {
         calcDraiochtSubroutine(DRAGONBORN_DRAIOCHT_MIN, DRAGONBORN_DRAIOCHT_ROLLS, DRAGONBORN_MANA_DIE, 
            DRAGONBORN_MANA_BONUS, DRAGONBORN_REPLENISH); 
      }
      if(race.equals("Drow")) {
         calcDraiochtSubroutine(DROW_DRAIOCHT_MIN, DROW_DRAIOCHT_ROLLS, DROW_MANA_DIE, 
            DROW_MANA_BONUS, DROW_REPLENISH); 
      }
      if(race.equals("Eladrin")) {
         calcDraiochtSubroutine(ELADRIN_DRAIOCHT_MIN, ELADRIN_DRAIOCHT_ROLLS, ELADRIN_MANA_DIE, 
            ELADRIN_MANA_BONUS, ELADRIN_REPLENISH); 
      }
      if(race.equals("Tiefling")) {
         calcDraiochtSubroutine(TIEFLING_DRAIOCHT_MIN, TIEFLING_DRAIOCHT_ROLLS, TIEFLING_MANA_DIE, 
            TIEFLING_MANA_BONUS, TIEFLING_REPLENISH); 
      }
      if(race.equals("Faunus")) {
         calcDraiochtSubroutine(FAUNUS_DRAIOCHT_MIN, FAUNUS_DRAIOCHT_ROLLS, FAUNUS_MANA_DIE, 
            FAUNUS_MANA_BONUS, FAUNUS_REPLENISH); 
      }
      //that awkward moment when you replenish more in an hour than you actually can ever have
      //well that doesn't happen anymore thanks to this handy little algorithim
      if(replenishRate>draiocht) {
         replenishRate = draiocht;
      }
     
   }
   public void incrementLowest() { //dependent on at least one stat being less than 20
      points--;
      int temp1 = Math.min(arc, dex);
      int temp2 = Math.min(temp1, end);
      int temp3 = Math.min(temp2, str);
      
      if(temp3==arc) {
         arc++;
      }
      else if(temp3==dex) {
         dex++;
      }
      else if(temp3==end) {
         end++;
      }
      else if(temp3==str) {
         str++;
      }
   }
   public void calcDraiochtSubroutine(
      int draiochtMin, int draiochtRolls, 
      int manaDie, int manaBonus, 
      int replenish)
   {
      for(int a = 0; a < draiochtRolls;a++) {
         draiocht += (int)(Math.random()*manaDie)+ 1;
      }
      draiocht+=manaBonus;
      if(draiocht<draiochtMin) {
         draiocht = draiochtMin;
      }
      replenishRate = replenish;
   }
   //base stat configs by race
   public void dwarfStdConfig()
   {
      end = dwarf_min_end;
      points-=5;
   }
   public void dwarfCstmConfig()
   {
      log.println("Custom configuration is not available for race: " + race);
   }
   public void elfStdConfig()
   {
      int choice = (int)(Math.random()*1) + 1;
      switch (choice) {
         case 1: 
            dex = elf_min_dex;
            points-=5;
            break;
         case 2:
            arc = elf_min_arc;
            points-=5;
            break;
      }
   
   }
   public void elfCstmConfig()
   {
      log.println("Enter the number corresponding to the desired base stat (pick one)." +
               "\n1. Dexterity: " + elf_min_dex+"\n2. Arcana: "+elf_min_arc);
      int choice = Integer.parseInt(log.takeInput());
      switch (choice) {
         case 1: 
            dex = elf_min_dex;
            points-=5;
            break;
         case 2:
            arc = elf_min_arc;
            points-=5;
            break;
      }
   
   }
   public void humanStdConfig()
   {
      end = human_min_end;
      str = human_min_str;
      points-=5;
      points-=5;
   }
   public void humanCstmConfig()
   {
      log.println("Custom configuration is not available for race: " + race);
   }
   public void halfelfStdConfig()
   {
      int choice = (int)(Math.random()*1) + 1;
      switch (choice) {
         case 1: 
            dex = halfelf_min_dex;
            points-=5;
            break;
         case 2:
            arc = halfelf_min_arc;
            points-=5;
            break;
      }
   
   }
   public void halfelfCstmConfig()
   {
      log.println("Enter the number corresponding to the desired base stat (pick one)." +
               "\n1. Dexterity: " + halfelf_min_dex+"\n2. Arcana: "+halfelf_min_arc);
      int choice = Integer.parseInt(log.takeInput());
      switch (choice) {
         case 1: 
            dex = halfelf_min_dex;
            points-=5;
                 
            break;
         case 2:
            arc = halfelf_min_arc;
            points-=5;
            break;
      }
   }
   public void halforcStdConfig()
   {
      str = halforc_min_str;
      points-=5;
   }
   public void halforcCstmConfig()
   {
      log.println("Custom configuration is not available for race: " + race);
   }
   public void tieflingStdConfig()
   {
      end = tiefling_min_end;
      points-=5;
   }
   public void tieflingCstmConfig()
   {
      log.println("Custom configuration is not available for race: " + race);
   }
   public void eladrinStdConfig()
   {
      int choice = (int)(Math.random()*(eladrin_arcdex_bonus+1));
      arc = 10 + choice;
      points -=5;
      eladrin_arcdex_bonus-=choice;
      choice = (int)(Math.random()*(eladrin_arcdex_bonus+1));
      dex = 10 + choice;
      points -=5;
         
   }
   public void eladrinCstmConfig()
   {
   //temp -> {stat, bonus, points}
      int[] temp = new int[3];
      temp=flexibleBonusPoints(arc, eladrin_arcdex_bonus, "Arcana", "arcana and dexterity");
      arc = temp[0]; 
      eladrin_arcdex_bonus = temp[1];
      points = temp[2];
      if(eladrin_arcdex_bonus > 0){
         temp=flexibleBonusPoints(dex, eladrin_arcdex_bonus, "Dexterity", "arcana and dexterity");
         dex = temp[0];
         eladrin_arcdex_bonus = temp[1];
         points = temp[2];
      }
      if(eladrin_arcdex_bonus>0){
         log.println("Error: You did not designate all bonus points. Generation will continue.");
   
      }
   }
   public void dragonbornStdConfig()
   {
      str = dragonborn_min_str;
      end = dragonborn_min_end;
      points-=5;
      points-=5;
   }
   public void dragonbornCstmConfig()
   {
      log.println("Custom configuration is not available for race: " + race);
   }
   public void drowStdConfig()
   {
      int choice = (int)(Math.random()*(drow_arcdex_bonus+1));
      arc = 10 + choice;
      points -=5;
      drow_arcdex_bonus-=choice;
      choice = (int)(Math.random()*(drow_arcdex_bonus+1));
      dex = 10 + choice;
      points -=5;
   }
   public void drowCstmConfig()
   {
   //temp -> {stat, bonus, points}
      int[] temp = new int[3];
      temp=flexibleBonusPoints(arc, drow_arcdex_bonus, "Arcana", "arcana and dexterity");
      arc = temp[0]; 
      drow_arcdex_bonus = temp[1];
      points = temp[2];
      if(eladrin_arcdex_bonus > 0){
         temp=flexibleBonusPoints(dex, drow_arcdex_bonus, "Dexterity", "arcana and dexterity");
         dex = temp[0];
         drow_arcdex_bonus = temp[1];
         points = temp[2];
      }
      if(eladrin_arcdex_bonus>0){
         log.println("Error: You did not designate all bonus points. Generation will continue.");
       
      }
   }
   public void hobbitStdConfig()
   {
      end = hobbit_min_end;
      points-=5;
   }
   public void hobbitCstmConfig()
   {
      log.println("Custom configuration is not available for race: " + race);
   }
   public void faunusStdConfig()
   {
      points+=faunus_any_bonus;
   }
   public void faunusCstmConfig()
   {
   //temp -> {stat, bonus, points}
      int[] temp = new int[3];
      temp=flexibleBonusPoints(arc, faunus_any_bonus, "Arcana", "arcana, strength, endurance and dexterity");
      arc = temp[0]; 
      faunus_any_bonus = temp[1];
      points = temp[2];
      if(faunus_any_bonus > 0){
         temp=flexibleBonusPoints(dex, faunus_any_bonus, "Dexterity", "arcana and dexterity");
         dex = temp[0];
         faunus_any_bonus = temp[1];
         points = temp[2];
      }
      if(faunus_any_bonus > 0){
         temp=flexibleBonusPoints(str, faunus_any_bonus, "Strength", "arcana and dexterity");
         str = temp[0];
         faunus_any_bonus = temp[1];
         points = temp[2];
      }
      if(faunus_any_bonus > 0){
         temp=flexibleBonusPoints(end, faunus_any_bonus, "Endurance", "arcana and dexterity");
         end = temp[0];
         faunus_any_bonus = temp[1];
         points = temp[2];
      }
      if(faunus_any_bonus>0){
         log.println("Error: You did not designate all bonus points. Generation will continue.");
      
      }
   }
   public int[] flexibleBonusPoints(int stat, int bonus, String statName, String allBonusTypes)
   {
      int choice = -1;
      log.println("You must now designate base "+allBonusTypes+". \nYou have " +
               bonus + " points to spend."+
               "\nHow many points to "+statName+"? (" + bonus +" points remaining)"+
               "\n"+statName+" is currently "+(stat+5));
      do{
         choice = Integer.parseInt(log.takeInput());
         if(choice >bonus) {
            log.println("ERROR: Number exceeded available bonus.");
            choice = -1;
         }
      }while(choice==-1);
      stat = 10+ choice;
      points-=5;
      bonus-=choice;
      int[] results = {stat, bonus, points};
      return results;
   }
}