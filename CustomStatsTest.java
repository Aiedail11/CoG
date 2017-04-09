public class CustomStatsTest
{
   public static void main(String[] args)
   {
   StatsGen generator = new StatsGen(new SysLog(new CharGen()), 1, "Human", true);
   System.out.print("\nLevel: " + generator.level+
                  "\nWisps: " + generator.wisps + "\n"+
                  
                  "\nEndurance: " + generator.end+
                  "\nDexterity: " + generator.dex+
                  "\nStrength: " + generator.str+
                  "\nArcana: " + generator.arc + "\n"+
                  
                  "\nDraiocht: " + generator.draiocht+
                  "\nReplenish Rate: " + generator.replenishRate);

   }
}