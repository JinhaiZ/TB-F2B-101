import java.io.*;
import java.util.*;

/**
 * 
 * @author Jinhai ZHOU
 */

public class Calculator {

  //  static final String endMessage = ".";
  //  private MyStreamSocket mySocket;
  //  private InetAddress serverHost;
  //  private int serverPort;

  // public Calculator() {
                                    
  // }
	 
  public static String getResult(String message) {     
    List<String> items = Arrays.asList(message.split(" "));

    if (items.size() == 3) {
      int result = 0;
      switch(items.get(0)) {
        case "ADD":
          result = Integer.parseInt(items.get(1)) + Integer.parseInt(items.get(2));
          break;
        case "MUL":
          result = Integer.parseInt(items.get(1)) * Integer.parseInt(items.get(2));
          break;
        default:
          result = -1;
          break;
      }
      return "Result: "+ String.valueOf(result);
    }

    return "No valide operator found, Echo Message: " + message;
  }

  public static void main(String args[]) {
    String message = "ADD 2 3";
    List<String> items = Arrays.asList(message.split(" "));
    if (items.get(0).equals("ADD")) {
      int result = Integer.parseInt(items.get(1)) + Integer.parseInt(items.get(2));
      System.out.println(result);
    }
    System.out.println(3);
  }
} //end class
