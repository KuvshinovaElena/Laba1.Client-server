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

public class Server {

    public static void main (String [] args) throws IOException, ClassNotFoundException
    {
        ServerSocket server = new ServerSocket(1098);
        new ServerImplement(server);
    }

}
