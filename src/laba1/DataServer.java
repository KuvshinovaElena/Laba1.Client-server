package laba1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Елена on 14.03.2015.
 */
public interface DataServer extends Remote {
     public void insert(Book book) throws RemoteException;
    public ArrayList<Book> getAll() throws RemoteException;
    public ArrayList<Book> findByAutor(String autor) throws RemoteException;
    public ArrayList<Book> findByTitle(String title) throws RemoteException;
    public ArrayList<Book> findByGenre(String genre) throws RemoteException;
    public ArrayList<Book> findByQuantity(int quantity) throws RemoteException;
    public ArrayList<Book> findByPrice(double price) throws RemoteException;
    public void delAll() throws RemoteException;
    public void delTheTitle(String title) throws RemoteException;

}
