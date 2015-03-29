package laba1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.Scanner;
import java.io.IOException;

/**
 * Created by Елена on 14.03.2015.
 */
public class Client
{
   public static void main (String [] args) throws Exception
   {
       Registry registry= LocateRegistry.getRegistry(33099);
       String objectName = "rmi://localhost/book";
       DataServer server= (DataServer)registry.lookup(objectName);
       Menu();
   }

    private static void Menu ()
    {
        System.out.println("\t\t\tMenu:");
        System.out.println("1 Add to the list of books");
        System.out.println("2 Remove from the list of books");
        System.out.println("3 Edit information about the book");
        System.out.println("4 Show the full list of books");
        System.out.println("5 Search by book list");
        System.out.println("0 Exit");
        System.out.print("Введите пункт меню: ");
        int item = userInput();
        switch (item){
            case 1:
                itemAdd();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

        public static int userInput(){
            Scanner sc = new Scanner(System.in);
            boolean run = true;
            Integer i = null;
            while (run) {
                System.out.print("Введите число: ");
                String inputText = sc.nextLine();
                if (inputText.equals("0"))
                    System.exit(-1);
                try {
                    i = Integer.parseInt(inputText);
                    run = false;
                }catch (NumberFormatException e){
                    System.out.println("Ошибка! Необходимо ввести целое число. Повторите ввод " + e.getLocalizedMessage());
                }
                if (i<0 || i>6) {
                    System.out.println("Данного пункта нет в меню. Поторите ввод:");
                    run=true;
                }
            }
            return i;
        }
    private static void itemAdd(){
        System.out.println("How many books?");


    }
}
