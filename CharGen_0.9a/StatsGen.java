import java.util.*;
import java.io.*;

public class StatsGen {   //FIND OUT WHY POINTS ISN'T RIGHT AT START OF NORMAL STAT CALCULATION CTRL F FOR 'POINTS'
   
   Scanner console = new Scanner(System.in);
   
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
   
   //Draiocht - special rules by race
   final int DROW_DRAIOCHT_ROLLS = 2; //drows roll their mana die twice and add the results togeter before adding the bonus
   
   final int ELADRIN_DRAIOCHT_ROLLS = 3;//eladrin roll their mana die thrice and take the sum of the results
   final int ELADRIN_DRAIOCHT_MIN = 15; //if the eladrin's rolls total a number below 15, their draiocht automatically becomes 15
   
   //all other races roll once and have no minimum draiocht, however this funcationality is built in, just in case 
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

   //replenish rate by race
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
   
   public StatsGen(int level2, String race2, boolean customConfig2) {
      level = level2;
      race = race2;
    
      customConfig = customConfig2;
      
      for(int k = level; k>1; k--) {
         wisps+=100;
      }
      
      calcDraiocht();
      calcBaseStats();
      if(level >1) {
         calcExtraStats();
      }
   
   }
   public void calcBaseStats() {
      int choice = 0;
      if(race.equals("Dwarf")) {
         end = dwarf_min_end;
         points-=5;
      }
      if(race.equals("Elf")) {
         if(customConfig) {
            System.out.println("Enter the number corresponding to the desired base stat (pick one)." +
               "1. Dexterity: " + elf_min_dex+"\n2. Arcana: "+elf_min_arc);
            choice = console.nextInt();
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
         else {
            choice = (int)(Math.random()*1) + 1;
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
      }
      if(race.equals("Human")) {
         end = human_min_end;
         str = human_min_str;
         points-=5;
         points-=5;
      }
      if(race.equals("Half-elf")) { 
         if(customConfig) {
            System.out.println("Enter the number corresponding to the desired base stat (pick one)." +
               "1. Dexterity: " + halfelf_min_dex+"\n2. Arcana: "+halfelf_min_arc);
            choice = console.nextInt();
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
         else {
            choice = (int)(Math.random()*1) + 1;
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
         end = halfelf_min_end;
         points-=5;
      }
      if(race.equals("Half-orc")) {
         str = halforc_min_str;
         points-=5;
      }
      if(race.equals("Hobbit")) {
         end = hobbit_min_end;
         points-=5;
      }
      if(race.equals("Dragonborn")) {
         str = dragonborn_min_str;
         end = dragonborn_min_end;
         points-=5;
         points-=5;
      }
      if(race.equals("Drow")) {
         if(customConfig) {
            System.out.println("You must now designate base arcana and dexterity. You have " +
               drow_arcdex_bonus + " points to spend."+
               "\nHow many points to arcana? (" + drow_arcdex_bonus +" points remaining)"+
               "\nArcana is currently 10");
            choice = console.nextInt();
            if(choice >drow_arcdex_bonus) {
               System.out.println("Error: Number exceeded available bonus. Zero points will be designated.");
               choice = 0;
            }
            arc = 10+ choice;
            points-=5;
            drow_arcdex_bonus-=choice;
            System.out.println("\nHow many points to dexterity? (" + drow_arcdex_bonus +" points remaining)"+
               "\nDexterity is currently 10");
            if(choice >drow_arcdex_bonus) {
               System.out.println("Error: Number exceeded available bonus. Zero points will be designated.");
               choice = 0;
            }
            choice = console.nextInt();
            dex = 10 + choice;
            points-=5;
         }
         else {
            choice = (int)(Math.random()*(drow_arcdex_bonus+1));
            arc = 10 + choice;
            points -=5;
            drow_arcdex_bonus-=choice;
            choice = (int)(Math.random()*(drow_arcdex_bonus+1));
            dex = 10 + choice;
            points -=5;
         }
      }
      if(race.equals("Eladrin")) {
         if(customConfig) {
            System.out.println("You must now designate base arcana and dexterity. You have " +
               eladrin_arcdex_bonus + " points to spend."+
               "\nHow many points to arcana? (" + eladrin_arcdex_bonus +" points remaining)"+
               "\nArcana is currently 10");
            choice = console.nextInt();
            if(choice >eladrin_arcdex_bonus) {
               System.out.println("Error: Number exceeded available bonus. Zero points will be designated.");
               choice = 0;
            }
            arc = 10+ choice;
            points-=5;
            eladrin_arcdex_bonus-=choice;
            System.out.println("\nHow many points to dexterity? (" + eladrin_arcdex_bonus +" points remaining)"+
               "\nDexterity is currently 10");
            
            choice = console.nextInt();
            if(choice >eladrin_arcdex_bonus) {
               System.out.println("Error: Number exceeded available bonus. Zero points will be designated.");
               choice = 0;
            }
            dex = 10 + choice;
            points-=5;
         }
         else {
            choice = (int)(Math.random()*(eladrin_arcdex_bonus+1));
            arc = 10 + choice;
            points -=5;
            eladrin_arcdex_bonus-=choice;
            choice = (int)(Math.random()*(eladrin_arcdex_bonus+1));
            dex = 10 + choice;
            points -=5;
         }
      
      }
      if(race.equals("Tiefling")) {
         end = tiefling_min_end;
         points-=5;
      }
      if(race.equals("Faunus")) {
         if(customConfig) {
            System.out.println("You must now designate base arcana, strength, endurance and dexterity. You have " +
               faunus_any_bonus + " points to spend."+
               "\nHow many points to arcana? (" + faunus_any_bonus +" points remaining)"+
               "\nArcana is currently 10");
            
            choice = console.nextInt();
            if(choice >faunus_any_bonus) {
               System.out.println("Error: Number exceeded available bonus. Zero points will be designated.");
               choice = 0;
            }
            arc = 10+ choice;
            if(arc!=5) {   
               points-=5;
            }
            faunus_any_bonus-=choice;
            
            if(points>0) {
               System.out.println("\nHow many points to dexterity? (" + faunus_any_bonus +" points remaining)"+
                  "\nDexterity is currently 10");
               choice = console.nextInt();
               if(choice >faunus_any_bonus) {
                  System.out.println("Error: Number exceeded available bonus. Zero points will be designated.");
                  choice = 0;
               }
               dex = 10 + choice;
               if(dex!=5) {   
                  points-=5;
               }
               faunus_any_bonus-=choice;
            }
            
            if(points>0) {
               System.out.println("\nHow many points to strength? (" + faunus_any_bonus +" points remaining)"+
                  "\nStrength is currently 10");
               choice = console.nextInt();
               if(choice >faunus_any_bonus) {
                  System.out.println("Error: Number exceeded available bonus. Zero points will be designated.");
                  choice = 0;
               }
               str = 10 + choice;
               if(str!=5) {   
                  points-=5;
               };
               faunus_any_bonus-=choice;
            }
         
            if(points>0) {
               System.out.println("\nHow many points to endurance? (" + faunus_any_bonus +" points remaining)"+
                  "\nEndurance is currently 10");
               choice = console.nextInt();
               if(choice >faunus_any_bonus) {
                  System.out.println("Error: Number exceeded available bonus. Zero points will be designated.");
                  choice = 0;
               }
               end = 10 + choice;
               faunus_any_bonus-=choice;
               points-=5;
            }
         }
         else {
            while(faunus_any_bonus>0) {
               choice = (int)(Math.random()*(4));
               switch (choice) {
                  case 0:
                     if(end==5) {
                        end+=5;
                        points-=5;
                     }
                     end++;
                     break;
                  case 1:
                     if(str==5) {
                        str+=5;
                        points-=5;
                     }
                     str++;
                     break;
                  case 2:
                     if(dex==5) {
                        dex+=5;
                        points-=5;
                     }
                     dex++;
                     break;
                  case 3:
                     if(arc==5) {
                        arc+=5;
                        points-=5;
                     }
                     arc++;
                     break;
               
               }
               faunus_any_bonus--;
            }
         }
      
      }
      if(customConfig) {
         if(faunus_any_bonus > 0 || eladrin_arcdex_bonus > 0 || drow_arcdex_bonus > 0) {
            System.out.println("Error: You did not designate all bonus points. Generation will continue.");
            System.out.println();
         }
      }
   //main section, after race is accounted for
      int totalPriority = endPriority + strPriority + arcPriority + dexPriority;
      
      
      
      int lookAhead;
      int lastPoints;
      int firstRange = endPriority;
      int secondRange = (endPriority+strPriority);
      int thirdRange = (endPriority+strPriority+arcPriority);
     
      
      while(points>0) {
         int statChoice = (int)(Math.random()*totalPriority) +1;
         lastPoints = points;
         
         
         //ENDURANCE PROCESS
         if(end>=20 && statChoice >0 && statChoice <= endPriority) {
            lookAhead = points-(int)((end+1)/10);
         }
         else {
            lookAhead = points - 1;
         }
         
         if(statChoice >0 && statChoice <= firstRange && lookAhead>0) { // zero to endPriority ex: (0, 4]
           
            end++; //increment endurance
            points = lookAhead;
            
            
         }
         
         //STRENGTH PROCESS
         if(statChoice >firstRange && statChoice <= secondRange &&str>=20) {
            lookAhead = points-(int)((str+1)/10);
         }
         else {
            lookAhead = points - 1;
         }
         if(statChoice >firstRange && statChoice <= secondRange &&lookAhead>0) { // endPriority to (endPriority+strPriority) ex: (4, 8]
           
            str++; //increment strength
            points = lookAhead; 
         }
         
         //ARCANA PROCESS
         if(arc>=20 && statChoice >secondRange && statChoice <= thirdRange) {
            lookAhead = points-(int)((arc+1)/10);
         }
         else {
            lookAhead = points - 1;
         }
         if(statChoice >secondRange && statChoice <= thirdRange &&lookAhead>0) { // (endPriority+strPriority) to (end+str+arc) ex: (8, 12]
           
            arc++; //increment arcana
            points = lookAhead; 
         }
         
         //DEXTERITY PROCESS
         if(statChoice >thirdRange && statChoice <= (totalPriority)&&dex>=20) {
            lookAhead = points-(int)((dex+1)/10);
         }
         else {
            lookAhead = points - 1;
         }
         if(statChoice >thirdRange && statChoice <= (totalPriority)&& lookAhead>0) { // (end+str+arc) to (end+str+arc+dex) ex: (12, 16]
          
            dex++; //increment dexterity
            points = lookAhead;
         
            
         }
      
         if(lastPoints==points) {
            incrementLowest();
            return;
         }
      }
   }
   
   public void calcExtraStats() {
   //this is for all levels after one, dummy
   //should just add to base stats
   }
   
   public void calcDraiocht() {
      draiocht=0;
      if(race.equals("Dwarf")) {
         for(int a = 0; a < DWARF_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*DWARF_MANA_DIE)+ 1;
         }
         draiocht+=DWARF_MANA_BONUS;
         if(draiocht<DWARF_DRAIOCHT_MIN) {
            draiocht = DWARF_DRAIOCHT_MIN;
         }
         replenishRate = DWARF_REPLENISH;
      }
      if(race.equals("Elf")) {
         for(int a = 0; a < ELF_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*ELF_MANA_DIE)+ 1;
         }
         draiocht+=ELF_MANA_BONUS;
         if(draiocht<ELF_DRAIOCHT_MIN) {
            draiocht = ELF_DRAIOCHT_MIN;
         }
         replenishRate = ELF_REPLENISH;
      }
      if(race.equals("Human")) {
         for(int a = 0; a < HUMAN_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*HUMAN_MANA_DIE)+ 1;
         }
         draiocht+=HUMAN_MANA_BONUS;
         if(draiocht<HUMAN_DRAIOCHT_MIN) {
            draiocht = HUMAN_DRAIOCHT_MIN;
         }
         replenishRate = HUMAN_REPLENISH;
      }
      if(race.equals("Half-elf")) {
         for(int a = 0; a < HALFELF_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*HALFELF_MANA_DIE)+ 1;
         }
         draiocht+=HALFELF_MANA_BONUS;
         if(draiocht<HALFELF_DRAIOCHT_MIN) {
            draiocht = HALFELF_DRAIOCHT_MIN;
         }
         replenishRate = HALFELF_REPLENISH;
      }
      if(race.equals("Half-orc")) {
         for(int a = 0; a < HALFORC_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*HALFORC_MANA_DIE)+ 1;
         }
         draiocht+=HALFORC_MANA_BONUS;
         if(draiocht<HALFORC_DRAIOCHT_MIN) {
            draiocht = HALFORC_DRAIOCHT_MIN;
         }
         replenishRate = HALFORC_REPLENISH;
      }
      if(race.equals("Hobbit")) {
         for(int a = 0; a < HOBBIT_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*HOBBIT_MANA_DIE)+ 1;
         }
         draiocht+=HOBBIT_MANA_BONUS;
         if(draiocht<HOBBIT_DRAIOCHT_MIN) {
            draiocht = HOBBIT_DRAIOCHT_MIN;
         }
         replenishRate = HOBBIT_REPLENISH;
      }
      if(race.equals("Dragonborn")) {
         for(int a = 0; a < DRAGONBORN_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()* DRAGONBORN_MANA_DIE)+ 1;
         }
         draiocht+= DRAGONBORN_MANA_BONUS;
         if(draiocht< DRAGONBORN_DRAIOCHT_MIN) {
            draiocht =  DRAGONBORN_DRAIOCHT_MIN;
         }
         replenishRate =  DRAGONBORN_REPLENISH;
      }
      if(race.equals("Drow")) {
         for(int a = 0; a < DROW_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*DROW_MANA_DIE)+ 1;
         }
         draiocht+=DROW_MANA_BONUS;
         if(draiocht<DROW_DRAIOCHT_MIN) {
            draiocht = DROW_DRAIOCHT_MIN;
         }
         replenishRate = DROW_REPLENISH;
      }
      if(race.equals("Eladrin")) {
         for(int a = 0; a < ELADRIN_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*ELADRIN_MANA_DIE)+ 1;
         }
         draiocht+=ELADRIN_MANA_BONUS;
         if(draiocht<ELADRIN_DRAIOCHT_MIN) {
            draiocht = ELADRIN_DRAIOCHT_MIN;
         }
         replenishRate = ELADRIN_REPLENISH;
      }
      if(race.equals("Tiefling")) {
         for(int a = 0; a < TIEFLING_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*TIEFLING_MANA_DIE)+ 1;
         }
         draiocht+=TIEFLING_MANA_BONUS;
         if(draiocht<TIEFLING_DRAIOCHT_MIN) {
            draiocht = TIEFLING_DRAIOCHT_MIN;
         }
         replenishRate = TIEFLING_REPLENISH;
      }
      if(race.equals("Faunus")) {
         for(int a = 0; a < FAUNUS_DRAIOCHT_ROLLS;a++) {
            draiocht += (int)(Math.random()*FAUNUS_MANA_DIE)+ 1;
         }
         draiocht+=FAUNUS_MANA_BONUS;
         if(draiocht<FAUNUS_DRAIOCHT_MIN) {
            draiocht = FAUNUS_DRAIOCHT_MIN;
         }
         replenishRate = FAUNUS_REPLENISH;
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
}