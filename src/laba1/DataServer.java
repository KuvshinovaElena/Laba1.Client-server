package laba1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Елена on 14.03.2015.
 */
public interface DataServer extends Remote {
    public void paste(Book book) throws RemoteException;
    public ArrayList<Book> getAll() throws RemoteException;
    public void edit(String article,Book book ) throws RemoteException;
    public ArrayList<Book> findByAutor(String autor) throws RemoteException;
    public ArrayList<Book> findByTitle(String title) throws RemoteException;
    public ArrayList<Book> findByArticle(String article) throws RemoteException;
    public ArrayList<Book> findByQuantity(int quantity) throws RemoteException;
    public ArrayList<Book> findByPrice(int price) throws RemoteException;
    public void delAll() throws RemoteException;
    public void delTheTitle(String title) throws RemoteException;
}
