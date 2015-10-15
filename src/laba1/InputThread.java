package laba1;

import javafx.collections.FXCollections;
import laba3.GUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by Елена on 22.09.2015.
 */
  public class InputThread extends Thread {
    Socket socket;
    GUI gui;
    ObjectInputStream ois;

    public InputThread(Socket socket, GUI gui) {
            this.socket = socket;
            this.gui = gui;
            start();
    }

    public ObjectInputStream getInputStream(){
        return ois;
    }
    @Override
    public void run() {
        try {
            while (true) {
                ois = new ObjectInputStream(socket.getInputStream());
                String message = (String) ois.readObject();
                System.out.println("Message from server: " + message);
                if (message.equals("Update")){
                    List<String> ListE = (List<String>) ois.readObject();
                    List<Book> books = EventBase.decodingMessages(ListE);
                    gui.databaseUpdateRequest(FXCollections.observableList(books));

                    message = (String) ois.readObject();
                    System.out.println("Message from server: " + message);
                }
                if (message.equals("Bye, Client!")){
                    return;
                }
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
