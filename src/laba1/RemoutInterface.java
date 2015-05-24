package laba1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Елена on 23.05.2015.
 */
public interface RemoutInterface extends Remote {
    public void databaseUpdateRequest (ArrayList<Book> newList) throws RemoteException;
}
