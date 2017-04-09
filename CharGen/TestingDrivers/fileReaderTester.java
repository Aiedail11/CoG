public class fileReaderTester {
   public static void main (String[] args) {
   String myName, myClass, myRace;
   
      int gender = 0;
      ReadFiles fileReader = new ReadFiles(1, "Latin", gender);
      
      fileReader.initialize();
      
      int namesIndex = (int)(Math.random() * (fileReader.namesList.size()-1));
      myName=fileReader.namesList.get(138);
      int classIndex = (int)(Math.random() * (fileReader.classes.size()-1));
      myClass = fileReader.classes.get(classIndex);
      
   
      int raceIndex = (int)(Math.random() * (fileReader.races.size()-1));
      myRace = fileReader.races.get(raceIndex);
    
      
      System.out.println("Name: " + myName);
      System.out.println();
      System.out.println("Class: " + myClass);
      System.out.println("Race: " + myRace);
   }
}