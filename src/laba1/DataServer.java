package laba1;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.util.ArrayList;

/**
 * Created by Елена on 14.03.2015.
 */
public interface DataServer extends Remote {
    public void paste(Book book) throws RemoteException, IOException;
    public ArrayList<Book> getAll() throws RemoteException;
    public void edit(int index,Book book) throws RemoteException, IOException;
    public void IndexEdit(String article, Book book) throws RemoteException, IOException;
    public ArrayList<Book> findByAutor(String autor) throws RemoteException, IOException;
    public ArrayList<Book> findByTitle(String title) throws RemoteException, IOException;
    public ArrayList<Book> findByArticle(String article) throws RemoteException, IOException;
    public ArrayList<Book> findByQuantity(int quantity) throws RemoteException, IOException;
    public ArrayList<Book> findByPrice(int price) throws RemoteException, IOException;
    public boolean delAll() throws RemoteException;
    public ArrayList<Book> delTheArticle(String article) throws RemoteException,IOException;
}
