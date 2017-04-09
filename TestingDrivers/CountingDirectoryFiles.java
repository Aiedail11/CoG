import java.util.*;
import java.io.*;

public class CountingDirectoryFiles {

   public static void main(String[] args) {
      File test = new File(".\\\\CDFTesting");
      int count  = test.list().length;
      System.out.println("There are "+ count + " files in this directory.");
      if(count ==3) {
      System.out.println("YAYYYYY");
      }
   }
}