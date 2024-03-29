//**************************************************************
// See BetterExample4ConnectionRequestor.java for an improved
// version of this class.  This class does not allow a timeout
// on connection.  The better version does.
//**************************************************************

import java.net.*;
import java.io.*;

/**
 * This example illustrates the basic syntax for stream-mode
 * socket.
 * @author M. L. Liu
 */
public class Example4ConnectionRequestor {

// An application that requests a connection and
//  sends a message using stream-mode socket.
// Two command line arguments are expected: 
//
//    <host name of the connection accceptor>
//    <port number of the connection accceptor>

   public static void main(String[] args) {
      if (args.length != 2)
         System.out.println
            ("This program requires two command line arguments");
      else {
         try {
  		      InetAddress acceptorHost = InetAddress.getByName(args[0]);
  		      int acceptorPort = Integer.parseInt(args[1]);
            // instantiates a data socket
   	      Socket mySocket = new Socket(acceptorHost, acceptorPort); 
/**/        System.out.println("Connection request granted"); 
            // get an input stream for reading from the data socket
            InputStream inStream = mySocket.getInputStream();
            // create a BufferedReader object for text line input
            BufferedReader socketInput = 
               new BufferedReader(new InputStreamReader(inStream));
/**/        System.out.println("waiting to read");
            // read a line from the data stream
            String message = socketInput.readLine( );
            Integer message2 = Integer.parseInt(socketInput.readLine( ));
/**/        System.out.println("Message received:");
            System.out.println("\t" + message);
            System.out.println("\t" + message2);
            mySocket.close( );
/**/        System.out.println("data socket closed");
         } // end try
	      catch (Exception ex) {
            ex.printStackTrace( );
	      } //end catch
      } // end else
   } // end main
} // end class


