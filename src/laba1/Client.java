package laba1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Елена on 14.03.2015.
 */
public class Client {

    public static void main (String [] args) throws Exception {
       Registry registry= LocateRegistry.getRegistry(1099);
       String objectName = "rmi://localhost/book";
       DataServer server= (DataServer)registry.lookup(objectName);
       ClientImplement clients = new ClientImplement(server);
   }
}
