package laba1;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.util.ArrayList;

/**
 * Created by Елена on 14.03.2015.
 */
public interface DataServer extends Remote {

    public void paste(Book book) throws RemoteException;
    public ArrayList<Book> getAll() throws RemoteException;
    public void edit(int index,Book book) throws RemoteException;
    public int IndexEdit(String article, Book book) throws RemoteException;
    public ArrayList<Book> findByAutor(String autor) throws RemoteException;
    public ArrayList<Book> findByTitle(String title) throws RemoteException;
    public ArrayList<Book> findByArticle(String article) throws RemoteException;
    public ArrayList<Book> findByArticle (String article, String newarticle) throws RemoteException;
    public ArrayList<Book> findByQuantity(int quantity) throws RemoteException;
    public ArrayList<Book> findByPrice(int price) throws RemoteException;
    public boolean delAll() throws RemoteException;
    public ArrayList<Book> delTheArticle(String article) throws RemoteException;
    public void setClient(RemoutInterface client) throws RemoteException;
    public void deleteClient (RemoutInterface client) throws RemoteException;
}
