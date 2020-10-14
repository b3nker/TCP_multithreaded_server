package stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{

    private Socket clientSocket;
    private BufferedReader socIn = null;

    ReadThread(Socket s) {
        this.clientSocket = s;
        try {
            socIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                System.out.println("En attente d'un message venant des participants...");
                String line = socIn.readLine();
                if (!line.isEmpty()) {
                    System.out.println("Message recu...");
                    EchoClient.leerRecibido(line);
                }
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
            e.printStackTrace();
        }
    }

    public BufferedReader getSocIn(){
        return this.socIn;
    }
}
