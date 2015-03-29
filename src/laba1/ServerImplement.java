package laba1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Елена on 15.03.2015.
 */
public class ServerImplement extends UnicastRemoteObject implements DataServer
{
    ArrayList<Book> books;

    public ServerImplement() throws RemoteException
    {
        super();
        this.books=new ArrayList<Book>();
    }

    @Override
    public ArrayList<Book> getAll () throws RemoteException
    {
        return this.books;
    }

    @Override
    //Добавление элемента в конец списка
    public void insert (Book book) throws RemoteException
    {
        this.books.add(book);
    }

    @Override
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

    @Override
    public ArrayList<Book> findByTitle(String title) throws RemoteException
    {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books)
        {
            if (title.equals(book.getTitle()))
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    @Override
    public ArrayList<Book> findByGenre(String genre) throws RemoteException
    {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books)
        {
            if (genre.equals(book.getGenre()))
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    @Override
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

    @Override
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
