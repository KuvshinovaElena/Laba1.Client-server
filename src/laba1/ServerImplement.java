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
    public void edit(String article,Book book) throws RemoteException,IOException{
        int index=1;
        for (Book dop: books){
            if (dop.getArticle()== article){
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
        }
        return newbooks;
    }

    @Override
    public boolean delAll() throws RemoteException {
        if (!books.isEmpty()) {
            this.books.clear();
            return true;
        }
        else
            return false;
    }

    @Override
    public ArrayList<Book> delTheArticle(String article) throws RemoteException,IOException {
        ArrayList<Book> newbooks= new ArrayList<Book>();
        newbooks=findByArticle(article);
        for (Book book: books) {
            if (!article.equals(book.getArticle())) {
                newbooks.add(book);
            }
        }
        this.books=newbooks;
        return this.books;
    }
}
