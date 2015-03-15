package laba1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Елена on 15.03.2015.
 */
public class ServerImplement implements DataServer
{
    List<Book> books=new LinkedList();

    public ServerImplement() throws RemoteException
    {
        super();
    }

    @Override
    public List<Book> getAll () throws RemoteException
    {
        return books;
    }

    @Override
    //Добавление элемента в конец списка
    public void insert (Book book) throws RemoteException
    {
        books.add(book);
    }


    public List<Book> findByAutor(String autor) throws RemoteException
    {
        List<Book> newbooks = new LinkedList();
        for (Book book: books)
        {
            if (autor.equals(book.getAutor()))
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public List<Book> findByTitle(String title) throws RemoteException
    {
        List<Book> newbooks = new LinkedList();
        for (Book book: books)
        {
            if (book.getTitle() == title)
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public List<Book> findByGenre(Book.Genre genre) throws RemoteException
    {
        List<Book> newbooks = new LinkedList();
        for (Book book: books)
        {
            if (book.getGenre() == genre)
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public List<Book> findByQuantity(int quantity) throws RemoteException
    {
        List<Book> newbooks = new LinkedList();
        for (Book book: books)
        {
            if (book.getQuantity() == quantity)
            {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public List<Book> findByPrice(double price) throws RemoteException
    {
        List<Book> newbooks = new LinkedList();
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
