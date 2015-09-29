package laba1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by Елена on 22.09.2015.
 */
public class OutputThread extends Thread{
    Socket socket;

    public OutputThread(Socket socket) {
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject("Hi, I'm new client");
            while(true) {
                try {
                        TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }
                out.writeObject("Hi, I'm new client");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

