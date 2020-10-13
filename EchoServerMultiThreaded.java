/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */

package stream;

import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class EchoServerMultiThreaded  {

    /**
     * main method
     * @param EchoServer port
     *
     **/
    public static Map<String,PrintWriter> outputServiceClientSockets = new HashMap<>();
    public static int nbClient = 0;
    public static void main(String args[]){
        ServerSocket listenSocket;

        if (args.length != 1) {
            System.out.println("Usage: java EchoServer <EchoServer port>");
            System.exit(1);
        }
        try {
            listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port
            System.out.println("Server ready...");
            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Connexion from:" + clientSocket.getInetAddress());
                nbClient ++;
                ClientThread ct = new ClientThread(clientSocket,"Client " + nbClient);
                PrintWriter socOut = new PrintWriter(clientSocket.getOutputStream());
                EchoServerMultiThreaded.addServiceClientSocket(socOut, ct.getPseudo());

                ct.start();
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }


    /** Send a message to all connected clients
     *
     * @param message
     */
    synchronized public static void sendMessageToAll(String message, String pseudo){

        for (String key: outputServiceClientSockets.keySet()) {
            PrintWriter out = outputServiceClientSockets.get(key);
            out.print(pseudo + " said : " + message);
            System.out.println(pseudo + " said : " + message);
        }
    }

    /** Insert in a collection, new service client thread output stream
     *
     * @param out, service client thread output stream
     */
    synchronized public static void addServiceClientSocket(PrintWriter out, String pseudo){
        outputServiceClientSockets.put(pseudo, out);
    }
}



