package laba1;

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

    ClientImplement(DataServer server) throws RemoteException {
        this.server = server;
        this.scanner = new Scanner(System.in);
        this.Menu();
    }
    private void Menu() throws RemoteException {
        System.out.println("\t\t\tMenu:");
        System.out.println("1 Add to the list of books");
        System.out.println("2 Remove from the list of books...");
        System.out.println("3 Edit information about the book");
        System.out.println("4 Show the full list of books");
        System.out.println("5 Search by book list");
        System.out.println("0 Exit");
        System.out.print("\nEnter the menu: ");
        int item = userInput();
        while (true){
            if (item<0 || item>6) {
                System.out.println("This item not on the menu. Re-enter:");
                item=userInput();
            }
            else break;
        }
        while (item!=0) {
            switch (item) {
                case 1:
                    itemAdd();
                    break;
                case 2:
                    itemRemove();
                    break;
                case 3:
                    itemEdit();
                    break;
                case 4:
                    this.print(server.getAll());
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    break;
            }
            System.out.println("Would you like to return to the menu? Press 1 if yes, 0 if not");
            if (this.userInput()==1) Menu();
            else item=0;
        }
    }

    public int userInput(){
        Scanner sc = new Scanner(System.in);
        Integer i = null;
        while (true) {
            String inputText = sc.nextLine();
            //if (inputText.equals("0"))
              //  System.exit(-1);
            try {
                i = Integer.parseInt(inputText);
                break;
            }catch (NumberFormatException e){
                System.out.println("Error! You must enter an integer. Retype " + e.getLocalizedMessage());
            }
        }
        return i;
    }

    private void itemAdd() throws RemoteException{
        System.out.println("\nEnter the number of titles:");
        int num = userInput();
        while (num!=0){
            Book book = new Book();
            System.out.println("\nEnter article:");
            book.setArticle(this.scanner.nextLine());
            System.out.println("Enter the name of the author:");
            book.setAutor(this.scanner.nextLine());
            System.out.println("Enter the name of the book");
            book.setTitle(this.scanner.nextLine());
            System.out.println("Enter the number of:");
            book.setQuantity(this.userInput());
            System.out.println("Enter the price:");
            book.setPrise(this.userInput());
            this.server.paste(book);
            System.out.println("\n");
            num--;
        }
    }

    private void itemRemove () throws RemoteException{
        System.out.println("\t\t\tRemove menu:");
        System.out.println("1 Delete all");
        System.out.println("2 Remove by title");
        System.out.println("3 Back to menu");
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
            case 1: server.delAll();
                break;
            case 2:
                System.out.println("\nEnter the article of the book you want to delete");
                server.delTheTitle(this.scanner.nextLine());
                break;
        }

    }
    private void itemEdit() throws RemoteException {
        Book newbook= new Book();
        System.out.println("Enter the article of the book, the information you want to change:");
        String article=scanner.nextLine();
        ArrayList<Book> list=server.findByArticle(article);
        if (!list.isEmpty()) print(list);
    }
    public void print(ArrayList<Book> books) throws RemoteException{
        if (!books.isEmpty()){
            for (Book book: books){
                System.out.println("\nArticle:"+book.getArticle());
                System.out.println("\nAutor:"+book.getAutor());
                System.out.println("\nTitle:"+book.getTitle());
                System.out.println("\nQuantity:"+book.getQuantity());
                System.out.println("\nPrice:"+book.getPrice());
            }
        }
        else System.out.println("Em1pty");
    }

    private void itemSearch() throws RemoteException{

    }

}
