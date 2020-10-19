package UI;

import stream.EchoClient;

import java.awt.*;

public class Chat {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            EchoClient model = new EchoClient();
            new FrameManager(model);
        });
    }
}
