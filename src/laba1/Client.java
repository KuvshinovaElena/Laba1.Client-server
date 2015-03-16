package laba1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.Scanner;
import java.io.IOException;

/**
 * Created by Елена on 14.03.2015.
 */
public class Client
{
   public static void main (String [] args) throws Exception{
       Registry registry= LocateRegistry.getRegistry();
       String objectName = "rmi://localhost/book";
       DataServer server= (DataServer)registry.lookup(objectName);
   }
    private void Menu () throws 
}
