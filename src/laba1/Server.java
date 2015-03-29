package laba1;

import laba1.DataServer;
import laba1.ServerImplement;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.MalformedURLException;

/**
 * Created by Елена on 14.03.2015.
 */
public class Server
{
    public static void main (String [] args) throws RemoteException, MalformedURLException
    {
        DataServer server = new ServerImplement();      //Создание удалённого объекта
        Registry registry = LocateRegistry.createRegistry (1099); // регистрация удаленного объекта в реестре rmiregistry
        String nameServer = "rmi://localhost/book";      //Название удалённого объекта
        registry.rebind(nameServer, server);
    }
}
