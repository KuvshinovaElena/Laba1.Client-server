package laba1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Елена on 15.03.2015.
 */
public class ServerImplement implements DataServer
{
    ArrayList<Book> books=new ArrayList<Book>();

    public ServerImplement() throws RemoteException
    {
        super();
    }

    @Override
    public ArrayList<Book> getAll () throws RemoteException
    {
        return books;
    }

    @Override
    //Добавление элемента в базу
    public void insert (Book book) throws RemoteException
    {
        books.add(book);
    }


    public ArrayList<Book> findByAutor(String autor) throws RemoteException
    {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books)
        {
            if (autor.equals(book.getAutor()))
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public ArrayList<Book> findByTitle(String title) throws RemoteException
    {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books)
        {
            if (book.getTitle() == title)
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public ArrayList<Book> findByGenre(Book.Genre genre) throws RemoteException
    {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books)
        {
            if (book.getGenre() == genre)
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public ArrayList<Book> findByQuantity(int quantity) throws RemoteException
    {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books)
        {
            if (book.getQuantity() == quantity)
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public ArrayList<Book> findByPrice(double price) throws RemoteException
    {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books)
        {
            if (book.getPrice() == price)
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }


}
