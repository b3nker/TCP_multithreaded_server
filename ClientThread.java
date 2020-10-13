/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */

package stream;

import java.io.*;
import java.net.*;

/**Corresponds to a server connection
 *
 */
public class ClientThread
        extends Thread {

    private Socket clientSocket;
    private String pseudo;

    ClientThread(Socket s, String pseudo) {
        this.clientSocket = s;
        this.pseudo = pseudo;
    }

    /**
     * receives a request from client then sends an echo to the client
     **/
    public void run() {
        try {
            BufferedReader socIn = null;
            socIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
            while (true) {
                String line = socIn.readLine();
                socOut.println(line);
                if(!line.isEmpty()){
                    EchoServerMultiThreaded.sendMessageToAll(line, this.pseudo);
                }
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }

    public String getPseudo() {
        return pseudo;
    }
}


