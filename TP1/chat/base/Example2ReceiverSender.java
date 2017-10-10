
import java.net.*;
import java.util.Scanner;



/**
 * This example illustrates a process which sends then receives
 * using a datagram socket.
 * @author M. L. Liu
 */
public class Example2ReceiverSender {
// An application which sends then receives a message using
// connectionless datagram socket.
// Four command line arguments are expected, in order: 
//    <domain name or IP address of the receiver>
//    <port number of the receiver's datagram socket>
//    <port number of this process's datagram socket>
//    <message, a string, to send>

   public static void main(String[] args) {
      if (args.length != 3)
         System.out.println
            ("This program requires four command line arguments");
      else {
         try {      
  		      InetAddress receiverHost = InetAddress.getByName(args[0]);
            int receiverPort = Integer.parseInt(args[1]);
  		      int myPort = Integer.parseInt(args[2]);
            //String message = args[3];
   	      MyDatagramSocket mySocket = new MyDatagramSocket(myPort);  
               // instantiates a datagram socket for both sending
               // and receiving data
                        // First wait to receive a datagram from the socket
            while(true) {
                  mySocket.setSoTimeout(5000);
                  System.out.println(mySocket.receiveMessage());
                  // Now send a message to the other process.
                  Scanner reader = new Scanner(System.in);  // Reading from System.in
                  System.out.println("Enter a message: ");
                  String msg = reader.nextLine(); // Scans the next token of the input as an int
                  mySocket.sendMessage( receiverHost, receiverPort, msg);
                  
            }
            //
         } // end try
	      catch (Exception ex) {
            ex.printStackTrace( );
	      } //end catch
      } //end else
   } //end main 

} //end class
