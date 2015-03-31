package laba1;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 * Created by Елена on 15.03.2015.
 */
public class ServerImplement extends UnicastRemoteObject implements DataServer {

    ArrayList<Book> books;

    public ServerImplement() throws RemoteException {
        super();
        this.books=new ArrayList<Book>();
    }

    @Override
    public ArrayList<Book> getAll () throws RemoteException {
        return this.books;
    }

    @Override
    //Добавление элемента в конец списка
    public void paste (Book book) throws RemoteException,IOException{

        books.add(book);
    }
    @Override
    //Редактирование элемента
    public void edit(String article,Book book ) throws RemoteException,IOException{
        int index=1;
        for (Book dop: books){
            if (article.equals(book.getArticle())){
                break;
            }
            index++;
        }
        books.set(index, book);
    }
    //Поиск элемента по автору
    @Override
    public ArrayList<Book> findByAutor(String autor) throws RemoteException,IOException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books) {
            if (autor.equals(book.getAutor())){
                newbooks.add(book);
            }
            else  System.out.println("This book is not in the database.");
        }
        return newbooks;
    }
    //Поиск элемента по названию
    @Override
    public ArrayList<Book> findByTitle(String title) throws RemoteException,IOException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books) {
            if (title.equals(book.getTitle())) {
                newbooks.add(book);
            }
            else  System.out.println("This book is not in the database.");
        }
        return newbooks;
    }
    //Поиск элемента по артикулу
    @Override
    public ArrayList<Book> findByArticle(String article) throws RemoteException,IOException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books) {
            if (article.equals(book.getArticle())) {
                newbooks.add(book);
            }
            else  System.out.println("This book is not in the database.");
        }
        return newbooks;
    }
    //Поиск элемента по количеству
    @Override
    public ArrayList<Book> findByQuantity(int quantity) throws RemoteException,IOException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books) {
            if (book.getQuantity() == quantity) {
                newbooks.add(book);
            }
            else  System.out.println("This book is not in the database.");
        }
        return newbooks;
    }
    //Поиск элемента по цене
    @Override
    public ArrayList<Book> findByPrice(int price) throws RemoteException,IOException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book: books) {
            if (book.getPrice() == price) {
                newbooks.add(book);
            }
            else  System.out.println("This book is not in the database.");
        }
        return newbooks;
    }

    @Override
    public void delAll() throws RemoteException {
        this.books.clear();
    }

    @Override
    public void delTheArticle(String article) throws RemoteException,IOException {
        ArrayList<Book> newbooks= new ArrayList<Book>();
        for(Book book: this.books) {
            if (article.equals(book.getArticle()))
                newbooks.add(book);
            else
                System.out.println("This book is not in the database.");
        }
        this.books=newbooks;
    }
}
