package laba1;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.ServerException;
import java.rmi.MarshalException;
import java.rmi.UnmarshalException;
import java.rmi.RemoteException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mvideo on 29.03.2015.
 */
public class ClientImplement {

    private DataServer server;
    private Scanner scanner;

    ClientImplement(DataServer server) throws IOException, NotBoundException {
        this.server = server;
        this.scanner = new Scanner(System.in);
        this.Menu();
    }
    private void Menu() throws IOException, NotBoundException {
        int item;
        do{
            System.out.println("\t\t\tMenu:");
            System.out.println("1 Add to the list of books");
            System.out.println("2 Remove from the list of books...");
            System.out.println("3 Edit information about the book");
            System.out.println("4 Show the full list of books");
            System.out.println("5 Search by book list...");
            System.out.println("0 Exit");
            System.out.print("\nEnter the menu:");
            item = userInput();
            while (true){
                if (item<0 || item>6) {
                    System.out.println("This item not on the menu. Re-enter:");
                    item=userInput();
                }
                else break;
            }
            switch (item) {
                case 1: {
                    itemAdd();
                    break;
                }
                case 2: {
                    try {
                        itemRemove();
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    try {
                        itemEdit();
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 4: {
                    print(server.getAll());
                    break;
                }
                case 5: {
                    itemSearch();
                    break;
                }
                case 0: {
                    break;
                }
                default: break;
            }
            if (item!=0) {
                System.out.println("\nWould you like to return to the menu? Press 1 if yes, 0 if not");
                if (this.userInput() == 1) Menu();
                else item = 0;
            }
        }while (item!=0);
    }

    public int userInput()throws RemoteException,IOException{
        Scanner sc = new Scanner(System.in);
        Integer i = null;
        while (true) {
            String inputText = sc.nextLine();
            try {
                i = Integer.parseInt(inputText);
                break;
            }catch (NumberFormatException e){
                System.out.println("Error! You must enter an integer. Retype " + e.getLocalizedMessage());
            }
        }
        return i;
    }

    private void itemAdd() throws RemoteException, IOException {
        System.out.println("\nEnter the number of titles:");
        int num = userInput();
        while (num!=0){
            Book book = new Book();
            System.out.println("\nEnter article:");
            String article=this.scanner.nextLine();
            if (server.findByArticle(article).isEmpty())
                book.setArticle(article);
            else {
                while(!server.findByArticle(article).isEmpty()) {
                    System.out.println("\nError! This article is already in the database. Retype article:");
                    article = this.scanner.nextLine();
                }
            }
            System.out.println("Enter the name of the author:");
            book.setAutor(this.scanner.nextLine());
            System.out.println("Enter the name of the book");
            book.setTitle(this.scanner.nextLine());
            System.out.println("Enter the number of:");
            book.setQuantity(this.userInput());
            System.out.println("Enter the price:");
            book.setPrise(this.userInput());
            try {
                server.paste(book);
            } catch (TransformerException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            num--;
        }
    }

    private void itemRemove () throws RemoteException, IOException, TransformerException, ParserConfigurationException {
        System.out.println("\t\t\tRemove menu:");
        System.out.println("1 Delete all");
        System.out.println("2 Remove by article");
        System.out.print("\nEnter the menu: ");
        int item = userInput();
        while (true){
            if (item<1 || item>3) {
                System.out.println("This item not on the menu. Re-enter:");
                item=userInput();
            }
            else break;
        }
        switch (item){
            case 1:
                if (server.delAll())
                    System.out.println("The operation was successful");;
                break;
            case 2: {
                ArrayList<Book> list;
                System.out.println("\nEnter the article of the book you want to delete");
                list=server.delTheArticle(this.scanner.nextLine());
                if (list.isEmpty()) System.out.println("This book is not in the database.");
                else System.out.println("The operation was successful");
                break;
            }
            default: break;
        }

    }

    private void itemEdit() throws RemoteException, IOException, TransformerException, ParserConfigurationException {
        Book newbook= new Book();
        System.out.println("Enter the article of the book, the information you want to change:");
        String article=this.scanner.nextLine();
        ArrayList<Book> list=this.server.findByArticle(article);
        if (!list.isEmpty()) {
            print(list);       //Вывод коллекции на экран при условии, что она не пуста
            newbook.setArticle(article);
            System.out.println("\nEnter the new information:");
            System.out.println("Enter the name of the author:");
            newbook.setAutor(this.scanner.nextLine());
            System.out.println("Enter the name of the book");
            newbook.setTitle(this.scanner.nextLine());
            System.out.println("Enter the number of:");
            newbook.setQuantity(this.userInput());
            System.out.println("Enter the price:");
            newbook.setPrise(this.userInput());
            this.server.IndexEdit(article, newbook);
        }
        else System.out.println("Not found");
    }

    public void print(ArrayList<Book> books) throws RemoteException,IOException{
        if (!books.isEmpty()){
            for (Book book: books){
                System.out.println("\nArticle:"+book.getArticle());
                System.out.println("Autor:"+book.getAutor());
                System.out.println("Title:"+book.getTitle());
                System.out.println("Quantity:"+book.getQuantity());
                System.out.println("Price:"+book.getPrice());
            }
        }
        else System.out.println("Em1pty");
    }

    private void itemSearch() throws RemoteException, IOException, NotBoundException {
        System.out.println("\t\t\tSearch menu:");
        System.out.println("1 Search by article");
        System.out.println("2 Search by autor");
        System.out.println("3 Search by title");
        System.out.println("4 Search by quantity");
        System.out.println("5 Search by price");
        System.out.print("\nEnter the menu: ");
        int item = userInput();
        while (true){
            if (item<0 || item>5) {
                System.out.println("This item not on the menu. Retype");
                item=userInput();
            }
            else break;
        }
        ArrayList<Book> list;
        switch (item){
            case 1:
                System.out.println("\nEnter the article:");
                String article=scanner.nextLine();
                list=server.findByArticle(article);
                if (!list.isEmpty()) print(list);
                else System.out.print("\nNot found");
                break;
            case 2:
                System.out.println("\nEnter the autor:");
                String autor=scanner.nextLine();
                list=server.findByAutor(autor);
                if (!list.isEmpty()) print(list);
                else System.out.print("\nNot found");
                break;
            case 3:
                System.out.println("\nEnter the title:");
                String title=scanner.nextLine();
                list=server.findByTitle(title);
                if (!list.isEmpty()) print(list);
                else System.out.print("\nNot found");
                break;
            case 4:
                System.out.println("\nEnter the quantity:");
                int quantity=userInput();
                list=server.findByQuantity(quantity);
                if (!list.isEmpty()) print(list);
                else System.out.print("\nNot found");
                break;
            case 5:
                System.out.println("\nEnter the price:");
                int price =userInput();
                list=server.findByPrice(price);
                if (!list.isEmpty()) print(list);
                else System.out.println("\nNot found");
                break;
            default: break;
        }
    }
}
