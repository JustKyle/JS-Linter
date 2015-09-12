import java.lang.*;
import java.io.*;

public class Linter {

  // Trims all trailing whitespace and checks if the line in the file contains
  // a {, }, or is empty. If the line doesn't contain these then it checks if
  // the line contains a semicolon, if it does the method returns false.
  public static boolean checkLine(String line) {
    if (line.trim().endsWith("}") || line.trim().endsWith("{") || line.length() == 0) {
      return false;
    }
    else if(line.trim().endsWith(";")) {
      return false;
    }
    else {
      return true;
    }
  }

  // Main method, opens the file and iterates over each line and uses the
  // checkLine method to verify that each line, that doesn't contain loops,
  // functions, if blocks, or while blocks, ends with a semicolon
  public static void main(String [] args) {
    String line = null;
    try {
      FileReader fileReader = new FileReader(args[0]);

      BufferedReader bufferedReader = new BufferedReader(fileReader);

      int lineNum = 1;

      while ((line = bufferedReader.readLine()) != null) {
        if (checkLine(line)) {
          System.out.println(lineNum + ". Statement should end with a semicolon");
        }
        lineNum++;
      }
      bufferedReader.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("Caught FileNotFoundException: " + e.getMessage());
    }
    catch (IOException e) {
      System.out.println("Caught IOException: " + e.getMessage());
    }
  }
}
