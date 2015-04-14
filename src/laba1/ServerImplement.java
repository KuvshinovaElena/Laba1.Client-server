package laba1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.rmi.ServerException;
import java.lang.IndexOutOfBoundsException;

/**
 * Created by Елена on 15.03.2015.
 */
public class ServerImplement extends UnicastRemoteObject implements DataServer {

    ArrayList<Book> books;
    private static final String FIlE_PATH = "src/BooksDatabase.xml";
    private static final String ROOT = "BooksDatabase";
    private static final String ELEMENT = "Book";
    private static final String ARTICLE = "Article";
    private static final String AUTOR = "Autor";
    private static final String TITLE = "Title";
    private static final String QUANTITY = "Quantity";
    private static final String PRICE = "Price";

    public ServerImplement() throws IOException, SAXException, ParserConfigurationException {
        super();
        this.books=new ArrayList<Book>();
        this.XMLReader();
    }

    @Override
    public ArrayList<Book> getAll () throws RemoteException {
        return this.books;
    }

    @Override
    //Добавление элемента в конец списка
    public void paste (Book book) throws RemoteException, TransformerException, ParserConfigurationException {
        books.add(book);
        this.XMLWriter();
    }
    @Override
    public void edit(int index,Book book) throws RemoteException{
        books.set(index, book);
        this.XMLWriter();
    }
    @Override
    public void IndexEdit(String article,Book book) throws RemoteException {
        int index=0;
        for (Book dop: this.books){
            if (article.equals(book.getArticle())){
                this.edit(index,book);
            }
            index++;
        }
    }
    //Поиск элемента по автору
    @Override
    public ArrayList<Book> findByAutor(String autor) throws RemoteException {
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
    public ArrayList<Book> findByTitle(String title) throws RemoteException {
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
    public ArrayList<Book> findByArticle(String article) throws RemoteException {
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
    public ArrayList<Book> findByQuantity(int quantity) throws RemoteException {
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
    public ArrayList<Book> findByPrice(int price) throws RemoteException {
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
    public ArrayList<Book> delTheArticle(String article) throws RemoteException {
        ArrayList<Book> newbooks= new ArrayList<Book>();
        for (Book book: books) {
            if (!article.equals(book.getArticle())) {
                newbooks.add(book);
            }
        }
        this.books=newbooks;
        //this.XMLWriter();
        return this.books;
    }

    public void XMLWriter () throws ParserConfigurationException, TransformerException {
        if (books.isEmpty()) return;
        File fxml=new File(FIlE_PATH);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.newDocument();

        Element rootElement = document.createElement(ROOT);
        document.appendChild(rootElement);
        int i = 0;
        for (Book book: this.books){
            Element elem = document.createElement(ELEMENT);
            rootElement.appendChild(elem);

            Element article = document.createElement(ARTICLE);
            article.appendChild(document.createTextNode(book.getArticle()));
            elem.appendChild(article);

            Element autor = document.createElement(AUTOR);
            autor.appendChild(document.createTextNode(book.getAutor()));
            elem.appendChild(autor);

            Element title = document.createElement(TITLE);
            title.appendChild(document.createTextNode(book.getTitle()));
            elem.appendChild(title);

            Element quantity = document.createElement(QUANTITY);
            quantity.appendChild(document.createTextNode(Integer.toString(book.getQuantity())));
            elem.appendChild(quantity);

            Element price = document.createElement(PRICE);
            price.appendChild(document.createTextNode(Integer.toString(book.getPrice())));
            elem.appendChild(price);

        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(fxml);
        transformer.transform(source,result);
    }
    public void XMLReader () throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        File fxml=new File(FIlE_PATH);
        if (fxml.length() == 0) return;

        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(fxml);
        NodeList readerList = document.getDocumentElement();
        for (int i = 0; i<readerList.getLength();i++){
            Node node = readerList.item(i);
            if (node.getNodeName() == ELEMENT){
                Book book = new Book();
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);
                    String childNoteContent;
                    childNoteContent = cNode.getLastChild().getTextContent().trim();
                    book.setArticle(childNoteContent);
                    switch (cNode.getNodeName()) {
                        case AUTOR:
                            childNoteContent = cNode.getLastChild().getTextContent().trim();
                            book.setAutor(childNoteContent);
                            break;
                        case TITLE:
                            childNoteContent = cNode.getLastChild().getTextContent().trim();
                            book.setTitle(childNoteContent);
                            break;
                        case QUANTITY:
                            childNoteContent = cNode.getLastChild().getTextContent().trim();
                            book.setQuantity(Integer.parseInt(childNoteContent));
                        case PRICE:
                            childNoteContent = cNode.getLastChild().getTextContent().trim();
                            book.setPrice(Integer.parseInt(childNoteContent));
                            break;
                    }

                }
            }
        }
    }
}




















