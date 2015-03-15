package laba1;

import laba1.DataServer;
import laba1.ServerImplement;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Елена on 14.03.2015.
 */
public class Server
{
    public static void main (String [] args) throws RemoteException
    {
       ServerImplement server= new ServerImplement();      //Создание удалённого объекта
        String nameServer="rmi://localhost/book";      //Название удалённого объекта
        Registry registry = LocateRegistry.createRegistry(); // регистрация удаленного объекта в реестре rmiregistry
        registry.rebind(nameServer, server);
    }
}
