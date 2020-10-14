    /***
     * EchoServer
     * Example of a TCP server
     * Date: 10/01/04
     * Authors:
     */

    package stream;

    import java.io.PrintStream;
    import java.io.PrintWriter;
    import java.net.*;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class EchoServerMultiThreaded  {

        /**
         * main method
         * @param EchoServer port
         *
         **/
        public static List<Socket> outputServiceClientSockets = new ArrayList();
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
                    EchoServerMultiThreaded.addServiceClientSocket(clientSocket);

                    ct.start();
                }
            } catch (Exception e) {
                System.err.println("Error in EchoServer:" + e);
                e.printStackTrace();

            }
        }


        /** Send a message to all connected clients
         *
         * @param message
         */
        synchronized public static void sendMessageToAll(String message, String pseudo) {
            for (Socket s: outputServiceClientSockets) {
                try{
                    PrintStream out = new PrintStream(s.getOutputStream());
                out.println(pseudo + " said : " + message);
                System.out.println("Output : " + out);
                System.out.println(pseudo + " said : " + message);
                }catch(Exception e){
                    e.printStackTrace();
                }
                ;
            }
        }

        /** Insert in a collection, new service client thread output stream
         *
         * @param out, service client thread output stream
         */
        synchronized public static void addServiceClientSocket(Socket s){
                outputServiceClientSockets.add(s);
        }
    }



