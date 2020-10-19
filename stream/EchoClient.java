/***
 * EchoClient
 * Example of a TCP client 
 * Date: 10/01/04
 * Authors:
 */
package stream;

import java.io.*;
import java.net.*;


public class EchoClient {

    private static Socket echoSocket = null;
    private static PrintStream socOut = null;
    private static BufferedReader socIn = null;

    /**
     * main method
     * accepts a connection, receives a message from client then sends an echo to the client
     **/

    public EchoClient() {
        setUpClient();
    }


    private void setUpClient() {
        try {
            // Usage: java EchoClient <EchoServer host> <EchoServer port>
            echoSocket = new Socket("10.43.5.122", 200);
            socIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            socOut = new PrintStream(echoSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readReceived(String message) {
        System.out.println(message);
    }

    public void sendMessage(String message) {
        socOut.println(message);
    }
}


