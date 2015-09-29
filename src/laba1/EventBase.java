package laba1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Елена on 22.09.2015.
 */
public class EventBase {

    public static final int CLIENT_CONNECTION = 1;
    public static final int ADD = 2;
    public static final int DELETE = 3;
    public static final int EDIT = 4;
    public static final int GET_LIST = 5;
    public static final int CLIENT_SHUTDOWN = 0;

    public static List<List<String>> codingMessages (int event, List<Book> books, String article) {

        List<List<String>> message = new ArrayList<>();
        ArrayList<String> dop = new ArrayList<>();
        dop.add(0, Integer.toString(event));
        message.add(0, dop);
        if (books == null) {
            return message;
        }

        for(int i = 1; i < books.size(); i++) {
            dop.add(0, books.get(i).getArticle());
            dop.add(1, books.get(i).getAutor());
            dop.add(2, books.get(i).getTitle());
            dop.add(3, Integer.toString(books.get(i).getQuantity()));
            dop.add(4, Integer.toString(books.get(i).getPrice()));
            if (article != null){
                dop.add(5, article);
            }
            message.add(i,dop);
        }
        if (article != null){

        }
        return message;
    }
    //Для удаления и поиска не требуется полная запись о книге, а только лишь артикул
    public static List<List<String>> codingMessages (int event, String ... str) {
        List<List<String>> message = new ArrayList<>();
        ArrayList<String> dop = new ArrayList<>();
        dop.add(0, Integer.toString(event));
        dop.add(1, str[0]);
        if(str.length==1){
            dop.add(2,str[1]);
        }
        message.add(0, dop);
        return message;
    }

    public static ArrayList<Book> decodingMessages (List<List<String>> message){
        ArrayList<Book> books = new ArrayList<Book>();
        if (message.size() > 1) {
            for(int i = 0; i < message.size()-1;i++) {
                books.get(i).setArticle(message.get(i+1).get(0));
                books.get(i).setAutor(message.get(i+1).get(1));
                books.get(i).setTitle(message.get(i+1).get(2));
                books.get(i).setQuantity(Integer.parseInt(message.get(i+1).get(3)));
                books.get(i).setPrice(Integer.parseInt(message.get(i+1).get(4)));
            }
        }
        return books;
    }
}
