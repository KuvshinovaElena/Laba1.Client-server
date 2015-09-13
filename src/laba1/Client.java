package laba1;

import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Елена on 14.03.2015.
 */
public class Client {
    public static void main (String [] args) throws Exception {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectInputStream oos = null;
        ObjectInputStream ois = null;
        socket = new Socket(host.getHostName(),1098);
        oos = new ObjectInputStream(socket.getInputStream());
        ois =new ObjectInputStream(socket.getInputStream());
        oos.close();
        ois.close();
        socket.close();
      // Registry registry= LocateRegistry.getRegistry(1099);
       String objectName = "rmi://localhost/book";
      // DataServer server= (DataServer)registry.lookup(objectName);
       //ClientImplement clients = new ClientImplement(server);
   }
}
