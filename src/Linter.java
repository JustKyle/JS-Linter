
import java.io.*;
import java.util.regex.*;

public class Linter {

  // Trims all trailing whitespace and checks if the line in the file contains
  // a {, }, or is empty. If the line doesn't contain these then it checks if
  // the line contains a semicolon, if it does the method returns false.
  public static boolean checkForSemi(String line) {
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

  // Uses the regex key for trailing whitespace to determine if a line of code
  // contains unnecessary trailing whitespace. If it does contain whitespace
  // then the method returns true.
  public static boolean checkForWhite(String line) {
    Pattern whitespace = Pattern.compile(".*\\s+");
    Matcher m = whitespace.matcher(line);
    return m.matches();
  }

  public static void checkBrackets(String line, int lineNum) {
    if(line.trim().endsWith("{")) {
      if(line.indexOf("{") == 0) {
        System.out.println(lineNum + ". Open curly brace should not stand-alone.");
      }
    }
    if(line.trim().endsWith("}")) {
      line = line.replaceAll("^\\s+", "");
      if(line.indexOf("}") != 0 && line.charAt(1) != '\n') {
        System.out.println(lineNum + ". Closing curly brace should stand-alone.");
      }
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
      String lastLine = "";

      while ((line = bufferedReader.readLine()) != null) {
        if (checkForWhite(line)) {
          System.out.println(lineNum + ". Statement should not have trailing whitespace.");
        }
        if (checkForSemi(line)) {
        System.out.println(lineNum + ". Statement should end with a semicolon");
        }
        checkBrackets(line, lineNum);
        lineNum++;
        lastLine = line;
      }

      if(lastLine.charAt(0) != '\n') {
        System.out.println(lineNum +". File "+ args[0] + " should end with a newline character.");
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
