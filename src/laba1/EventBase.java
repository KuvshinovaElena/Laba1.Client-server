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

    public static List<String> codingMessages (int event, List<Book> books, String article) {
        List<String> message = new ArrayList<>();
        message.add(0, Integer.toString(event));
        if (books == null) {
            return message;
        }
        int k = 1;
        for(int i = 0; i < books.size(); i++) {
            message.add(k, books.get(i).getArticle());
            message.add(++k, books.get(i).getAutor());
            message.add(++k, books.get(i).getTitle());
            message.add(++k, Integer.toString(books.get(i).getQuantity()));
            message.add(++k, Integer.toString(books.get(i).getPrice()));
            if (article != null){       //Для изменения записи
                message.add(++k, article);
            }
        }
        return message;
    }

    //Для удаления и поиска не требуется полная запись о книге, а только лишь артикул
   /* public static List<List<String>> codingMessages (int event, String ... str) {
        List<List<String>> message = new ArrayList<>();
        ArrayList<String> dop = new ArrayList<>();
        dop.add(0, Integer.toString(event));
        dop.add(1, str[0]);
        if(str.length==1){
            dop.add(2,str[1]);
        }
        message.add(0, dop);
        return message;
    }*/

    public static ArrayList<Book> decodingMessages (List<String> message){
        ArrayList<Book> books = new ArrayList<Book>();
        if (message.size() > 1) {
            for (int i = 0; i < message.size() - 1; i++) {
                    books.get(i).setArticle(message.get(i + 1));
                    books.get(i).setAutor(message.get(i + 1));
                    books.get(i).setTitle(message.get(i + 1));
                    books.get(i).setQuantity(Integer.parseInt(message.get(i + 1)));
                    books.get(i).setPrice(Integer.parseInt(message.get(i + 1)));
                }
            }
        return books;
    }
}
