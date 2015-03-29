package laba1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Елена on 14.03.2015.
 */
public interface DataServer extends Remote {
    public void insert(Book book) throws RemoteException;
    public List<Book> getAll() throws RemoteException;
    public List<Book> findByAutor(String autor) throws RemoteException;
    public List<Book> findByTitle(String title) throws RemoteException;
    public List<Book> findByGenre(String genre) throws RemoteException;
    public List<Book> findByQuantity(int quantity) throws RemoteException;
    public List<Book> findByPrice(double price) throws RemoteException;

}
