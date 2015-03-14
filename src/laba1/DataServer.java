package laba1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Елена on 14.03.2015.
 */
public interface DataServer
{
        public void insert(Book book) throws RemoteException;
        public ArrayList<Book> getAll() throws RemoteException;;

}
