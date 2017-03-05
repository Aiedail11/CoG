public class SGTester {
   public static void main (String[] args) {
   String race = "Faunus";
      StatsGen test = new StatsGen(1, race, true);
      System.out.println("Race: " + race);
      System.out.println("Level: " + test.level);
      System.out.println("Wisps: " + test.wisps);
      System.out.println();
      System.out.println("Endurance: " + test.end);
      System.out.println("Dexterity: " + test.dex);
      System.out.println("Strength: " + test.str);
      System.out.println("Arcana: " + test.arc);
      System.out.println();
      System.out.println("Draiocht: " + test.draiocht);
      System.out.println("Replenish Rate: " + test.replenishRate);
   }

}