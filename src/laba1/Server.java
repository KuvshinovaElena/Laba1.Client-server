package laba1;

import laba1.DataServer;
import laba1.ServerImplement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Created by Елена on 14.03.2015.
 */

//Смотри примеры 1 и 2 в lab_5 и лекциях

public class Server {

    public static void main (String [] args) throws IOException, ClassNotFoundException
    {
        ServerImplement server = new ServerImplement();      //Создание удалённого объекта
      /*  ServerSocket server = new ServerSocket(1098);
        System.out.println("Running...");
        Socket socket = server.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();     //Письмо от подключённого клиента
        System.out.println("Running...");
        ois.close();
        socket.close();
        server.close();*/
        Registry registry = LocateRegistry.createRegistry(1099); // регистрация удаленного объекта в реестре rmiregistry
        String nameServer = "rmi://localhost/book";      //Название удалённого объекта
        registry.rebind(nameServer, server);
       //String message = (String) ois.readObject();
        System.out.println("Running...");
    }

}
