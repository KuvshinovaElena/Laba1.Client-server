package laba1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.Scanner;
import java.io.IOException;

/**
 * Created by Елена on 14.03.2015.
 */
public class Client
{
   public static void main (String [] args) throws Exception
   {
       Registry registry= LocateRegistry.getRegistry(33099);
       String objectName = "rmi://localhost/book";
       DataServer server= (DataServer)registry.lookup(objectName);
       Menu();
   }

    private static void Menu ()
    {
        int item=1;
        System.out.println("\t\t\tMenu:");
        System.out.println("1 Add to the list of books");
        System.out.println("2 Remove from the list of books");
        System.out.println("3 Edit information about the book");
        System.out.println("4 Show the full list of books");
        System.out.println("5 Search by book list");
        try {
            item=(int)System.in.read();
        } catch  (IOException e) {
            System.out.println("Input ERROR");
        }
    }
}
