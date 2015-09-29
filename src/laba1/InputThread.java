package laba1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by mvideo on 22.09.2015.
 */
  public class InputThread extends Thread {

        Socket socket;

        public InputThread(Socket socket) {
            this.socket = socket;
            start();
        }

    @Override
    public void run() {
        String input = null;
        try {
            while (true) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                input = (String) in.readObject();
                System.out.println("IN: " + input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println("ended");
    }
}
