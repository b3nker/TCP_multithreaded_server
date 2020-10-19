package stream;

import UI.FrameManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{
    private Socket clientSocket;
    private BufferedReader socIn = null;
    private FrameManager frontEnd;

    public ReadThread(Socket s, FrameManager frontEnd) {
        this.clientSocket = s;
        this.frontEnd = frontEnd;
        try {
            socIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                String line = socIn.readLine();
                if (!line.isEmpty()) {
                    frontEnd.readReceived(line);
                }
            }
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }
}
