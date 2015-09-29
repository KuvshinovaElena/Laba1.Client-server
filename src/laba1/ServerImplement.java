package laba1;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Елена on 15.03.2015.
 */

public class ServerImplement extends Thread {
    ArrayList<Book> books;
    public Map<Integer,Socket> clients = new HashMap<Integer, Socket>();
    DOM dom = new DOM();
    private ServerSocket server;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public ServerImplement(ServerSocket server) throws IOException {
        this.books = new ArrayList<Book>();
        this.server = server;
        try {
            dom.XMLReader(books);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for clients request");

            while(true) {
                //ожидание соединения клиентов
                Socket socket = server.accept();

                //создание  объекта ObjectInputStream для чтения
                ois = new ObjectInputStream(socket.getInputStream());
                //создание  объекта ObjectOutputStream для записи
                oos = new ObjectOutputStream(socket.getOutputStream());


                try {
                    String message;
                    List<List<String>> listE = (List<List<String>>) ois.readObject();
                    System.out.println("Message received: " + (message = IncomingEvent(listE)));

                    if (message.equals("Connect new client made successfully:"))
                    {
                        System.out.println("\t" + socket.toString());
                        clients.put(socket.getPort(),socket);
                        continue;
                    }

                    if (message.equals("Client removed:")) {
                        Integer port = (Integer) ois.readObject();
                        System.out.println(clients.get(port).toString());
                        oos = new ObjectOutputStream(clients.get(port).getOutputStream());
                        oos.writeObject("Bye, Client!");
                        clients.remove(port); //удаляем сокет из map
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
                oos.close();
                ois.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTables() throws RemoteException {
        for (int i = 0; i < clients.size(); i++) {
        }
    }

    public void deleteClient(RemoutInterface client) throws RemoteException {
        clients.remove(client);
    }

    public ArrayList<Book> getAll() throws RemoteException {
        return this.books;
    }

    //Добавление элемента в конец списка
    public void paste(Book book) throws RemoteException {
        books.add(book);
       // updateTables();
        try {
            dom.XMLWriter(books);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void edit(int index, Book book) throws RemoteException {
        books.set(index, book);
        try {
            dom.XMLWriter(books);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public int IndexEdit(String article, Book book) throws RemoteException {
        int index = 0;
        for (Book dop : this.books) {
            if (article.equals(dop.getArticle())) {
                edit(index, book);

            }
            index++;
        }
        return index;
    }

    public ArrayList<Book> findByAutor(String autor) throws RemoteException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book : books) {
            if (autor.equals(book.getAutor())) {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public ArrayList<Book> findByTitle(String title) throws RemoteException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book : books) {
            if (title.equals(book.getTitle())) {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public ArrayList<Book> findByArticle(String article) throws RemoteException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book : books) {
            if (article.equals(book.getArticle())) {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public ArrayList<Book> findByArticle(String article, String newarticle) throws RemoteException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        if (newarticle.equals(article)) {
            return newbooks;
        }
        for (Book book : books) {
            if (newarticle.equals(book.getArticle())) {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public ArrayList<Book> findByQuantity(int quantity) throws RemoteException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getQuantity() == quantity) {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public ArrayList<Book> findByPrice(int price) throws RemoteException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getPrice() == price) {
                newbooks.add(book);
            }
        }
        return newbooks;
    }

    public boolean delAll() throws RemoteException {
        if (!books.isEmpty()) {
            this.books.clear();
            try {
                dom.XMLWriter(books);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
           // updateTables();
            return true;
        } else
            return false;
    }

    public ArrayList<Book> delTheArticle(String article) throws RemoteException {
        ArrayList<Book> newbooks = new ArrayList<Book>();
        for (Book book : books) {
            if (!article.equals(book.getArticle())) {
                newbooks.add(book);
            }
        }

        this.books = newbooks;
        try {
            dom.XMLWriter(books);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

       // updateTables();
        return this.books;
    }
    public String IncomingEvent(List<List<String>> messages) throws IOException, XMLStreamException {
        if (messages != null) {
           ArrayList<Book> books = EventBase.decodingMessages(messages);
            switch (Integer.parseInt(messages.get(0).get(0))) {

                case EventBase.CLIENT_CONNECTION:
                {
                    //конвертируем сообщение от клиента в строку
                    String message = null;   //Читаем сообщение Hi server! I'm client...
                    try {
                        message = (String) ois.readObject();

                        System.out.println("Message received: " + message);

                        //пишем сообщение в сокет через ObjectOutputStream
                        oos.writeObject("Hi Client!");
                        List<List<String>> reply = EventBase.codingMessages(EventBase.ADD, this.books,null);
                        oos.writeObject(reply);      //пишем список всех TV
                        oos.writeObject("TVs added");
                        return "Connect new client made successfully:";

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                case EventBase.ADD:
                {
                    paste(books.get(0));
                    updateTables();
                }

                case EventBase.DELETE:
                {
                    delTheArticle(messages.get(1).get(1));
                    updateTables();
                }
                case EventBase.EDIT:
                {
                    IndexEdit(messages.get(1).get(5), books.get(0));
                    updateTables();
                }

                case EventBase.GET_LIST:
                {
                    findByArticle(messages.get(1).get(0),messages.get(2).get(0));
                }

                case EventBase.CLIENT_SHUTDOWN:
                {
                    return "Client removed:";
                }
            }
        }
        return "Unknown request!";
    }
}




















