package laba1;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Елена on 14.03.2015.
 */
public class Client extends Thread{
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;
    static Socket socketInMap = null;

    public static void main (String [] args) throws Exception {
        socketInMap = new Socket("localhost", 1098);
        oos = new ObjectOutputStream(socketInMap.getOutputStream());
        ois = new ObjectInputStream(socketInMap.getInputStream());
        System.out.println("Sending request to Socket Server");
        //ois.readObject();
        oos.writeObject("Hi server! I'm client - " + socketInMap.toString());
        List<String> eventList = EventBase.codingMessages(EventBase.CLIENT_CONNECTION, null, null);
        oos.writeObject(eventList);
        String message = (String) ois.readObject();    //Читаем сообщение Hi Client!
        System.out.println("Message from server: " + message);
        ois.close();
        oos.close();
        socketInMap.close();
   }
}
